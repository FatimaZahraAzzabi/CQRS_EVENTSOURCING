package net.fz.demoescqrsaxon.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class UpadateAccountStatusCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private double amount;

    private String currency;
}
