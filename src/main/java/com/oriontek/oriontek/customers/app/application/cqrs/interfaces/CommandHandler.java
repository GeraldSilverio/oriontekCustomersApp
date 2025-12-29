package com.oriontek.oriontek.customers.app.application.cqrs.interfaces;

import com.oriontek.oriontek.customers.app.application.result.Result;

public interface CommandHandler<C extends Command<R>, R> {
    Result<R> handle(C command);
}

