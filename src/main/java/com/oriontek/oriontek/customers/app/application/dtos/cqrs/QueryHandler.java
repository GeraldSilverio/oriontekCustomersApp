package com.oriontek.oriontek.customers.app.application.dtos.cqrs;

public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
}
