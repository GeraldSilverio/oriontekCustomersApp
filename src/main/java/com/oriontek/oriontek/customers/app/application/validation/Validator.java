package com.oriontek.oriontek.customers.app.application.validation;

import com.oriontek.oriontek.customers.app.application.result.Result;

public interface Validator<T> {
    
    Result<Void> validate(T input);
}