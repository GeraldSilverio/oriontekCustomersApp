package com.oriontek.oriontek.customers.app.application.dtos.cqrs.bus;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.oriontek.oriontek.customers.app.application.dtos.cqrs.Command;
import com.oriontek.oriontek.customers.app.application.dtos.cqrs.CommandHandler;
import com.oriontek.oriontek.customers.app.application.dtos.cqrs.Query;
import com.oriontek.oriontek.customers.app.application.dtos.cqrs.QueryHandler;

@Component
public class Mediator implements CommandBus, QueryBus {

    private final ApplicationContext context;

    public Mediator(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <C extends Command> void dispatch(C command) {
        String handlerName = command.getClass().getSimpleName() + "Handler";
        CommandHandler<C> handler =
                (CommandHandler<C>) context.getBean(handlerName);
        handler.handle(command);
    }

    @Override
    public <R, Q extends Query<R>> R dispatch(Q query) {
        String handlerName = query.getClass().getSimpleName() + "Handler";
        QueryHandler<Q, R> handler =
                (QueryHandler<Q, R>) context.getBean(handlerName);
        return handler.handle(query);
    }
}

