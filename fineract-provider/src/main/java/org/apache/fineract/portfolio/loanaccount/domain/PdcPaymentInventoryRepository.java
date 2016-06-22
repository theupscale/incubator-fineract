package org.apache.fineract.portfolio.loanaccount.domain;

import java.util.List;

import org.apache.fineract.portfolio.loanproduct.domain.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PdcPaymentInventoryRepository extends JpaRepository<PaymentInventoryPdc, Long>, JpaSpecificationExecutor<PaymentInventoryPdc> {
	/*@Query("delete from PaymentInventoryPdc pdcPayment where pdcPayment.payment_inventory_id = :inventoryId")
    List<PaymentInventoryPdc> retrievePdcByInventoryId(@Param("inventoryId") Long inventoryId);*/
}
