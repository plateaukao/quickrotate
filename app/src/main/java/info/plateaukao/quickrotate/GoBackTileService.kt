package info.plateaukao.quickrotate

import android.content.Intent
import android.service.quicksettings.TileService
import java.util.*

open class GoBackTileService : TileService() {
    @Suppress("DEPRECATION")
    override fun onClick() {
        val it = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        baseContext.sendBroadcast(it)

        val timerTaskObj: TimerTask = object : TimerTask() {
            override fun run() {
                baseContext.sendBroadcast(
                    Intent("onyx.android.intent.send.key.event").putExtra("key_code", 4)
                )
            }
        }
        Timer().schedule(timerTaskObj, 100)
    }
}
