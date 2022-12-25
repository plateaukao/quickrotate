package info.plateaukao.quickrotate

import android.content.Intent
import android.service.quicksettings.TileService
import java.util.*

class RefreshTileService : TileService() {
    @Suppress("DEPRECATION")
    override fun onClick() {
        baseContext.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))

        val timerTaskObj: TimerTask = object : TimerTask() {
            override fun run() {
                baseContext.sendBroadcast(Intent("onyx.android.intent.action.REFRESH_SCREEN"))
            }
        }
        Timer().schedule(timerTaskObj, 100)
    }
}
