/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.fineract.portfolio.loanaccount.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.data.EnumOptionData;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "m_payment_inventory", uniqueConstraints = { @UniqueConstraint(columnNames = { "loan_id" }, name = "loan_id_unique")})
public class PaymentInventory extends AbstractPersistable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
    @JoinColumn(name = "loan_id", referencedColumnName = "id", nullable = false)
    private Loan loan;
	
	@Column(name = "periods", nullable = false)
	private Integer periods;
	
	@Column(name = "is_directDebitActive", nullable = false)
    private boolean isDirectDebitactive;
	
	@LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy(value = "period")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentInventory", orphanRemoval = true)
    private List<PaymentInventoryPdc> paymentInventoryPdc = new ArrayList<>();
	
	@Column(name = "pdc_type", nullable = false)
	private Integer pdcType;
	
	@Column(name = "is_SeriesCheques", nullable = false)
	private boolean isSeriesCheques;
	
	@Column(name = "is_chequesDispatched", nullable = false)
	private boolean isChequesDispatched;

	
	
	public PaymentInventory(){
		this.loan = null;
		this.periods = null;
		this.isDirectDebitactive = false;
		this.paymentInventoryPdc = null;
		this.isChequesDispatched = false;
		this.isSeriesCheques = false;
		this.pdcType = null;
	}
	
	 public static PaymentInventory createNewFromJson(final Loan loan, final JsonCommand command) {
	        final Integer periods = loan.getLoanRepaymentScheduleDetail().getNumberOfRepayments();
	        final boolean isDirectDebitActive = command.booleanPrimitiveValueOfParameterNamed("isDirectDebitActive");
	        final Integer pdcType = command.integerValueOfParameterNamed("pdcType");
	        final boolean isSeriesCheques = command.booleanPrimitiveValueOfParameterNamed("isSeriesCheques");
	        final boolean isChequesDispatched = command.booleanPrimitiveValueOfParameterNamed("isChequesDispatched");
	        return createNewFromJson(loan,command, periods, isDirectDebitActive, pdcType, isSeriesCheques, isChequesDispatched);
	    }
	 
	 public static PaymentInventory createNewFromJson(final Loan loan, final JsonCommand command, final Integer periods, final boolean isDirectDebitActive,
			 final Integer pdcTypeValue, final boolean isSeriesCheques, final boolean isChequesDispatched){
		 
		 final PdcTypeEnumOption pdcType = PdcTypeEnumOption.fromInt(pdcTypeValue);
		 return new PaymentInventory(loan, periods, isDirectDebitActive, pdcType, isSeriesCheques, isChequesDispatched);
	 }
	 
	
	public PaymentInventory(final Loan loan, final Integer periods, final boolean isDirectDebitActive, final PdcTypeEnumOption pdcType,
			final boolean isSeriesCheques, final boolean isChequesDispatched){
		this.loan = loan;
		this.periods = periods;
		this.isDirectDebitactive = isDirectDebitActive;
		this.isChequesDispatched = isChequesDispatched;
		this.isSeriesCheques = isSeriesCheques;
		this.pdcType = pdcType.getValue();
	}
	

	public Long getPaymentInventoryId(){
		return this.getId();
	}
	
	public boolean isDirectDebitActive(){
		return this.isDirectDebitactive;
	}
	
	public void setPdcType(final Integer pdcTypeValue){
		this.pdcType = pdcTypeValue;
	}
	
	public Integer getPdcType(){
		return this.pdcType;
	}
	
	public boolean isSeriesCheques(){
		return this.isSeriesCheques;
	}
	
	public boolean isChequesDispatched(){
		return this.isChequesDispatched;
	}
	
	public List<PaymentInventoryPdc> getPaymentInventoryPdc(){
		 return this.paymentInventoryPdc;
	}
}
