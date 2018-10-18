package inakavr.app.birth.h3.inakavr.di

import dagger.Subcomponent
import inakavr.app.birth.h3.inakavr.fragment.PhotoFragment

@Subcomponent(modules = [PhotoModule::class, ApiModule::class])
interface PhotoComponent {

    fun inject(fragment: PhotoFragment)
}