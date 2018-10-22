package inakavr.app.birth.h3.inakavr

import android.app.Application
import inakavr.app.birth.h3.inakavr.di.AppComponent
import inakavr.app.birth.h3.inakavr.di.DaggerAppComponent

class InakaVRApp : Application() {
    val component: AppComponent = DaggerAppComponent.create()
}