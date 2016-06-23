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

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "m_payment_inventory_pdc")
public class PaymentInventoryPdc extends AbstractPersistable<Long>{
	 
	 	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  @ManyToOne(optional = false)
	  @JoinColumn(name = "payment_id")
	  private PaymentInventory paymentInventory;
	
	  @Column(name = "period", nullable = true)
	  private Integer period;
	 
	  @Temporal(TemporalType.DATE)
	  @Column(name = "date")
	  private Date date;
	  
	  @Column(name = "amount", scale = 6, precision = 19, nullable = false)
	  private BigDecimal amount;
	  
	  @Temporal(TemporalType.DATE)
	  @Column(name = "cheque_date" )
	  private Date chequeDate;
	  
	  @Column(name = "cheque_no", nullable = false)
	  private Long chequeno;
	
	  @Column(name = "name_of_bank", nullable = false)
	  private String nameOfBank;
		
	  @Column(name = "ifsc_code", nullable = false)
	  private String ifscCode;
		
	  @Column(name = "present_type_of", nullable = false)
	  private Integer presentationStatus;
		
	  @Column(name = "make_presentation", nullable = false)
	  private boolean makePresentation;
	  
	  protected PaymentInventoryPdc(){
	    	this.date = null;
	    	this.presentationStatus = null;
	    	this.amount = null;
	    	this.chequeDate = null;
	    	this.chequeno = null;
	    	this.ifscCode = null;
	    	this.period = null;
	    	this.nameOfBank = null;
	    	this.makePresentation = false;
	    	
	  }


	  public PaymentInventoryPdc(final Integer period, final LocalDate date, final BigDecimal amount, 
			  final LocalDate chequeDate, final Long chequeno, final String nameOfBank, final String ifscCode, 
			  final PdcPresentationEnumOption status, final boolean makePresentation){
		  this.period = period;
		  this.amount = amount;
		  this.chequeDate = chequeDate.toDate();
		  this.chequeno = chequeno;
		  this.date = date.toDate();
		  this.ifscCode = ifscCode;
		  this.nameOfBank = nameOfBank;
		  this.presentationStatus = status.getValue();
		  this.makePresentation = makePresentation;
	
	  }
	  
	  public PaymentInventoryPdc(final PaymentInventory payment,final Integer period, final LocalDate date, final BigDecimal amount, 
			  final LocalDate chequeDate, final Long chequeno, final String nameOfBank, final String ifscCode, 
			  final PdcPresentationEnumOption status, final boolean makePresentation){
		  this.paymentInventory = payment;
		  this.period = period;
		  this.date = date.toDate();
		  this.amount = amount;
		  this.chequeDate = chequeDate.toDate();
		  this.chequeno = chequeno;
		  this.nameOfBank = nameOfBank;
		  this.ifscCode = ifscCode;
		  this.presentationStatus = status.getValue();
		  this.makePresentation = makePresentation;
	  }




	public static PaymentInventoryPdc createNew(final PaymentInventory payment,final Integer period, final LocalDate date, final BigDecimal amount,
			final LocalDate chequeDate, final Long chequeno, final String nameOfBank, final String ifscCode,
			final Integer presentationStatus, final boolean makePresentation) {
		
		final PdcPresentationEnumOption status = PdcPresentationEnumOption.fromInt(presentationStatus);
		return new PaymentInventoryPdc(payment,period, date, amount, chequeDate, chequeno, nameOfBank, ifscCode, status, makePresentation);
	}
	
	
	public Integer getPeriod() {
		return period;
	}


	public void setPeriod(Integer period) {
		this.period = period;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public Date getChequeDate() {
		return chequeDate;
	}


	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}


	public String getChequeno() {
		return chequeno.toString();
	}


	public void setChequeno(Long chequeno) {
		this.chequeno = chequeno;
	}


	public String getNameOfBank() {
		return nameOfBank;
	}


	public void setNameOfBank(String nameOfBank) {
		this.nameOfBank = nameOfBank;
	}
	
	public void setPresentationStatus(Integer presentationStatus){
		this.presentationStatus = presentationStatus;
		
	}

	public String getIfscCode() {
		return ifscCode;
	}


	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}


	public boolean isMakePresentation() {
		return makePresentation;
	}


	public void setMakePresentation(boolean makePresentation) {
		this.makePresentation = makePresentation;
	}


	public Integer getPresentationStatus() {
		return presentationStatus;
	}

	public Integer getPresentation(){
		return this.presentationStatus;
	}
}
