package net.fz.demoescqrsaxon.query.handlers;

import net.fz.demoescqrsaxon.query.dtos.AccountStatementResponseDTO;
import net.fz.demoescqrsaxon.query.entites.Account;
import net.fz.demoescqrsaxon.query.entites.AccountOperation;
import net.fz.demoescqrsaxon.query.queries.GetAccountStatementQuery;
import net.fz.demoescqrsaxon.query.queries.GetAllAccountsQuery;
import net.fz.demoescqrsaxon.query.queries.WatchEventQuery;
import net.fz.demoescqrsaxon.query.repository.AccountRepository;
import net.fz.demoescqrsaxon.query.repository.OperationRepository;
import org.axonframework.queryhandling.QueryHandler;

import java.util.List;

public class AccountQueryHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    public AccountQueryHandler(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query) {
        return accountRepository.findAll();
    }

    @QueryHandler
    public AccountStatementResponseDTO on(GetAccountStatementQuery query) {
        Account account = accountRepository.findById(query.getAccountId()).get();
        List<AccountOperation> operations = operationRepository.findByAccountId(query.getAccountId());
        return new AccountStatementResponseDTO(account, operations);
    }


    public AccountOperation on(WatchEventQuery query) {
        return AccountOperation.builder().build();
    }

}
