package inakavr.app.birth.h3.inakavr.model.service

import h3.birth.app.kusegemater.service.MovieApi
import inakavr.app.birth.h3.inakavr.model.api.PhotoApi
import inakavr.app.birth.h3.inakavr.model.repository.MovieRepository
import inakavr.app.birth.h3.inakavr.model.repository.PhotoRepository
import javax.inject.Inject

class PhotoService @Inject constructor(
        private val api: PhotoApi,
        private val repository: PhotoRepository
) {
    /**
     * アイテム取得
     */
    fun fetchItems() = api.getPhoto().flatMapCompletable(repository::setItems)

    /**
     * アイテム監視
     */
    fun observeItems() = repository.observeItems()
}