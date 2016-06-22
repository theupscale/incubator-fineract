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
package org.apache.fineract.portfolio.client.command;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;

import org.apache.fineract.infrastructure.core.data.ApiParameterError;
import org.apache.fineract.infrastructure.core.data.DataValidatorBuilder;
import org.apache.fineract.infrastructure.core.exception.PlatformApiDataValidationException;
import org.apache.fineract.portfolio.client.domain.ClientIdentifierStatus;

/**
 * Immutable command for creating or updating details of a client identifier.
 */
public class ClientIdentifierCommand {

    private final Long documentTypeId;
    private final Long proofTypeId;
    private final String documentKey;
    private final LocalDate validity;
    private final boolean isLifeTime;
    private final String locale;
    private final String dateFormat;
    private final String description;
	private final String status;

    public ClientIdentifierCommand(final Long documentTypeId, final Long proofTypeId, final String documentKey, final LocalDate validity, final boolean isLifeTime, 
                        final String locale, final String dateFormat, final String statusString, final String description) {
        this.documentTypeId = documentTypeId;
        this.proofTypeId = proofTypeId;
        this.documentKey = documentKey;
        this.validity = validity;
        this.isLifeTime = isLifeTime;
        this.locale = locale;
        this.dateFormat = dateFormat;
        this.status = statusString;
        this.description = description;
    }

    public Long getDocumentTypeId() {
        return this.documentTypeId;
    }

    public String getDocumentKey() {
        return this.documentKey;
    }

    public String getDescription() {
        return this.description;
    }
    
    public LocalDate getValidity() {
		return this.validity;
	}
    public boolean getisLifeTime() { 
    	return this.isLifeTime;
    }
    
    public String getlocale() {
    	return this.locale;
    }
    
    public Long getproofTypeId() {
    	return this.proofTypeId;
    }

    public void validateForCreate() {
        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();

        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("clientIdentifier");

        baseDataValidator.reset().parameter("documentTypeId").value(this.documentTypeId).notNull().integerGreaterThanZero();
        baseDataValidator.reset().parameter("documentKey").value(this.documentKey).notBlank();
        
        baseDataValidator.reset().parameter("validity").value(this.validity).validateDateAfter(LocalDate.now().plusMonths(1)).ignoreIfNull();
		baseDataValidator.reset().parameter("isLifeTime").value(this.isLifeTime).ignoreIfNull();

        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }

    public void validateForUpdate() {
        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();

        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("clientIdentifier");

        baseDataValidator.reset().parameter("documentKey").value(this.documentKey).ignoreIfNull().notBlank();

        // FIXME - KW - add in validation
        // if (command.isDocumentTypeChanged()) {
        // baseDataValidator.reset().parameter("documentTypeId").value(command.getDocumentTypeId()).notNull().integerGreaterThanZero();
        // }

        baseDataValidator.reset().anyOfNotNull(this.documentTypeId, this.documentKey);

        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }
}