package info.plateaukao.quickrotate

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.hardware.display.DisplayManager
import android.provider.Settings
import android.service.quicksettings.TileService
import android.view.Display
import android.view.Surface
import androidx.core.content.getSystemService


class RotateLeftService : TileService() {
    override fun onClick() {
        if (!Settings.System.canWrite(this)) {
            // show setting dialog
            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            return
        }

        val defaultDisplay =
            getSystemService<DisplayManager>()?.getDisplay(Display.DEFAULT_DISPLAY) ?: return

        Settings.System.putInt(
            contentResolver,
            Settings.System.ACCELEROMETER_ROTATION,
            0
        )

        when (defaultDisplay.orientation) {
            Surface.ROTATION_0-> Settings.System.putInt(
                contentResolver,
                Settings.System.USER_ROTATION,
                Surface.ROTATION_270
            )
            else -> Settings.System.putInt(
                contentResolver,
                Settings.System.USER_ROTATION,
                Surface.ROTATION_0
            )
        }
    }

}