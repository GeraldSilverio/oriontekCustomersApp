package com.oriontek.oriontek.customers.app.application.cqrs.bus;

import com.oriontek.oriontek.customers.app.application.cqrs.interfaces.Query;
import com.oriontek.oriontek.customers.app.application.result.Result;

public interface QueryBus {

    <R, Q extends Query<R>> Result<R> ask(Q query);
}


