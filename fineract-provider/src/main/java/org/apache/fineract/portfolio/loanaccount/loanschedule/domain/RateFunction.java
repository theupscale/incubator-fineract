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
package org.apache.fineract.portfolio.loanaccount.loanschedule.domain;

import java.math.BigDecimal;

public class RateFunction {
	
	public final static BigDecimal rate(int paymentsPerYear,BigDecimal paymentAmount,Double presentValue, Double futureValue, Double dueEndOrBeginning,Double interest){
	    //If interest, futureValue, dueEndorBeginning was not set, set now
					paymentAmount= paymentAmount.negate();
					double pay = paymentAmount.doubleValue();
					
	                if(interest == null)
	                interest = 0.01;
	                
	                if (futureValue == null)
	                futureValue = 0.0;
	                
	                if (dueEndOrBeginning == null)
	                dueEndOrBeginning = 0.0;

	                float FINANCIAL_MAX_ITERATIONS = 128;//Bet accuracy with 128
	                double FINANCIAL_PRECISION = 0.0000001;//1.0e-8

	                double y;
					double y0, y1, x0, x1 = 0, f = 0, i = 0;
	                Double rate = interest;
	                if (Math.abs(rate) < FINANCIAL_PRECISION){
	                     y = presentValue * (1 + paymentsPerYear * rate) + pay * (1 + rate * dueEndOrBeginning) * paymentsPerYear + futureValue;
	                    }else{
	                        f = Math.exp(paymentsPerYear * Math.log(1 + rate));
	                        y = presentValue * f + pay * (1 / rate + dueEndOrBeginning) * (f - 1) + futureValue;
	                    }
	                y0 = presentValue + pay * paymentsPerYear + futureValue;
	                y1 = presentValue * f + pay * (1 / rate + dueEndOrBeginning) * (f - 1) + futureValue;

	        // find root by Newton secant method
	                i = x0 = 0.0;
	                x1 = rate;
	                while ((Math.abs(y0 - y1) > FINANCIAL_PRECISION)
	                        && (i < FINANCIAL_MAX_ITERATIONS)){
	                rate = (y1 * x0 - y0 * x1) / (y1 - y0);
	                x0 = x1;
	                x1 = rate;

	                if (Math.abs(rate) < FINANCIAL_PRECISION){
	                y = presentValue * (1 + paymentsPerYear * rate) + pay * (1 + rate * dueEndOrBeginning) * paymentsPerYear + futureValue;
	                }else{
	                f = Math.exp(paymentsPerYear * Math.log(1 + rate));
	                y = presentValue * f + pay * (1 / rate + dueEndOrBeginning) * (f - 1) + futureValue;
	            }

	                y0 = y1;
	                y1 = y;
	                ++i;
	        }
	                
	        return BigDecimal.valueOf(rate);
	    }

}
