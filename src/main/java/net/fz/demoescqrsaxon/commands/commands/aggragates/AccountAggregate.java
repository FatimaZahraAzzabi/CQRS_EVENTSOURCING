package net.fz.demoescqrsaxon.commands.commands.aggragates;

import lombok.extern.slf4j.Slf4j;
import net.fz.demoescqrsaxon.commands.commands.AddAccountCommand;
import net.fz.demoescqrsaxon.commands.commands.CreditAccountCommand;
import net.fz.demoescqrsaxon.enums.AccountStatus;
import net.fz.demoescqrsaxon.events.AccountActivatedEvent;
import net.fz.demoescqrsaxon.events.AccountCreatedEvent;
import net.fz.demoescqrsaxon.events.AccountCreditEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAggregate {

    @AggregateIdentifier
    private String accountId;
    private double balance;
    private AccountStatus status;
   // pour Axon
    public AccountAggregate() {

    }

    // faire subscribe chez event bus et c'est la fct de decision
    @CommandHandler
    public AccountAggregate(AddAccountCommand command) {
        log.info("reception de cmd {}", command);
        if(command.getInitialBalance()<=0 )throw new IllegalArgumentException("balance must be positive");

        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                AccountStatus.CREATED,
                command.getCurrency()
        ));

        AggregateLifecycle.apply(new AccountActivatedEvent(
                command.getId(),
                AccountStatus.ACTIVATED
        ));




    }


    @CommandHandler
    public void handle(CreditAccountCommand command) {
        log.info("################ CreditAccountCommand Received ################");

        if (!status.equals(AccountStatus.ACTIVATED))
            throw new RuntimeException("The account is not activated");

        if (command.getAmount() <= 0)
            throw new IllegalArgumentException("Amount must be positive");

        AggregateLifecycle.apply(new AccountCreditEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }


    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("le traitemnt de cmd ");
        this.accountId = event.getAccountId();
        this.balance=event.getInitialBalance();
        this.status=event.getStatus();

    }

    @EventSourcingHandler
    public void on(AccountCreditEvent event) {
        log.info("################ AccountCreditedEvent occurred ################");
        this.accountId = event.getAccountId();
        this.balance = this.balance + event.getAmount();
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
        log.info("################ AccountCreditedEvent occurred ################");
        this.accountId = event.getAccountId();
        this.status = event.getStatus();
    }



}
