package com.aydozkan.crossover.network.http;

import android.net.NetworkInfo;
import android.util.Log;

import com.aydozkan.crossover.utility.ConnectivityChangeEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Custom CallBack to handle response.code and response.errorBody and send an appropriate Error Message.
 *
 * @param <T> Response Object of related Service
 */
public abstract class ResponseCallback<T> implements Callback<T> {

    private static final String TAG = ResponseCallback.class.getName();

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null) {
            if (response.body() != null) {
                onResponse(response);
            } else {
                try {
                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
                    onFailure(jsonObject.getString("message"));
                } catch (JSONException | IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof ConnectException) {
            EventBus.getDefault().post(new ConnectivityChangeEvent(NetworkInfo.State.DISCONNECTED));
            onFailure("");
        } else {
            onFailure(t.getMessage());
        }
    }

    public abstract void onResponse(Response<T> response);

    public abstract void onFailure(String errorMessage);
}