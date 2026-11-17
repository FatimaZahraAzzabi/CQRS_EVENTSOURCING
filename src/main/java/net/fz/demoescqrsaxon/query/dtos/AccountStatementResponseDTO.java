package net.fz.demoescqrsaxon.query.dtos;

import net.fz.demoescqrsaxon.query.entites.Account;
import net.fz.demoescqrsaxon.query.entites.AccountOperation;

import java.util.List;

public record AccountStatementResponseDTO(
        Account account,
        List<AccountOperation> operations
) {
}
