package com.farazannajmi.majesticlife;

import android.app.Application;
import android.graphics.Bitmap;

import com.backtory.java.internal.BacktoryClient;
import com.backtory.java.internal.KeyConfiguration;
import com.backtory.java.internal.LogLevel;

import java.util.ArrayList;

/**
 * Created by Narges on 4/18/2018.
 */

public class AppManager extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        // Logs with debug priority or higher. Includes network requests.
        // todo: when building for release, comment debug codes!!!!
        BacktoryClient.setDebugMode(BuildConfig.DEBUG);
        BacktoryClient.setLogLevel(LogLevel.Debug);

        // Initializing backtory
        BacktoryClient.init(KeyConfiguration.newBuilder().setAuthKeys(
                "5afd32ace4b02c90785846f2",
                "5afd32ace4b0322942dd05e7").
                // Finalizing sdk
                        build(), this);
    }
}
