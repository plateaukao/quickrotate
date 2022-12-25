package info.plateaukao.quickrotate.service

import android.content.Intent
import android.service.quicksettings.TileService
import java.util.*

open class RecentTaskTileService : TileService() {
    @Suppress("DEPRECATION")
    override fun onClick() {
        val it = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        baseContext.sendBroadcast(it)

        val timerTaskObj: TimerTask = object : TimerTask() {
            override fun run() {
                baseContext.sendBroadcast(Intent("toggle_recent_screen"))
            }
        }
        Timer().schedule(timerTaskObj, 100)
    }
}
