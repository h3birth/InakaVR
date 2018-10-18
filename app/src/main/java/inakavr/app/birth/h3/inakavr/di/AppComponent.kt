package inakavr.app.birth.h3.inakavr.di

import dagger.Component

@Component(modules = [ApiModule::class])
interface AppComponent {
    fun plus(module: PhotoModule): PhotoComponent
}