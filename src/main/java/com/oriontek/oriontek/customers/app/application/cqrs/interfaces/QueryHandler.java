package com.oriontek.oriontek.customers.app.application.cqrs.interfaces;

import com.oriontek.oriontek.customers.app.application.result.Result;

public interface QueryHandler<Q extends Query<R>, R> {
    Result<R> handle(Q query);
}
