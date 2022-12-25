package info.plateaukao.quickrotate.service

import android.service.quicksettings.TileService
import com.onyx.android.sdk.api.device.epd.EpdController
import com.onyx.android.sdk.api.device.epd.UpdateOption
import info.plateaukao.quickrotate.RefreshModeActivity

class RefreshModeTileService : TileService() {
    override fun onClick() {
        super.onClick()
        val option = if (EpdController.isInFastMode()) UpdateOption.NORMAL else UpdateOption.FAST
        startActivityAndCollapse(RefreshModeActivity.createIntent(this, option))
    }
}
