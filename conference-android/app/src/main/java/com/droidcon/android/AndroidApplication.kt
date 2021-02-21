package com.droidcon.android

import android.app.Application
import android.content.Context
import com.droidcon.DependencyInjector
import com.droidcon.Droidcon

class AndroidApplication : Application() {
    var droidcon = Droidcon()
}

fun Context.injector(): DependencyInjector = (applicationContext as AndroidApplication).droidcon
