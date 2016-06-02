package com.example.manoj.haptiktest.rest;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

public final class NetworkManager {

    private final NetworkHelper networkHelper;
    private final String pathUrl;
    private final int method;
    private final TypeToken<?> classTarget;

    public NetworkManager(Builder builder) {
        this.networkHelper = NetworkHelper.getInstance(Rest.getContext());
        this.pathUrl = builder.pathUrl;
        this.method = builder.method;
        this.classTarget = builder.targetType;
    }

    private void fromJsonObject(final RequestCallback requestCallback) {
        JsonObjectRequest request = new JsonObjectRequest(method, pathUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("manoj", jsonObject.toString());
                try {
                    Object t = new Gson().fromJson(jsonObject.toString(), classTarget.getType());
                    if (requestCallback != null)
                        requestCallback.onRequestSuccess(t);
                } catch (Exception e) {
                    if (requestCallback != null)
                        requestCallback.onRequestError(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (requestCallback != null) {
                    requestCallback.onRequestError(error.getMessage());
                }
            }
        });
        networkHelper.addToRequestQueue(request);
    }


    public void withCallback(RequestCallback callback) {
        fromJsonObject(callback);
    }

    public static class Builder implements INetworkManagerBuilder {
        private String pathUrl;
        private int method;
        private TypeToken<?> targetType;

        public Builder setMethod(int method) {
            this.method = method;
            return this;
        }

        @Override
        public INetworkManagerBuilder load(String pathUrl) {
            Log.d("manoj", "netweok call : " + pathUrl);
            this.pathUrl = pathUrl;
            return this;
        }

        @Override
        public NetworkManager as(Class classTarget) {
            this.targetType = TypeToken.get(classTarget);
            return new NetworkManager(this);
        }
    }

    public interface INetworkManagerBuilder {
        INetworkManagerBuilder load(String pathUrl);

        NetworkManager as(Class classTarget);
    }
}