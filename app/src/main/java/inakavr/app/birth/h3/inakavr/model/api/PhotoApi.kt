package inakavr.app.birth.h3.inakavr.model.api

import inakavr.app.birth.h3.inakavr.model.entity.Photo
import io.reactivex.Single
import retrofit2.http.GET

interface PhotoApi {
    @GET
    fun getPhoto(): Single<List<Photo>>
}