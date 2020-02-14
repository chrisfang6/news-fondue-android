package net.chris.news.fondue.android.view

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import net.chris.news.fondue.android.R
import net.chris.news.fondue.android.viewmodel.NewsViewModel
import javax.inject.Inject

class MainFragment : BaseFragment() {

    @Inject
    lateinit var newsViewModelFactory: ViewModelProvider.Factory

    private val newsViewModel: NewsViewModel by viewModels { newsViewModelFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        newsViewModel.request()
    }

    override fun getLayoutId() = R.layout.fragment_main
}
