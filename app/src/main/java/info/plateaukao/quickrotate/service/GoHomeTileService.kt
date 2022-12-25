package info.plateaukao.quickrotate

import android.content.ComponentName
import android.content.Intent
import android.service.quicksettings.TileService
import java.util.*

open class GoHomeTileService : TileService() {
    @Suppress("DEPRECATION")
    override fun onClick() {
        val it = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        baseContext.sendBroadcast(it)

        val timerTaskObj: TimerTask = object : TimerTask() {
            override fun run() {
                startActivity(
                    Intent(Intent.ACTION_MAIN)
                        .addCategory(Intent.CATEGORY_HOME)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .setComponent(ComponentName("com.onyx", "com.onyx.StartupActivity"))
                )
            }
        }
        Timer().schedule(timerTaskObj, 100)
    }
}
