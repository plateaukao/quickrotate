package info.plateaukao.quickrotate

import android.content.Intent
import android.service.quicksettings.TileService
import com.onyx.android.sdk.api.device.epd.UpdateOption
import com.onyx.android.sdk.device.BaseDevice
import com.onyx.android.sdk.device.Device

class RefreshModeNormalTileService : TileService() {
    private val device: BaseDevice = Device.currentDevice()

    @Suppress("DEPRECATION")
    override fun onClick() {
        baseContext.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
        device.systemRefreshMode = UpdateOption.NORMAL
    }
}
