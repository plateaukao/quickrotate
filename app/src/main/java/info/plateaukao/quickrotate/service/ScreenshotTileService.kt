package info.plateaukao.quickrotate.service

import android.content.Intent
import android.service.quicksettings.TileService
import java.util.*

open class ScreenshotTileService : TileService() {
    @Suppress("DEPRECATION")
    override fun onClick() {
        baseContext.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))

        val timerTaskObj: TimerTask = object : TimerTask() {
            override fun run() {
                baseContext.sendBroadcast(Intent("onyx.android.intent.screenshot"))
            }
        }
        Timer().schedule(timerTaskObj, 100)
    }
}
