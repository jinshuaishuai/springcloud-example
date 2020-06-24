package com.jin.common.util;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Assert;

/**
 * 将中文转化为拼音
 *
 * @author shuai.jin
 * @date 2020/6/23 18:55
 */
@Slf4j
public class ChineseToPinyinUtil {

    public static String chineseToPinyin(String name) throws BadHanyuPinyinOutputFormatCombination {
        //不能为空
        Assert.assertNotNull(name);
        char[] chineseChars = name.toCharArray();
        String[] chinesePinyinStrs = new String[chineseChars.length];
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
        pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder result = new StringBuilder();
        int length = chineseChars.length;
        try {
            for (int i = 0; i < length; i++) {
                // 判断是否为汉字字符
                if (Character.toString(chineseChars[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    //如果是中文,将其放到中文拼音数组中
                    chinesePinyinStrs = PinyinHelper.toHanyuPinyinStringArray(chineseChars[i], pyFormat);
                    result.append(chinesePinyinStrs[0]);
                } else {
                    // 如果不是汉字字符，直接取出字符并连接到字符串后面
                    result.append(Character.toString(chineseChars[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("格式化汉语拼音时出现错误：------>{}" + e.toString());
        }
        return result.toString();
    }

}
