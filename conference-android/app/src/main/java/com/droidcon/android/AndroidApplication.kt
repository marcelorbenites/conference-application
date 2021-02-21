package com.droidcon.android

import android.app.Application
import com.droidcon.DependencyManager
import com.droidcon.DroidconApplication
import com.droidcon.conference.OkHttpConferenceGateway
import com.droidcon.rxjava.RxJavaDispatcherFactory
import okhttp3.OkHttpClient

class AndroidApplication : Application() {

    lateinit var dependencyManager: DependencyManager

    override fun onCreate() {
        super.onCreate()
        dependencyManager = DroidconApplication.Builder()
            .registerDispatcherFactory(lazy { RxJavaDispatcherFactory() })
            .registerConferenceGateway(lazy {
                OkHttpConferenceGateway(
                    "http://10.0.2.2:8080/",
                    OkHttpClient()
                )
            })
            .start()
    }
}
