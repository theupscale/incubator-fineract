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
package org.apache.fineract.portfolio.client.data;

import java.util.Collection;

import org.apache.fineract.infrastructure.codes.data.CodeValueData;
import org.apache.fineract.infrastructure.core.data.EnumOptionData;
import org.joda.time.LocalDate;

/**
 * Immutable data object represent client identity data.
 */
public class ClientIdentifierData {

    private final Long id;
    private final Long clientId;
    private final CodeValueData documentType;
    private final CodeValueData proofType;
    private final String documentKey;
    private final LocalDate validity;
    private final Boolean isLifeTime;
    private final String locale;
    private final String dateFormat;
    private final String description;
    private final String status;
    @SuppressWarnings("unused")
    private final Collection<CodeValueData> allowedDocumentTypes;
    private final Collection<CodeValueData> allowedProofTypes;

    public static ClientIdentifierData singleItem(final Long id, final Long clientId, final CodeValueData documentType, final CodeValueData proofType, 
                        final String documentKey, final LocalDate validity,final Boolean isLifeTime, final String locale, final String dateFormat, final String status, final String description) {
        return new ClientIdentifierData(id, clientId, documentType, proofType, documentKey, validity, isLifeTime, locale, dateFormat, description, status, null, null);
    }

    public static ClientIdentifierData template(final Collection<CodeValueData> codeValues, final Collection<CodeValueData> proofValues) {
        return new ClientIdentifierData(null, null, null, null, null, null, null, null, null, null, null, codeValues, proofValues);
    }

    public static ClientIdentifierData template(final ClientIdentifierData data, final Collection<CodeValueData> codeValues, final Collection<CodeValueData> proofValues) {
        return new ClientIdentifierData(data.id, data.clientId, data.documentType, data.proofType, data.documentKey, data.validity, data.isLifeTime, data.locale,
                data.dateFormat, data.description, data.status,  codeValues, proofValuesdata.id, data.clientId, data.documentType, data.documentKey, data.description, data.status,  codeValues);
    }

 public ClientIdentifierData(final Long id, final Long clientId, final CodeValueData documentType, final CodeValueData proofType, final String documentKey, final LocalDate validity,
                final Boolean isLifeTime, final String locale, final String dateFormat, final String description, final String status, final Collection<CodeValueData> allowedDocumentTypes,
                final Collection<CodeValueData> allowedProofTypes) {
        this.id = id;

        this.clientId = clientId;
        this.documentType = documentType;
        this.proofType = proofType;
        this.documentKey = documentKey;
        this.validity = validity;
        this.isLifeTime = isLifeTime;
        this.locale = locale;
        this.dateFormat = dateFormat;
        this.description = description;
        this.allowedDocumentTypes = allowedDocumentTypes;
        this.allowedProofTypes = allowedProofTypes;
        this.status = status;
    }
}