package info.plateaukao.quickrotate

import com.onyx.android.sdk.api.device.epd.UpdateOption

class RefreshModeFastQualityTileService : BaseRefreshModeTileService() {
    override fun onClick() {
        super.onClick()
        device?.systemRefreshMode = UpdateOption.FAST_QUALITY
    }
}
