package inakavr.app.birth.h3.inakavr.di

import android.graphics.Movie
import dagger.Module
import dagger.Provides
import h3.birth.app.kusegemater.service.MovieApi
import inakavr.app.birth.h3.inakavr.model.repository.MovieRepository
import inakavr.app.birth.h3.inakavr.model.service.MovieService
import io.reactivex.processors.BehaviorProcessor

@Module
class MovieModule() {
    @Provides
    fun ProvideMovieRepository() = MovieRepository(BehaviorProcessor.create())

    @Provides
    fun ProvideMovieService(api: MovieApi, repository: MovieRepository) = MovieService(api, repository)
}