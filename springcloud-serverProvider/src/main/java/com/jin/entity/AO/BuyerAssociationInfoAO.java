package com.jin.entity.AO;

import lombok.Data;

@Data
public class BuyerAssociationInfoAO {
	
	private BuyerInfoAO buyerInfoAO;
	
	private BuyerBankInfoAO buyerBankInfoAO;
	
	private BuyerCorporateIdentityInfoAO buyerCorporateIdentityInfoAO;

}
