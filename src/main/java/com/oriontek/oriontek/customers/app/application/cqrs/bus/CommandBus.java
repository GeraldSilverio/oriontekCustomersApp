package com.oriontek.oriontek.customers.app.application.cqrs.bus;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Command;
import com.oriontek.oriontek.customers.app.application.result.Result;

public interface CommandBus {

    <R, C extends Command<R>> Result<R> send(C command);
}
