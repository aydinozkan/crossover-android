package com.aydozkan.crossover.gui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.aydozkan.crossover.R;
import com.aydozkan.crossover.utility.ConnectivityChangeEvent;
import com.aydozkan.crossover.view.BaseView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * All activities should be extended from BaseActivity to handle common events.
 * <p>
 * Implements BaseView to handle onBeforeRequest and onAfterRequest callBack methods
 * if related activity makes a service call.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    final String TAG = getClass().getName();
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide ActionBar
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        EventBus.getDefault().register(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading));

        //Make app more secure.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Show Progress Dialog before Service Call
     */
    @Override
    public void onBeforeRequest() {
        if (mProgressDialog != null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    /**
     * Dismiss Progress Dialog after Service Call
     */
    @Override
    public void onAfterRequest() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    /**
     * Show ServiceError Dialog when service error occurs
     *
     * @param errorMessage shown ServiceError Message on UI
     */
    @Override
    public void onError(String errorMessage) {
        if (errorMessage != null && !errorMessage.isEmpty())
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    /**
     * Prompts an AlertDialog before logout.
     */
    void logoutAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.sign_out))
                .setMessage(getString(R.string.prompt_sign_out))
                .setPositiveButton(getString(R.string.action_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                        finish();
                    }

                })
                .setNegativeButton(getString(R.string.action_no), null)
                .show();
    }

    /**
     * Prompts an AlertDialog if network connection misses
     */
    private void networkAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.warning))
                .setMessage(getString(R.string.network_error))
                .setPositiveButton(getString(R.string.action_ok), null)
                .show();
    }

    /**
     * Called when the network connectivity has changed during runtime
     *
     * @param event ConnectivityChangeEvent
     */
    @Subscribe
    public void onEventMainThread(final ConnectivityChangeEvent event) {
        if (event != null) {
            switch (event.state) {
                case CONNECTED:
                    break;
                case CONNECTING:
                    break;
                case DISCONNECTED:
                    // Show Network Connection Missing Dialog
                    if (!this.isFinishing())
                        networkAlertDialog();
                    break;
            }
        }
    }
}
