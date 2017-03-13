package com.jpp.memories.api;

/**
 * The interface Api callback.
 *
 * @param <T> the type parameter
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */
public interface APICallback<T> {

    /**
     * On success
     *
     * @param response the response
     */
    void onSuccess(T response);

    /**
     * On error
     *
     * @param throwable the throwable
     */
    void onError(Throwable throwable);

    /**
     * On complete
     */
    void onComplete();
}
