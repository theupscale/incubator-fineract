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

package org.apache.fineract.portfolio.loanaccount.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.fineract.infrastructure.core.data.EnumOptionData;
import org.apache.fineract.infrastructure.core.domain.JdbcSupport;
import org.apache.fineract.infrastructure.core.service.RoutingDataSource;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.apache.fineract.portfolio.common.service.DropdownReadPlatformService;
import org.apache.fineract.portfolio.loanaccount.data.PaymentInventoryData;
import org.apache.fineract.portfolio.loanaccount.data.PaymentInventoryPdcData;
import org.apache.fineract.portfolio.loanaccount.exception.PaymentInventoryNotFound;
import org.apache.fineract.portfolio.loanaccount.exception.PaymentInventoryPdcNotFound;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentInventoryReadPlatformServiceImpl implements PaymentInventoryReadPlatformService {

	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final PaymentInventoryService paymentInvnetory;

	@Autowired
	public PaymentInventoryReadPlatformServiceImpl(final PlatformSecurityContext context,
			final RoutingDataSource dataSource, final DropdownReadPlatformService dropdownReadPlatformService,
			final LoanAssembler loanAssembler, final PaymentInventoryService paymentInventory) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.paymentInvnetory = paymentInventory;
	}

	private static final class PaymentInventoryMapper implements RowMapper<PaymentInventoryData> {

		public String schema() {
			return "pi.id as id, pi.loan_id as loanId, pi.is_directDebitActive as directDebit, "
					+ "pi.periods as periods , pi.pdc_Type as pdcType, pi.is_SeriesCheques as SeriesCheques, "
					+ "pi.is_ChequesDispatched as ChequesDispatched from m_payment_inventory pi "
					+ "join m_loan l on l.id = pi.loan_id ";
		}

		@Override
		public PaymentInventoryData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final Long loanId = rs.getLong("loanId");
			final boolean isdirectDebitActive = rs.getBoolean("directDebit");
			final int periods = rs.getInt("periods");
			final boolean isChequesDisbursed = rs.getBoolean("ChequesDispatched");
			final boolean isSeriesCheques = rs.getBoolean("SeriesCheques");
			final int pdcTypeValue = rs.getInt("pdcType");
			final EnumOptionData pdcType = PdcTypeEnumerations.pdcType(pdcTypeValue);

			return new PaymentInventoryData(id, periods, isdirectDebitActive, loanId, null, null, null, pdcType,
					isSeriesCheques, isChequesDisbursed);
		}
	}

	@Override
	public PaymentInventoryData retrieveBasedOnInventoryId(final Long inventoryId) {
		this.context.authenticatedUser();

		final PaymentInventoryMapper rm = new PaymentInventoryMapper();

		final String sql = "select " + rm.schema() + " where pi.id=?";

		return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { inventoryId });

	}

	@Override
	public PaymentInventoryData retrieveBasedOnLoanId(final Long loanId) {

		try {
			this.context.authenticatedUser();
			final PaymentInventoryMapper rm = new PaymentInventoryMapper();

			final String sql = "select " + rm.schema() + " where pi.loan_id=?";

			return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { loanId });

		} catch (final EmptyResultDataAccessException e) {

			throw new PaymentInventoryNotFound(loanId);
		}
	}

	@Override
	public PaymentInventoryData retrievePaymentInventory(final Long loanId, final Long inventoryId) {

		try {
			this.context.authenticatedUser();
			final PaymentInventoryMapper rm = new PaymentInventoryMapper();

			final String sql = "select " + rm.schema() + " where pi.loan_id=? and pi.id=?";

			return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { loanId, inventoryId });

		} catch (final EmptyResultDataAccessException e) {

			throw new PaymentInventoryNotFound(loanId);
		}

	}

	@Override
	public PaymentInventoryPdcData retrieveEnumOptions() {

		final List<EnumOptionData> allowedOptions = this.paymentInvnetory.retriveEnumOptions();

		return PaymentInventoryPdcData.template(allowedOptions);

	}

	private static final class PaymentInventoryPdcMapper implements RowMapper<PaymentInventoryPdcData> {

		public String schema() {
			return "pdc.id as id, pdc.period as pdcPeriod, " + "pdc.date as Date, " + "pdc.amount as Amount, "
					+ "pdc.cheque_date as chequeDate, " + "pdc.cheque_no as chequeNo, "
					+ "pdc.name_of_bank as bankName, " + "pdc.ifsc_code as ifscCode, "
					+ "pdc.present_type_of as presentationStatus, " + "pdc.make_presentation as makePresentation "
					+ "from m_payment_inventory_pdc pdc " + "join m_payment_inventory pi on pi.id = pdc.payment_id ";
		}

		@Override
		public PaymentInventoryPdcData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final int pdcPeriod = rs.getInt("pdcPeriod");
			final BigDecimal amount = rs.getBigDecimal("Amount");
			LocalDate date = JdbcSupport.getLocalDate(rs, "Date");
			LocalDate chequeDate = JdbcSupport.getLocalDate(rs, "chequeDate");
			final Long chequeNo = rs.getLong("chequeNo");
			final String bankName = rs.getString("bankName");
			final String ifscCode = rs.getString("ifscCode");
			final int presentationStatus = rs.getInt("presentationStatus");
			final EnumOptionData presentationType = PaymentInventoryEnumerations.presentationTime(presentationStatus);
			final boolean makePresentation = rs.getBoolean("makePresentation");

			return PaymentInventoryPdcData.instance(id, pdcPeriod, date, amount, chequeDate, chequeNo, bankName,
					ifscCode, presentationType, makePresentation);
		}

	}

	@Override
	public Collection<PaymentInventoryPdcData> retrievePdcPaymentDetails(Long inventoryId,
			boolean onlyPdcPendingDetails) {
		final PaymentInventoryPdcMapper rm = new PaymentInventoryPdcMapper();
		String sql = "select " + rm.schema() + "where pdc.payment_id=? ";
		/*
		 * if (onlyPdcPendingDetails) { sql = sql +
		 * "and pdc.waived =0 and lic.is_paid_derived=0"; }
		 */
		return this.jdbcTemplate.query(sql, rm, new Object[] { inventoryId });
	}

	@Override
	public Collection<PaymentInventoryPdcData> retrievePdcInventory(Long inventoryId) {
		final PaymentInventoryPdcMapper rm = new PaymentInventoryPdcMapper();
		String sql = "select " + rm.schema() + "where pdc.payment_id=? ";
		return this.jdbcTemplate.query(sql, rm, new Object[] { inventoryId });
	}

	@Override
	public PaymentInventoryPdcData retrieveByInstallment(Integer installmentNumber, Long inventoryId) {

		try {
			final PaymentInventoryPdcMapper rm = new PaymentInventoryPdcMapper();
			String sql = "select " + rm.schema() + "where pdc.payment_id=? AND pdc.period=?";
			return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { inventoryId, installmentNumber });

		} catch (final EmptyResultDataAccessException e) {
			throw new PaymentInventoryPdcNotFound(inventoryId);
		}
	}

	@Override
	public PaymentInventoryPdcData retrieveByCheque(String chequeNo, Long inventoryId) {

		try {
			final PaymentInventoryPdcMapper rm = new PaymentInventoryPdcMapper();
			String sql = "select " + rm.schema() + "where pdc.payment_id=? AND pdc.cheque_no=?";
			return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { inventoryId, chequeNo });

		} catch (final EmptyResultDataAccessException e) {
			throw new PaymentInventoryPdcNotFound(inventoryId);
		}

	}

}
