package info.plateaukao.quickrotate

import com.onyx.android.sdk.api.device.epd.UpdateOption

class RefreshModeXTileService : BaseRefreshModeTileService() {
    override fun onClick() {
        super.onClick()
        device?.systemRefreshMode = UpdateOption.FAST_X
    }
}
