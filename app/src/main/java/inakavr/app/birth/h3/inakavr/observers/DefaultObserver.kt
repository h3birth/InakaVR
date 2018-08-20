package h3.birth.app.kusegemater.observers

import io.reactivex.observers.DefaultObserver
/**
 * Created by h3birth on 2018/08/07.
 */
abstract class MyDefaultObserver<T>(val next:(T)->Unit = {},
                         val error:(Throwable)->Unit = {},
                         val completed:()->Unit = {}) : DefaultObserver<T>() {
    override fun onNext(t: T): Unit = next(t)

    override fun onError(e: Throwable): Unit = error(e)

    fun onCompleted(): Unit = completed()
}