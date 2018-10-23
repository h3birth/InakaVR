package inakavr.app.birth.h3.inakavr.ui.movie

import inakavr.app.birth.h3.inakavr.model.service.MovieService
import javax.inject.Inject

class MovieViewModel @Inject constructor(
        private val service: MovieService
) {

    var youtubeKey: String = ""

    /**
     * アイテム取得
     * @return Completable
     */
    fun fetchItems() = service.fetchItems(youtubeKey)

    /**
     * アイテム監視
     * @return Flowable
     */
    fun observeItems() = service.observeItems()
}