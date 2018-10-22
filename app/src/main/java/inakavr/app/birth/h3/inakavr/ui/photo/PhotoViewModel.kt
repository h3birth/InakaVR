package inakavr.app.birth.h3.inakavr.ui.photo

import inakavr.app.birth.h3.inakavr.model.service.PhotoService
import javax.inject.Inject

class PhotoViewModel @Inject constructor(
        private val service: PhotoService
) {

    /**
     * アイテム取得
     * @return Completable
     */
    fun fetchItems() = service.fetchItems()

    /**
     * アイテム監視
     * @return Flowable
     */
    fun observeItems() = service.observeItems()
}