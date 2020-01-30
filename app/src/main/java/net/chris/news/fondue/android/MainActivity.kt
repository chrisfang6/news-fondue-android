package net.chris.news.fondue.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.chris.news.fondue.android.di.DaggerAppComponent
import net.chris.news.fondue.usecase.Headlines
import net.chris.news.fondue.usecase.NewsListUseCase
import net.chris.news.fondue.usecase.ResultListener
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var newsListUseCase: NewsListUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.builder().baseUrl("http://c.m.163.com/").build().inject(this)

        newsListUseCase.getHeadlines(
            "T1348647853363",
            0,
            object : ResultListener<Headlines> {
                override fun onResult(content: Headlines) {
                    Timber.d("got ${content.list.size} news")
                }

                override fun onError(error: Throwable) {
                    Timber.e(error)
                }
            })
    }
}
