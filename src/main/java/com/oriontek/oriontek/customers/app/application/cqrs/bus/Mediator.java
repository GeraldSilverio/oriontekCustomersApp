package com.oriontek.oriontek.customers.app.application.cqrs.bus;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Command;
import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.CommandHandler;
import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Query;
import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.QueryHandler;
import com.oriontek.oriontek.customers.app.application.result.Result;

@Component
public class Mediator implements CommandBus, QueryBus {

    private final ApplicationContext context;

    public Mediator(ApplicationContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R, C extends Command<R>> Result<R> send(C command) {

        String handlerName = command.getClass().getSimpleName() + "Handler";

        CommandHandler<C, R> handler =
                (CommandHandler<C, R>) context.getBean(handlerName);

        return handler.handle(command);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R, Q extends Query<R>> Result<R> ask(Q query) {

        String handlerName = query.getClass().getSimpleName() + "Handler";

        QueryHandler<Q, R> handler =
                (QueryHandler<Q, R>) context.getBean(handlerName);

        return handler.handle(query);
    }
}

