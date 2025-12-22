package com.oriontek.oriontek.customers.app.application.dtos.cqrs;

public interface CommandHandler<C extends Command> {
    void handle(C command);
}