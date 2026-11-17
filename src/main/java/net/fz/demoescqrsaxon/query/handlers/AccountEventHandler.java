package net.fz.demoescqrsaxon.query.handlers;


import lombok.extern.slf4j.Slf4j;
import net.fz.demoescqrsaxon.enums.OperationType;
import net.fz.demoescqrsaxon.events.*;
import net.fz.demoescqrsaxon.query.entites.Account;
import net.fz.demoescqrsaxon.query.entites.AccountOperation;
import net.fz.demoescqrsaxon.query.repository.AccountRepository;
import net.fz.demoescqrsaxon.query.repository.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.springframework.stereotype.Component;

import java.sql.Date;


@Component
@Slf4j
public class AccountEventHandler {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    public AccountEventHandler(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage eventMessage) {
        log.info("query side Account Created Event");
        Account account = Account.builder()
                .id(event.getAccountId())
                .balance(event.getInitialBalance())
                .status(event.getStatus())
                .createdAt(eventMessage.getTimestamp())
                .build();

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event) {
        Account account = accountRepository.findById(event.getAccountId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountStatusUpdateEvent event) {
        Account account = accountRepository.findById(event.getAccountId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountDebitEvent event, EventMessage eventMessage) {
        Account account = accountRepository.findById(event.getAccountId()).get();

        AccountOperation accountOperation = AccountOperation.builder()
                .amount(event.getAmount())
                .date(eventMessage.getTimestamp())
                .type(OperationType.DEBIT)
                .currency(event.getCurrency())
                .account(account)
                .build();

        operationRepository.save(accountOperation);

        account.setBalance(account.getBalance() - accountOperation.getAmount());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountCreditEvent event, EventMessage eventMessage) {
        Account account = accountRepository.findById(event.getAccountId()).get();

        AccountOperation accountOperation = AccountOperation.builder()
                .amount(event.getAmount())
                .date(eventMessage.getTimestamp())
                .type(OperationType.CREDIT)
                .currency(event.getCurrency())
                .account(account)
                .build();

        operationRepository.save(accountOperation);

        account.setBalance(account.getBalance() + accountOperation.getAmount());
        accountRepository.save(account);
    }


}
