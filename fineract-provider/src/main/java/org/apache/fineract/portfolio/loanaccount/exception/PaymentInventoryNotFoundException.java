
package org.apache.fineract.portfolio.loanaccount.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformDomainRuleException;
import org.apache.fineract.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

/**
 * {@link AbstractPlatformDomainRuleException} thrown when Payment Inventory does not
 * exist.
 */
public class PaymentInventoryNotFoundException extends AbstractPlatformResourceNotFoundException{
	
	public PaymentInventoryNotFoundException(final Long id) {
        super("error.msg.paymentinventory.id.invalid", "Payment Inventory with identifier " + id + " does not exist", id);
    }

    public PaymentInventoryNotFoundException(final Long id, final Long loanId) {
        super("error.msg.paymentinventory.id.invalid.for.given.loan", "Payment Inventory with identifier " + id + " does not exist for loan " + loanId,
                id, loanId);
    }
}
