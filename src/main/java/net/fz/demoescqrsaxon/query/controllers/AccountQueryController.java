package net.fz.demoescqrsaxon.query.controllers;


import net.fz.demoescqrsaxon.query.dtos.AccountStatementResponseDTO;
import net.fz.demoescqrsaxon.query.entites.Account;
import net.fz.demoescqrsaxon.query.entites.AccountOperation;
import net.fz.demoescqrsaxon.query.queries.GetAccountStatementQuery;
import net.fz.demoescqrsaxon.query.queries.GetAllAccountsQuery;
import net.fz.demoescqrsaxon.query.queries.WatchEventQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accounts")
public class AccountQueryController {

    private QueryGateway queryGateway;

    public AccountQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }


    @GetMapping("/all")
    public CompletableFuture<List<Account>> getAllAccounts() {
        CompletableFuture<List<Account>> response = queryGateway.query(
                new GetAllAccountsQuery(),
                ResponseTypes.multipleInstancesOf(Account.class)
        );
        return response;

    }


    @GetMapping("/accountStatement/{accountId}")
    public CompletableFuture<AccountStatementResponseDTO> getAccountStatement(@PathVariable String accountId){
        return queryGateway.query(new GetAccountStatementQuery(accountId),
                ResponseTypes.instanceOf(AccountStatementResponseDTO.class));
    }




}
