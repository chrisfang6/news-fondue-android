package net.chris.news.fondue.android

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import net.chris.news.fondue.android.Constant.DETAIL_CHANNEL
import net.chris.news.fondue.android.Constant.FLUTTER_CALL_ERROR_CODE
import net.chris.news.fondue.android.Constant.FLUTTER_CALL_ERROR_MESSAGE_GET_DETAIL
import net.chris.news.fondue.android.Constant.FLUTTER_CALL_METHOD_GET_DETAIL
import net.chris.news.fondue.android.Constant.FLUTTER_CALL_METHOD_GO_BACK
import net.chris.news.fondue.android.Constant.INTENT_PARAM_ROUTE
import timber.log.Timber

class NewsDetailFlutterActivity : FlutterActivity() {

    private lateinit var newsDetailMethodChannel: MethodChannel

    private val flutterEngineCache by lazy { FlutterEngineCache.getInstance() }

    override fun getFlutterEngine(): FlutterEngine? = flutterEngineCache.get(Constant.FLUTTER_ENGINE_ID)

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        Timber.d("# configure FlutterEngine $flutterEngine")
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        newsDetailMethodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, DETAIL_CHANNEL).apply {
            setMethodCallHandler { call, result ->
                when (call.method) {
                    FLUTTER_CALL_METHOD_GET_DETAIL -> intent.extras?.get(INTENT_PARAM_ROUTE)
                        ?.let { route -> result.success(route) }
                        ?: result.error(
                            FLUTTER_CALL_ERROR_CODE,
                            FLUTTER_CALL_ERROR_MESSAGE_GET_DETAIL,
                            null
                        )
                    FLUTTER_CALL_METHOD_GO_BACK -> {
                        result.success(true)
                        onBackPressed()
                    }
                    else -> result.notImplemented()
                }
            }
        }
    }
}
