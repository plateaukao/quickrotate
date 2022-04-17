package info.plateaukao.quickrotate

import android.content.Intent
import android.provider.Settings
import android.service.quicksettings.TileService
import android.view.Surface
import android.view.WindowManager


class RotateRightService : TileService() {
    override fun onClick() {
        if (!Settings.System.canWrite(this)) {
            // show setting dialog
            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            return
        }

        when ((getSystemService(WINDOW_SERVICE) as WindowManager).defaultDisplay.orientation) {
            Surface.ROTATION_0->
                Settings.System.putInt(
                    contentResolver,
                    Settings.System.USER_ROTATION,
                    Surface.ROTATION_90
                )
            else ->
                Settings.System.putInt(
                    contentResolver,
                    Settings.System.USER_ROTATION,
                    Surface.ROTATION_0
                )
        }
    }

}