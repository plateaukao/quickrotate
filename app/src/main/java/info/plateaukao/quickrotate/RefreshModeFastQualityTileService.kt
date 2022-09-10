package info.plateaukao.quickrotate

import android.service.quicksettings.TileService
import com.onyx.android.sdk.api.device.epd.UpdateOption

class RefreshModeFastQualityTileService : TileService() {
    override fun onClick() {
        super.onClick()
        startActivityAndCollapse(RefreshModeActivity.createIntent(this, UpdateOption.FAST_QUALITY))
    }
}
