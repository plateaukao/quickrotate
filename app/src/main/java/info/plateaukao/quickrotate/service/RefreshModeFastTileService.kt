package info.plateaukao.quickrotate.service

import android.service.quicksettings.TileService
import com.onyx.android.sdk.api.device.epd.UpdateOption
import info.plateaukao.quickrotate.RefreshModeActivity

class RefreshModeFastTileService : TileService() {
    override fun onClick() {
        super.onClick()
        startActivityAndCollapse(RefreshModeActivity.createIntent(this, UpdateOption.FAST))
    }
}
