package com.aydozkan.crossover.view;

/**
 * All Views should be extended from BaseView to handle common Callbacks.
 * <p>
 * BaseView is being implemented by BaseActivity
 */
public interface BaseView {
    void onBeforeRequest();
    void onAfterRequest();
    void onError(String errorMessage);
}
