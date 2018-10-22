package h3.birth.app.kusegemater.service

import inakavr.app.birth.h3.inakavr.model.entity.YoutubeDataAPI
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("youtube/v3/search")
    fun getMovies(@Query("key") key: String, @Query("part") part: String, @Query("channelId") channelId: String, @Query("type") type: String): Single<YoutubeDataAPI>
}