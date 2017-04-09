package com.aydozkan.crossover.view;

/**
 * All Views should be extended from BaseView to handle common Callbacks.
 * <p>
 * BaseView is being implemented by BaseActivity
 */
public interface BaseView {
    /**
     * Is called before a service call to show Progress Dialog.
     */
    void onBeforeRequest();

    /**
     * Is called after a service call to hide Progress Dialog.
     */
    void onAfterRequest();

    /**
     * Is called when a service returns error.
     *
     * @param errorMessage related error message
     */
    void onError(String errorMessage);
}
