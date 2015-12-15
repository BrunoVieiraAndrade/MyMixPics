package com.example.bruno.mymixpics;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NoConnectionActivity extends ActionBarActivity {

    @Bind(R.id.noConnectionImageView)
    ImageView noConnectionImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);
        ButterKnife.bind(this);
        noConnectionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDeviceOnline(getApplicationContext())) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

    }

    private boolean isDeviceOnline(Context context) {
        boolean isConnectionAvail = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if(netInfo != null)
                return netInfo.isConnected();
            else
                return isConnectionAvail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnectionAvail;
    }

}
