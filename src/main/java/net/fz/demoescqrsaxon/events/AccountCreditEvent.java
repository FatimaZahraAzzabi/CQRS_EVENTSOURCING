package net.fz.demoescqrsaxon.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.fz.demoescqrsaxon.enums.AccountStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Getter
@AllArgsConstructor

public class AccountCreditEvent {
    @TargetAggregateIdentifier
    private String accountId;
    private double amount;

    private String currency;
}
