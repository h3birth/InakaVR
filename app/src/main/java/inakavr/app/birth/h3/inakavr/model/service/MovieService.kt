package inakavr.app.birth.h3.inakavr.model.service

import h3.birth.app.kusegemater.service.MovieApi
import inakavr.app.birth.h3.inakavr.model.repository.MovieRepository
import javax.inject.Inject

class MovieService @Inject constructor(
private val api: MovieApi,
private val repository: MovieRepository
) {
    /**
     * アイテム取得
     */
    fun fetchItems(youtubeKey: String) = api.getMovies(
            youtubeKey,
            "snippet",
            "UCcPQ1-EFJPeheyFL_tNWLtg",
            "video").flatMapCompletable(repository::setItems)

    /**
     * アイテム監視
     */
    fun observeItems() = repository.observeItems()
}