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

package org.apache.fineract.portfolio.loanaccount.data;

import java.math.BigDecimal;
import java.util.List;

import org.apache.fineract.infrastructure.core.data.EnumOptionData;
import java.util.Collection;

import org.apache.fineract.portfolio.loanaccount.domain.LoanRepaymentScheduleInstallment;
import org.joda.time.LocalDate;

public class PaymentInventoryPdcData {

		private final Long id;
		
		private final Integer period;
		
		private final LocalDate date;
		
		private final BigDecimal amount;
		
		private final LocalDate chequeDate;
		
		private final Long chequeno;
		
		private final String nameOfBank;
		
		private final String ifscCode;
		
		private EnumOptionData presentationStatus;
		
		private boolean makePresentation;
		
		private List<EnumOptionData> presentationType;
		
		public static PaymentInventoryPdcData template(final List<EnumOptionData> presentationType){
			
			return new PaymentInventoryPdcData(null, null, null, null, null, null, null, null, null, false, presentationType);
			
		}
		
		public static PaymentInventoryPdcData instance(final Long id, final Integer period,final LocalDate date, final BigDecimal amount, final LocalDate chequeDate,
				final Long chequeno, final String nameOfBank, final String ifscCode, final EnumOptionData presentationStatus, 
				final boolean makePresentation){
			
			final List<EnumOptionData> presentationType = null;
			
			return new PaymentInventoryPdcData(id, period, date, amount, chequeDate, chequeno, nameOfBank, ifscCode, presentationStatus, makePresentation, presentationType);
			
		}

		private  PaymentInventoryPdcData(final Long id, final Integer period,final LocalDate date, final BigDecimal amount, final LocalDate chequeDate,
				final Long chequeno, final String nameOfBank, final String ifscCode, final EnumOptionData presentationStatus, final boolean makePresentation,final List<EnumOptionData> presentationType ){
			this.period = period;
			this.id = id;
			this.date = date;
			this.amount = amount;
			this.chequeDate = chequeDate;
			this.chequeno = chequeno;
			this.nameOfBank = nameOfBank;
			this.ifscCode = ifscCode;
			this.presentationStatus = presentationStatus;
			this.makePresentation = makePresentation;
			this.presentationType = presentationType;
		}
		
		
		public Integer getPeriod() {
			return period;
		}
		
		
		public Long getId(){
			return this.id;
		}

		public LocalDate getDate() {
			return date;
		}


		public LocalDate getChequeDate() {
			return chequeDate;
		}


		public String getNameOfBank() {
			return nameOfBank;
		}


		public String getIfscCode() {
			return ifscCode;
		}


		public EnumOptionData getPresentationStatus() {
			return presentationStatus;
		}


		public boolean isMakePresentation() {
			return makePresentation;
		}


		public BigDecimal getAmount() {
			return amount;
		}

		public Long getChequeno() {
			return chequeno;
		}
		
		public void setPresentationStatus(EnumOptionData presentationStatus) {
			this.presentationStatus = presentationStatus;
		}

		public void setMakePresentation(boolean makePresentation) {
			this.makePresentation = makePresentation;
		}

		public void setPresentationType(List<EnumOptionData> presentationType) {
			this.presentationType = presentationType;
		}

		public List<EnumOptionData> getPresentationType() {
			return presentationType;
		}
		
		

		

}


