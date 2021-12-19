package com.sutton.jokeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * When using hilt we always need to do this
 */
@HiltAndroidApp
class MainApplication : Application()