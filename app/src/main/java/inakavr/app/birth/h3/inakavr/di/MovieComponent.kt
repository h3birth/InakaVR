package inakavr.app.birth.h3.inakavr.di

import dagger.Subcomponent
import inakavr.app.birth.h3.inakavr.ui.movie.MovieFragment

@Subcomponent(modules = [MovieModule::class, ApiModule::class])
interface MovieComponent {

    fun inject(fragment: MovieFragment)
}