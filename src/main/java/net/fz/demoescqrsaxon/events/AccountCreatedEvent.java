package net.fz.demoescqrsaxon.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.fz.demoescqrsaxon.enums.AccountStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Getter
@AllArgsConstructor

public class AccountCreatedEvent {
    @TargetAggregateIdentifier
    private String accountId;
    private double initialBalance;

    private AccountStatus status;

    private String currency;
}
