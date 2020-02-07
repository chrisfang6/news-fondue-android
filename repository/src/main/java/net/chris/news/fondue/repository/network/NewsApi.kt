package net.chris.news.fondue.repository.network

import io.reactivex.Single
import net.chris.news.fondue.usecase.bo.HeadlinesBO
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("nc/article/headline/{category}/{start_index}-20.html")
    fun fetchHeadlines(@Path("category") category: String, @Path("start_index") startIndex: Int): Single<HeadlinesBO>
}
