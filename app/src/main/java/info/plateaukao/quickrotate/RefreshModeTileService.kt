package info.plateaukao.quickrotate

import com.onyx.android.sdk.api.device.epd.EpdController
import com.onyx.android.sdk.api.device.epd.UpdateOption

class RefreshModeTileService : BaseRefreshModeTileService() {
    override fun onClick() {
        super.onClick()
        device?.systemRefreshMode =
            if (EpdController.isInFastMode()) UpdateOption.NORMAL else UpdateOption.FAST
    }
}
