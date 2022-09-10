package info.plateaukao.quickrotate

import android.service.quicksettings.TileService
import com.onyx.android.sdk.api.device.epd.UpdateOption

class RefreshModeNormalTileService : TileService() {
    override fun onClick() {
        super.onClick()
        startActivityAndCollapse(RefreshModeActivity.createIntent(this, UpdateOption.NORMAL))
    }
}
