package net.fz.demoescqrsaxon.commands.controllers;


import net.fz.demoescqrsaxon.commands.commands.AddAccountCommand;
import net.fz.demoescqrsaxon.commands.dto.AddNewAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands")
public class AccountCommandController {

    private CommandGateway commandGateway;
    //injection de dependance en usant le constructeur
    public AccountCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
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

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        return e.getMessage();
    }


}
