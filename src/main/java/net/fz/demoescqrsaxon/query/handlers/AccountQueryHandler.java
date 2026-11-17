package net.fz.demoescqrsaxon.query.handlers;

import net.fz.demoescqrsaxon.query.entites.Account;
import net.fz.demoescqrsaxon.query.queries.GetAllAccountsQuery;
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

}
