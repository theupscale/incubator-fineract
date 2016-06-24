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

import org.apache.fineract.infrastructure.core.data.EnumOptionData;
import org.apache.fineract.portfolio.loanaccount.domain.PdcPresentationEnumOption;

public class PaymentInventoryEnumerations {
	
	public static EnumOptionData presentationTime(final int d){
		return presentationTime(PdcPresentationEnumOption.fromInt(d));
	}

	public static EnumOptionData presentationTime(final PdcPresentationEnumOption pdcPresentation){
		EnumOptionData option = null;
		
		switch(pdcPresentation){
			
		case UNUSED:
			option = new EnumOptionData(PdcPresentationEnumOption.UNUSED.getValue().longValue(), PdcPresentationEnumOption.UNUSED.getCode(), "Unused");
			break;
		case PRESENTED:
			option = new EnumOptionData(PdcPresentationEnumOption.PRESENTED.getValue().longValue(), PdcPresentationEnumOption.PRESENTED.getCode(), "Presented");

		break;
		case PRESENTED_AND_CLEARED:
			option = new EnumOptionData(PdcPresentationEnumOption.PRESENTED_AND_CLEARED.getValue().longValue(), PdcPresentationEnumOption.PRESENTED_AND_CLEARED.getCode(), "Presented and Cleared");
			
			break;
		case PRESENTED_AND_DECLINED:
			option = new EnumOptionData(PdcPresentationEnumOption.PRESENTED_AND_DECLINED.getValue().longValue(), PdcPresentationEnumOption.PRESENTED_AND_DECLINED.getCode(), "Presented and Declined");
			break;
			
		default:
		
		option = new EnumOptionData(PdcPresentationEnumOption.INVALID.getValue().longValue(), PdcPresentationEnumOption.INVALID.getCode(), "Invalid");
		break;
		
		}
		return option;
	}
}
