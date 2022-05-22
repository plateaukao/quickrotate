package info.plateaukao.quickrotate

import android.content.Intent
import android.service.quicksettings.TileService
import com.onyx.android.sdk.api.device.epd.EpdController
import com.onyx.android.sdk.api.device.epd.UpdateOption
import com.onyx.android.sdk.device.BaseDevice
import com.onyx.android.sdk.device.Device
import java.util.*

class RefreshModeTileService : TileService() {
    private val device: BaseDevice = Device.currentDevice()

    @Suppress("DEPRECATION")
    override fun onClick() {
        baseContext.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
        val timerTaskObj: TimerTask = object : TimerTask() {
            override fun run() {
                device.systemRefreshMode =
                    if (EpdController.isInFastMode()) UpdateOption.NORMAL else UpdateOption.FAST
            }
        }
        Timer().schedule(timerTaskObj, 100)
    }
}
