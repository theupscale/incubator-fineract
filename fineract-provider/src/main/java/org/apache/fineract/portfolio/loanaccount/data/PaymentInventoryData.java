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

import java.util.Collection;

import org.apache.fineract.infrastructure.core.data.EnumOptionData;
import org.apache.fineract.portfolio.loanaccount.loanschedule.data.LoanScheduleData;

public class PaymentInventoryData {

	private final Long id;

	private final Integer periods;

	private final boolean isDirectDebitActive;

	private final Long loanId;

	private final Collection<PaymentInventoryPdcData> paymentInventoryPdc;

	private final LoanScheduleData repayment;

	private final PaymentInventoryPdcData options;

	private EnumOptionData pdcType;

	private final boolean isSeriesCheques;

	private final boolean isChequesDispatched;

	public PaymentInventoryData defaults(final Long id, final Integer periods, boolean isDirectDebitActive) {
		return defaults(id, periods, isDirectDebitActive);
	}

	public PaymentInventoryData(final Long id, final Integer periods, final boolean isDirectDebitActive,
			final Long loanid, final Collection<PaymentInventoryPdcData> paymentInventoryPdcData,
			final LoanScheduleData repayment, final PaymentInventoryPdcData options, final EnumOptionData pdcType,
			final boolean isSeriesCheques, final boolean isChequesDispatched) {
		this.id = id;
		this.periods = periods;
		this.isDirectDebitActive = isDirectDebitActive;
		this.loanId = loanid;
		this.paymentInventoryPdc = paymentInventoryPdcData;
		this.repayment = repayment;
		this.options = options;
		this.pdcType = pdcType;
		this.isSeriesCheques = isSeriesCheques;
		this.isChequesDispatched = isChequesDispatched;
	}

	public static PaymentInventoryData template(final Integer period, final LoanScheduleData repayment,
			final PaymentInventoryPdcData options) {
		return new PaymentInventoryData(null, null, false, null, null, repayment, options, null, false, false);
	}

	public static PaymentInventoryData template(final LoanScheduleData repayment,
			final PaymentInventoryPdcData options) {
		return template(1, repayment, options);
	}

	public PaymentInventoryData(PaymentInventoryData paymentInventoryData,
			Collection<PaymentInventoryPdcData> pdcInventoryData) {
		this.id = paymentInventoryData.id;
		this.loanId = paymentInventoryData.loanId;
		this.periods = paymentInventoryData.periods;
		this.isDirectDebitActive = paymentInventoryData.isDirectDebitActive;
		this.paymentInventoryPdc = pdcInventoryData;
		this.repayment = null;
		this.options = null;
		this.pdcType = paymentInventoryData.pdcType;
		this.isSeriesCheques = paymentInventoryData.isSeriesCheques;
		this.isChequesDispatched = paymentInventoryData.isChequesDispatched;

	}

	public Long getId() {
		return this.id;
	}

	public boolean getIsDirectDebitActive() {
		return this.isDirectDebitActive;
	}

	public boolean getIsSeriesCheques() {
		return this.isSeriesCheques;
	}

	public boolean getisChequesDispatched() {
		return this.isChequesDispatched;
	}

	public EnumOptionData getPdcType() {
		return this.pdcType;
	}

	public Collection<PaymentInventoryPdcData> getPaymentInventoryPdcData() {
		return this.paymentInventoryPdc;
	}
}
