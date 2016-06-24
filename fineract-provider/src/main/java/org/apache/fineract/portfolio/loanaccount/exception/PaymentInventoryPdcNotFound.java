package org.apache.fineract.portfolio.loanaccount.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class PaymentInventoryPdcNotFound extends AbstractPlatformResourceNotFoundException {
	
	public PaymentInventoryPdcNotFound(final Long inventoryId){
		super("error.msg.inventoryId.not.found", "PDC data not found with this" + " " +   inventoryId +  " " + "which means no PDC Data " + " " + inventoryId );
	}

}
