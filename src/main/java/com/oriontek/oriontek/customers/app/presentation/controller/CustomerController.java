package com.oriontek.oriontek.customers.app.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oriontek.oriontek.customers.app.application.dtos.cqrs.bus.CommandBus;
import com.oriontek.oriontek.customers.app.application.dtos.cqrs.bus.QueryBus;
import com.oriontek.oriontek.customers.app.application.dtos.features.customers.command.CreateCustomerCommand;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public CustomerController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCustomerCommand command) {
        commandBus.dispatch(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
