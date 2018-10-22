package inakavr.app.birth.h3.inakavr.model.repository

import inakavr.app.birth.h3.inakavr.model.entity.Photo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

class PhotoRepository @Inject constructor(
        private val items: BehaviorProcessor<List<Photo>>
){
    /**
     * アイテム監視
     */
    fun observeItems(): Flowable<List<Photo>> = items.hide()

    /**
     * アイテム設定
     */
    fun setItems(items: List<Photo>) = Completable.fromAction {
        this.items.onNext(items)
    }
}