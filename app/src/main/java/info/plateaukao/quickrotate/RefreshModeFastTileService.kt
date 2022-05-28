package info.plateaukao.quickrotate

import com.onyx.android.sdk.api.device.epd.UpdateOption

class RefreshModeFastTileService : BaseRefreshModeTileService() {
    override fun onClick() {
        super.onClick()
        startActivity(RefreshModeActivity.createIntent(this, UpdateOption.FAST))
    }
}
