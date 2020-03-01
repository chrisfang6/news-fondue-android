/**
 *   Copyright 2019 chrisfang6
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package net.chris.news.fondue.android.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_main.*
import net.chris.news.fondue.android.R
import net.chris.news.fondue.android.view.adapter.NewsAdapter
import net.chris.news.fondue.android.viewmodel.NewsViewModel
import net.chris.news.fondue.android.vo.NewsVO
import javax.inject.Inject

class MainFragment : BaseFragment() {

    private val adapter: NewsAdapter by lazy { NewsAdapter() }

    @Inject
    lateinit var newsViewModelFactory: ViewModelProvider.Factory

    private val newsViewModel: NewsViewModel by viewModels { newsViewModelFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        newsViewModel.headlinesLiveData.observe(viewLifecycleOwner, Observer<PagedList<NewsVO>> { render(it) })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun render(pagedList: PagedList<NewsVO>) {
        adapter.submitList(pagedList)
    }

    override fun getLayoutId() = R.layout.fragment_main

    private fun setupRecyclerView() {
        news_recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        news_recycler.adapter = adapter
    }
}
