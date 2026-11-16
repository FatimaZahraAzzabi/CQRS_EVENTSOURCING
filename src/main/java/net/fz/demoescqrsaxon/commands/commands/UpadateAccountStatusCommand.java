package net.fz.demoescqrsaxon.commands.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.fz.demoescqrsaxon.enums.AccountStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class UpadateAccountStatusCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private AccountStatus status;
}
