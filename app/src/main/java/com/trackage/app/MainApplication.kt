package com.trackage.app

import android.app.Application
import android.util.Log
import androidx.multidex.MultiDexApplication
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import dagger.hilt.android.HiltAndroidApp

/**
 * When using hilt we always need to do this
 */
@HiltAndroidApp
class MainApplication : MultiDexApplication() {
    override fun onCreate() {

        super.onCreate()
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("TrackageAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("TrackageAmplifyApp", "Could not initialize Amplify", error)
        }
    }
}