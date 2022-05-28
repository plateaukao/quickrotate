package info.plateaukao.quickrotate

import android.content.Intent
import android.service.quicksettings.TileService

open class BaseRefreshModeTileService : TileService() {
    @Suppress("DEPRECATION")
    override fun onClick() = baseContext.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
}
