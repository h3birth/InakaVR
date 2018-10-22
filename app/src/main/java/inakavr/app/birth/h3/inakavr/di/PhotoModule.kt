package inakavr.app.birth.h3.inakavr.di

import dagger.Module
import dagger.Provides
import inakavr.app.birth.h3.inakavr.model.api.PhotoApi
import inakavr.app.birth.h3.inakavr.model.repository.PhotoRepository
import inakavr.app.birth.h3.inakavr.model.service.PhotoService
import io.reactivex.processors.BehaviorProcessor

@Module
class PhotoModule() {
    @Provides
    fun ProvidePhotoRepository() = PhotoRepository(BehaviorProcessor.create())

    @Provides
    fun ProvidePhotoService(api: PhotoApi, repository: PhotoRepository) = PhotoService(api, repository)
}