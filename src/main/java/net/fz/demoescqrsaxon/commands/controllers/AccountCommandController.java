package net.fz.demoescqrsaxon.commands.controllers;


import net.fz.demoescqrsaxon.commands.commands.AddAccountCommand;
import net.fz.demoescqrsaxon.commands.commands.CreditAccountCommand;
import net.fz.demoescqrsaxon.commands.commands.DebitAccountCommand;
import net.fz.demoescqrsaxon.commands.dto.AddNewAccountRequestDTO;
import net.fz.demoescqrsaxon.commands.dto.CreditAccountRequestDTO;
import net.fz.demoescqrsaxon.commands.dto.DebitAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands")
public class AccountCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    //injection de dependance en usant le constructeur
    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }

    @PostMapping("/add")
    public CompletableFuture<String> addNewAccount(@RequestBody AddNewAccountRequestDTO request){
        CompletableFuture<String> response=commandGateway.send(new AddAccountCommand(
                UUID.randomUUID().toString(),
                request.initialBalance(),
                request.currency()

        ));

        return response;
    }

    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request){
        CompletableFuture<String> response=commandGateway.send(new CreditAccountCommand(
                request.accountId(),
                request.amount(),
                request.currency()

        ));

        return response;
    }

    @PostMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO request){
        CompletableFuture<String> response=commandGateway.send(new DebitAccountCommand(
                request.accountId(),
                request.amount(),
                request.currency()

        ));

        return response;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        return e.getMessage();
    }

    @GetMapping("/events/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
          return eventStore.readEvents(accountId).asStream();
    }


}
