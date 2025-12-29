package com.oriontek.oriontek.customers.app.application.result;

public final class Result<T> {

    private final boolean success;
    private final T value;
    private final Error error;

    private Result(boolean success, T value, Error error) {
        this.success = success;
        this.value = value;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public T getValue() {
        return value;
    }

    public Error getError() {
        return error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(true, value, null);
    }

    public static Result<Void> success() {
        return new Result<>(true, null, null);
    }

    public static <T> Result<T> failure(Error error) {
        return new Result<>(false, null, error);
    }
}
