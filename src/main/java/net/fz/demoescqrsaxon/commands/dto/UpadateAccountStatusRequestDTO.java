package net.fz.demoescqrsaxon.commands.dto;

import net.fz.demoescqrsaxon.enums.AccountStatus;

public record UpadateAccountStatusRequestDTO (
        String accountId, AccountStatus status){
}
