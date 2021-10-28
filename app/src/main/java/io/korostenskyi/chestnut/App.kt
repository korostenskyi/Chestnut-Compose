package io.korostenskyi.chestnut

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.korostenskyi.chestnut.domain.model.BuildParams
import io.sentry.Sentry
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject lateinit var buildParams: BuildParams

    override fun onCreate() {
        super.onCreate()
        initSentry()
    }

    private fun initSentry() {
        if (buildParams.isSentryEnabled) {
            Sentry.init { options ->
                options.apply {
                    dsn = buildParams.sentryUrl
                    isEnableAutoSessionTracking = true
                }
            }
        }
    }
}
