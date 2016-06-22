package org.apache.fineract.portfolio.loanaccount.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class PaymentInventoryNotFound  extends AbstractPlatformResourceNotFoundException{
	
	public PaymentInventoryNotFound(final Long id){
		super("error.msg.paymentId.not.found", "PDC data not found with this" + " " +   id +  " " + "which means no paymentInventory associated with this loan" + " " + id );
	}

}
