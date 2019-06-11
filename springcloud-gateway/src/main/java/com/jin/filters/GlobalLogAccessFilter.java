package com.jin.filters;

import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@Component
public class GlobalLogAccessFilter implements GlobalFilter,Ordered{
	@Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        String addressStr = remoteAddress.getAddress().toString();
        HttpMethod method = request.getMethod();
        String contentType = request.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        String url = request.getURI().getRawPath();
        String requestParams = request.getQueryParams().toString();
        StringBuffer normalMsg = new StringBuffer("");
        normalMsg.append("Request[ \n");
        normalMsg.append("remoteAddress: ").append(addressStr).append("\n");
        normalMsg.append("method: ").append (method == null ? "" : method.toString()).append("\n");
        normalMsg.append("url: ").append(url).append("\n");
        normalMsg.append("query params: ").append(requestParams).append("\n");

        ServerHttpResponseDecorator responseDecorator = this.handleResponse(exchange);

        //POST request取一次之后会失效，需重新组装request返回
        if (method == HttpMethod.POST && MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(contentType)
                || MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType)) {
            String bodyStr = resolveBodyFromRequest(request);
            //uri
            URI uri = request.getURI();
            URI newUri = UriComponentsBuilder.fromUri(uri).build(true).toUri();
            DataBuffer bodyDataBuffer = stringBuffer(bodyStr);
            Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);
            ServerHttpRequest newRequest = exchange.getRequest().mutate().header("Expect", "100-continue").uri(newUri).build();
            newRequest = new ServerHttpRequestDecorator(newRequest) {

                @Override
                public Flux<DataBuffer> getBody() {
                    return bodyFlux;
                }
            };
            normalMsg.append("request body: ").append(bodyStr).append("\n");
            normalMsg.append("] \n");
            log.info(normalMsg.toString());
            //重组原request
            return chain.filter(exchange.mutate().request(newRequest).response(responseDecorator).build());
        }
        normalMsg.append("] \n");
        log.info(normalMsg.toString());
        return chain.filter(exchange.mutate().response(responseDecorator).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }

    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     * @return 请求体
     */
    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     * @return 请求体
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
      
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        //获取request body
        return bodyRef.get();
    }

    /**
     * 字符串转DataBuffer
     * @param value
     * @return
     */
    private DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    private ServerHttpResponseDecorator handleResponse(ServerWebExchange exchange)
    {
        log.info(" ----- response start ------");

        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                log.info(" ----- response in ------");
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        StringBuffer normalMsg = new StringBuffer("");
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        String responseResult = new String(content, Charset.forName("UTF-8"));
                        normalMsg.append("Response:[ \n");
                        normalMsg.append(" status: ").append(this.getStatusCode()).append("\n");
                        normalMsg.append(" header:").append(this.getHeaders()).append("\n");;
                        normalMsg.append(" responseResult: ").append(responseResult).append("\n");
                        normalMsg.append("] \n");
                        log.info(normalMsg.toString());
                        return bufferFactory.wrap(content);
                    }));
                }
                log.info(" ----- response out------");
                return super.writeWith(body);
            }
        };
        log.info(" ----- response end ------");
        return decoratedResponse;
    }
}
