package info.plateaukao.quickrotate.service

import android.app.AlertDialog
import android.content.Intent
import android.service.quicksettings.TileService
import java.util.*

class FrontLightTileService: TileService() {
    override fun onClick() {
        // show an alert dialog with progress bar
        // when the progress bar is changed, send a broadcast to the service
        AlertDialog.Builder(this)
            .setTitle("Front Light")
            .setView(R.layout.front_light_dialog)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}