package info.plateaukao.quickrotate

import android.content.Intent
import android.service.quicksettings.TileService
import com.onyx.android.sdk.api.device.epd.EpdController
import java.util.*

open class RefreshTileService : TileService() {
    override fun onClick() {
        val it = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        baseContext.sendBroadcast(it)

        val timerTaskObj: TimerTask = object : TimerTask() {
            override fun run() {
                EpdController.enablePost(1)
                EpdController.applyGCOnce()
                EpdController.repaintEveryThing()
            }
        }
        Timer().schedule(timerTaskObj, 100)
    }
}
