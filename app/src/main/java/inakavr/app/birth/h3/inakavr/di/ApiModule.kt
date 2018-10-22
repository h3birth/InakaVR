package inakavr.app.birth.h3.inakavr.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import h3.birth.app.kusegemater.service.MovieApi
import inakavr.app.birth.h3.inakavr.R
import inakavr.app.birth.h3.inakavr.model.api.PhotoApi
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import inakavr.app.birth.h3.inakavr.model.entity.Photo

@Module
class ApiModule {

    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return client.build()
    }

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://www.googleapis.com/")
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun providePhotoApi() = object : PhotoApi {
        override fun getPhoto(): Single<List<Photo>> = Single.just(listOf(
                Photo(1, "ひまわり畑", "himawari.jpg", "https://kakosandbox.herokuapp.com/images/himawari_top.JPG"),
                Photo(2, "竜神狭", "ryujinkyo.jpg", "https://kakosandbox.herokuapp.com/images/ryujinkyo_top.JPG"),
                Photo(3, "水田", "suiden.jpg", "https://kakosandbox.herokuapp.com/images/suiden_top.JPG"),
                Photo(4, "大洗磯崎神社", "ooarai_isosaki_jinja.jpg", "https://kakosandbox.herokuapp.com/images/ooarai_top.JPG"),
                Photo(5, "袋田の滝", "fukurodanotaki.jpg", "https://kakosandbox.herokuapp.com/images/fukurodanotaki_top.JPG"),
                Photo(6, "駅", "yamaturieki.jpg", "https://kakosandbox.herokuapp.com/images/eki_top.JPG")
        ))
    }
}