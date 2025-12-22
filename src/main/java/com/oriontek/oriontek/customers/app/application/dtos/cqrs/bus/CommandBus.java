package com.oriontek.oriontek.customers.app.application.dtos.cqrs.bus;

import com.oriontek.oriontek.customers.app.application.dtos.cqrs.Command;

public interface CommandBus {
    <C extends Command> void dispatch(C command);
}
