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

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.ViewTreeObserver
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.chris.news.fondue.android.R
import net.chris.news.fondue.android.extension.load
import kotlinx.android.synthetic.main.fragment_detail.news_detail as newsDetail
import kotlinx.android.synthetic.main.fragment_detail.news_detail_img as newsImage

class DetailFragment : BaseFragment() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.StringActionArgsImgUrl?.let { imgPath -> newsImage.load(imgPath, R.drawable.img_loading, R.drawable.img_error) }
            ?: { newsImage.visibility = GONE }()
        newsDetail.settings.textZoom = 90
        newsDetail.loadUrl(args.StringActionArgsUrl)
        newsDetail.settings.builtInZoomControls = false
        newsDetail.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                if (newsDetail.measuredHeight != 0) {
                    newsDetail.viewTreeObserver.removeOnPreDrawListener(this)
                }
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_detail

    override fun showHomeAsUp() = true

    override fun getActionBarTitle() = args.StringActionArgsTitle
}
