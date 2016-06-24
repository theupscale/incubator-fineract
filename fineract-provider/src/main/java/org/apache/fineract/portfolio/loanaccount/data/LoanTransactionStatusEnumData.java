package org.apache.fineract.portfolio.loanaccount.data;

public class LoanTransactionStatusEnumData {

	private final Long id;
    private final String code;
    private final String value;

    private final boolean inProgress;
    private final boolean cleared;
    private final boolean bounced;
    
    public LoanTransactionStatusEnumData(final Long id, final String code, final String value) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.inProgress = Long.valueOf(1).equals(this.id);
        this.cleared = Long.valueOf(2).equals(this.id);
        this.bounced = Long.valueOf(3).equals(this.id);
        
    }

    public Long id() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Returns true if the transaction involves either a payment (disbursement)
     * or a receipt (repayments, repayments during disbursement etc)
     * 
     * @return
     */
    public boolean isInProgress() {
        return this.inProgress;
    }

    public boolean isCleared() {
        return this.cleared;
    }

    public boolean isBounced() {
        return this.bounced;
    }
}
