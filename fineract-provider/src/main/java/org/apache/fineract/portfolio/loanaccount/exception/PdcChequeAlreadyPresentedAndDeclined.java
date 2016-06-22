package org.apache.fineract.portfolio.loanaccount.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class PdcChequeAlreadyPresentedAndDeclined extends AbstractPlatformResourceNotFoundException {

	public PdcChequeAlreadyPresentedAndDeclined(final String chequeNo) {
		super("error.msg.paymentinventorypdc.cheque.already.declined.for.current.repayment", "PDC cheque: " + chequeNo + " has been declined already and cant be used again ",
				chequeNo);
	}
	

}
