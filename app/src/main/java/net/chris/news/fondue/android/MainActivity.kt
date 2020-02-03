package net.chris.news.fondue.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import net.chris.news.fondue.android.di.DaggerAppComponent
import net.chris.news.fondue.android.viewmodel.NewsViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var newsViewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.builder().baseUrl("http://c.m.163.com/").build().inject(this)

        val newsViewModel: NewsViewModel = ViewModelProvider(this, newsViewModelFactory)[NewsViewModel::class.java]

        newsViewModel.request()
    }
}
