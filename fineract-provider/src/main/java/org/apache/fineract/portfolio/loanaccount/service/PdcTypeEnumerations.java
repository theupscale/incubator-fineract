package org.apache.fineract.portfolio.loanaccount.service;

import org.apache.fineract.infrastructure.core.data.EnumOptionData;
import org.apache.fineract.portfolio.loanaccount.domain.PdcTypeEnumOption;

public class PdcTypeEnumerations {
	
	public static EnumOptionData pdcType(final int d){
		return pdcType(PdcTypeEnumOption.fromInt(d));
	}
	
	public static EnumOptionData pdcType(final PdcTypeEnumOption pdcTypeValue){
		EnumOptionData option = null;
		
		switch (pdcTypeValue) {
		case FULL_PDC:
			option = new EnumOptionData(PdcTypeEnumOption.FULL_PDC.getValue().longValue(), PdcTypeEnumOption.FULL_PDC.getCode(), "FullPdc");
			break;
		case SECURITY_PDC: 
			option = new EnumOptionData(PdcTypeEnumOption.SECURITY_PDC.getValue().longValue(), PdcTypeEnumOption.SECURITY_PDC.getCode(), "SecurityPdc");
		default:
			option = new EnumOptionData(PdcTypeEnumOption.INVALID.getValue().longValue(), PdcTypeEnumOption.INVALID.getCode() , "Invalid");
			break;
		}
		return option;
	}
}
