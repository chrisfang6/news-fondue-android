package net.chris.news.fondue.repository

import io.reactivex.Single
import net.chris.news.fondue.usecase.Headlines
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("nc/article/headline/{category}/{start_index}-20.html")
    fun getRealTimeHot(@Path("category") category: String, @Path("start_index") startIndex: Int): Single<Headlines>
}
