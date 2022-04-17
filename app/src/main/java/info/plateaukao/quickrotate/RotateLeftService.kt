package info.plateaukao.quickrotate

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.provider.Settings
import android.service.quicksettings.TileService
import android.view.Surface
import android.view.WindowManager


class RotateLeftService : TileService() {
    @SuppressLint("MissingPermission")
    override fun onClick() {
        applicationContext.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
        if (!Settings.System.canWrite(this)) {
            // show setting dialog
            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            return
        }

        Settings.System.putInt(
            contentResolver,
            Settings.System.ACCELEROMETER_ROTATION,
            0
        )

        when ((getSystemService(WINDOW_SERVICE) as WindowManager).defaultDisplay.orientation) {
            Surface.ROTATION_0 -> Settings.System.putInt(
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