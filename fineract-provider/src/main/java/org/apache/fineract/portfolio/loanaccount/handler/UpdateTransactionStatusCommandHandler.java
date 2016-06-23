package org.apache.fineract.portfolio.loanaccount.handler;

import org.apache.fineract.commands.annotation.CommandType;
import org.apache.fineract.commands.handler.NewCommandSourceHandler;
import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.portfolio.loanaccount.service.LoanWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import groovy.transform.AutoClone;
@Service
@CommandType(entity = "LOAN", action = "UPDATETRANSACTIONSTATUS")
public class UpdateTransactionStatusCommandHandler implements NewCommandSourceHandler{
	
	private final LoanWritePlatformService writePlatformService;
	
	@Autowired
	public UpdateTransactionStatusCommandHandler(final LoanWritePlatformService writePlatformService) {
		this.writePlatformService = writePlatformService;
	}

	@Transactional
	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
		return this.writePlatformService.updateTransactionStatus(command.getLoanId(), command.entityId(), command);
	}
}
