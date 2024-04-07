package info.plateaukao.quickrotate.service

import android.content.Intent
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import info.plateaukao.quickrotate.Utility


class ColorModeTileService: TileService() {
    private val utility = Utility()

    override fun onClick() {
        super.onClick()
        if (!utility.hasPermission(this)) {
            showDialog(utility.createTipsDialog(this))
            return
        }
        val oldState = qsTile.state
        if (oldState == Tile.STATE_ACTIVE) {
            setState(Tile.STATE_INACTIVE)
        } else {
            setState(Tile.STATE_ACTIVE)
        }
        utility.toggleGrayscale(this, oldState == Tile.STATE_INACTIVE)

        baseContext.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
    }


    private fun setState(state: Int) {
        val tile = qsTile
        tile.state = state
        tile.updateTile()
    }

    override fun onStartListening() {
        super.onStartListening()
        val grayscaleEnable: Boolean = utility.isGrayscaleEnable(this)
        setState(if (grayscaleEnable) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE)
    }
}