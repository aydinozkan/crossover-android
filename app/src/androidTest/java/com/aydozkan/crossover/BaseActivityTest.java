package com.aydozkan.crossover;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.aydozkan.crossover.gui.activity.BaseActivity;
import com.aydozkan.crossover.utility.ConnectivityChangeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BaseActivityTest extends ActivityTestRule<BaseActivity> {

    @Rule
    public final ActivityTestRule<BaseActivity> mActivityRule = new ActivityTestRule<>(
            BaseActivity.class);

    public BaseActivityTest() {
        super(BaseActivity.class);
    }

    @Test
    public void registerEventBus() throws Exception {

        EventBus.getDefault().register(this);

        assertTrue(EventBus.getDefault().isRegistered(this));
    }

    @Test
    public void unRegisterEventBusOnPause() throws Exception {

        EventBus.getDefault().unregister(this);

        assertTrue(!EventBus.getDefault().isRegistered(this));
    }

    @Test
    public void hideActionBar() throws Exception {

        if (mActivityRule.getActivity().getSupportActionBar() != null)
            mActivityRule.getActivity().getSupportActionBar().hide();

        assertTrue(!mActivityRule.getActivity().getSupportActionBar().isShowing());
    }

    @Subscribe
    public void onEventMainThread(ConnectivityChangeEvent event) {
        Log.d(mActivityRule.getActivity().getClass().getName(), event.toString());
    }
}