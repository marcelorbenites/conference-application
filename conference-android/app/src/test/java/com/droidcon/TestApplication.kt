package com.droidcon

import com.droidcon.android.AndroidApplication

fun AndroidApplication.onCreate(droidcon: Droidcon) {
    this.droidcon = droidcon
    onCreate()
}
