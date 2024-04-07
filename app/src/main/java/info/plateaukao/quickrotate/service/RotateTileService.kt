package info.plateaukao.quickrotate

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.drawable.Icon
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.service.quicksettings.TileService
import android.view.Surface
import android.view.WindowManager

class Rotate180Service : RotateTileService() {
    override val rotation = Surface.ROTATION_180
    override val rotateDrawableId = R.drawable.ic_rotate_180
}

class RotateLeftService : RotateTileService() {
    override val rotation = Surface.ROTATION_270
    override val rotateDrawableId = R.drawable.ic_rotate_left
}

class RotateRightService : RotateTileService() {
    override val rotation = Surface.ROTATION_90
    override val rotateDrawableId = R.drawable.ic_rotate_right
}

open class RotateTileService : TileService() {
    open val rotation = Surface.ROTATION_0
    open val rotateDrawableId = R.drawable.ic_rotate_right

    override fun onClick() {
        if (maybeShowPermissionActivity()) return

        val newOrientation = when (getCurrentOrientation()) {
            Surface.ROTATION_0 -> rotation
            else -> Surface.ROTATION_0
        }

        if (Build.MANUFACTURER.equals("ONYX")) {
            val intent = Intent().apply {
                action = "com.onyx.action.ROTATION"
                putExtra("rotation", newOrientation)
                putExtra("args_rotate_by", 2)
            }
            baseContext.sendBroadcast(intent)
        } else {
            Settings.System.putInt(
                contentResolver,
                Settings.System.USER_ROTATION,
                newOrientation
            )
        }
        updateTile(newOrientation)
    }

    override fun onBind(intent: Intent?): IBinder? {
        requestListeningState(this, ComponentName(this, RotateRightService::class.java))
        return super.onBind(intent)
    }

    override fun onStartListening() {
        super.onStartListening()
        updateTile()
    }

    private fun updateTile(orientation: Int = getCurrentOrientation()) {
        when (orientation) {
            Surface.ROTATION_0 -> qsTile.icon = Icon.createWithResource(this, rotateDrawableId)
            else -> qsTile.icon = Icon.createWithResource(this, R.drawable.ic_portrait)
        }
        qsTile.updateTile()
    }

    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission")
    private fun maybeShowPermissionActivity(): Boolean {
        applicationContext.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
        if (!Settings.System.canWrite(this)) {
            // show setting dialog
            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            return true
        }
        return false
    }

    @Suppress("DEPRECATION")
    private fun getCurrentOrientation(): Int =
        (getSystemService(WINDOW_SERVICE) as WindowManager).defaultDisplay.orientation

}