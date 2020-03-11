package net.chris.news.fondue.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import javax.inject.Singleton

@Module
class FlutterModule {

    @Singleton
    @Provides
    fun provideFlutterEngine(context: Context): FlutterEngine = FlutterEngine(context).also { flutterEngine ->
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
    }
}
