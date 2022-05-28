package info.plateaukao.quickrotate

import com.onyx.android.sdk.api.device.epd.EpdController
import com.onyx.android.sdk.api.device.epd.UpdateOption

class RefreshModeTileService : BaseRefreshModeTileService() {
    override fun onClick() {
        super.onClick()
        val option = if (EpdController.isInFastMode()) UpdateOption.NORMAL else UpdateOption.FAST
        startActivity(RefreshModeActivity.createIntent(this, option))
    }
}
