package inakavr.app.birth.h3.inakavr.model.repository

import inakavr.app.birth.h3.inakavr.model.entity.YoutubeDataAPI
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

class MovieRepository @Inject constructor(
        private val items: BehaviorProcessor<YoutubeDataAPI>
){
    /**
     * アイテム監視
     */
    fun observeItems(): Flowable<YoutubeDataAPI> = items.hide()

    /**
     * アイテム設定
     */
    fun setItems(items: YoutubeDataAPI) = Completable.fromAction {
        this.items.onNext(items)
    }
}