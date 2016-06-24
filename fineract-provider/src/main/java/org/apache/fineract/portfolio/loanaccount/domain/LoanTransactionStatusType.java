package org.apache.fineract.portfolio.loanaccount.domain;

import org.apache.fineract.portfolio.loanaccount.data.LoanTransactionStatusEnumData;

public enum LoanTransactionStatusType {
	
	NOSTATUS(0,"loanTransactionStatus.NO_STATUS"),//
	INPROGRESS(1,"loanTransactionStatus.IN_PROGRESS"),//
	CLEAR(2,"loanTransactionStatus.CLEAR_AND_SUCCESFUL"),//
	DECLINED(3,"loanTransactionStatus.DECLINED");
	
	
	
	private final Integer value;
	private final String code;
	
	private LoanTransactionStatusType(final Integer value, final String code){
			this.value = value;
			this.code = code;
			
	}
	
	public Integer getValue(){
		return this.value;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public static LoanTransactionStatusEnumData transactionStatus(final Integer id){
		return transactionStatus(LoanTransactionStatusType.fromInt(id));
	}
	
	public static LoanTransactionStatusEnumData transactionStatus(final LoanTransactionStatusType transactionStatus){
		
		LoanTransactionStatusEnumData loanStatus = null;
		switch(transactionStatus){
		case NOSTATUS:
			loanStatus = new LoanTransactionStatusEnumData(LoanTransactionStatusType.NOSTATUS.getValue().longValue(), 
					LoanTransactionStatusType.NOSTATUS.getCode(), "NOSTATUS");
			break;
		case INPROGRESS:
			loanStatus = new LoanTransactionStatusEnumData(LoanTransactionStatusType.INPROGRESS.getValue().longValue(),
					LoanTransactionStatusType.INPROGRESS.getCode(), "INPROGRESS");
			break;
		case CLEAR:
			loanStatus = new LoanTransactionStatusEnumData(LoanTransactionStatusType.CLEAR.getValue().longValue(),
					LoanTransactionStatusType.CLEAR.getCode(), "CLEAR");
			break;
		case DECLINED:
			loanStatus = new LoanTransactionStatusEnumData(LoanTransactionStatusType.DECLINED.getValue().longValue(),
					LoanTransactionStatusType.DECLINED.getCode(), "DECLINED");
			break;
		default:
			
			break;
		}
		
		return loanStatus;
	}
	

    public static LoanTransactionStatusType fromInt(final Integer transactionStatusType) {

        if (transactionStatusType == null) { return LoanTransactionStatusType.NOSTATUS; }

        LoanTransactionStatusType loanTransactionType = null;
        switch (transactionStatusType) {
            
            case 1:
                loanTransactionType = LoanTransactionStatusType.INPROGRESS;
            break;
            case 2:
                loanTransactionType = LoanTransactionStatusType.CLEAR;
            break;
            case 3:
                loanTransactionType = LoanTransactionStatusType.DECLINED;
            break;
           
            default:
                loanTransactionType = LoanTransactionStatusType.NOSTATUS;
            break;
        }
        return loanTransactionType;
    }
	

}
