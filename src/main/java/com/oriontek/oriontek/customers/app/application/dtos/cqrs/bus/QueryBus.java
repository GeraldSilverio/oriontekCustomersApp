package com.oriontek.oriontek.customers.app.application.dtos.cqrs.bus;

import com.oriontek.oriontek.customers.app.application.dtos.cqrs.Query;

public interface QueryBus {
    <R, Q extends Query<R>> R dispatch(Q query);
}
