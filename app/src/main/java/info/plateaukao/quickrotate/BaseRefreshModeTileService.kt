package info.plateaukao.quickrotate

import android.content.Intent
import android.service.quicksettings.TileService
import com.onyx.android.sdk.api.device.epd.UpdateOption
import com.onyx.android.sdk.device.BaseDevice
import com.onyx.android.sdk.device.Device

open class BaseRefreshModeTileService : TileService() {
    protected var device: BaseDevice? = null

    override fun onStartListening() {
        device = Device.currentDevice()
    }

    override fun onStopListening() {
        device = null
    }

    @Suppress("DEPRECATION")
    override fun onClick() {
        super.onClick()
        baseContext.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
    }
}
