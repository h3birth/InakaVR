package h3.birth.app.kusegemater.service

import inakavr.app.birth.h3.inakavr.model.YoutubeDataAPI
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by k-kobayashi on 2018/06/03.
 */
interface YoutubeDataApiService {
    @GET("youtube/v3/search")
    fun youtubeMovie(@Query("key") key: String, @Query("part") part: String, @Query("channelId") channelId: String): Observable<YoutubeDataAPI>
}