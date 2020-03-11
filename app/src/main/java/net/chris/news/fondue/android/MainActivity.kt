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
package net.chris.news.fondue.android

import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import net.chris.news.fondue.android.Constant.FLUTTER_ENGINE_ID
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.tool_bar as toolBar

class MainActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var flutterEngine: FlutterEngine

    private val flutterEngineCache by lazy { FlutterEngineCache.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)
        // Cache the FlutterEngine to be used by FlutterActivity.
        flutterEngineCache.put(FLUTTER_ENGINE_ID, flutterEngine)
    }

    override fun onDestroy() {
        flutterEngineCache.remove(FLUTTER_ENGINE_ID)
        flutterEngine.destroy()
        super.onDestroy()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
