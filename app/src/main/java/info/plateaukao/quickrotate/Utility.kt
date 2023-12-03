package info.plateaukao.quickrotate

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.ClipData
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.provider.Settings.Secure
import android.widget.Toast
import info.plateaukao.quickrotate.BuildConfig.*


class Utility {
    companion object {
        private const val PERMISSION = "android.permission.WRITE_SECURE_SETTINGS"
        private final val COMMAND = ("adb -d shell pm grant $APPLICATION_ID").toString() + " " + PERMISSION
        private final val SU_COMMAND = ("su -c pm grant $APPLICATION_ID").toString() + " " + PERMISSION

        private const val DISPLAY_DALTONIZER_ENABLED = "accessibility_display_daltonizer_enabled"
        private const val DISPLAY_DALTONIZER = "accessibility_display_daltonizer"

    }

    fun hasPermission(context: Context): Boolean {
        return context.checkCallingOrSelfPermission(PERMISSION) == PackageManager.PERMISSION_GRANTED
    }

    fun createTipsDialog(context: Context): Dialog {
        return AlertDialog.Builder(context, R.style.Theme_Material_Light_Dialog_Alert)
            .setTitle("Need Permission!")
            .setMessage("Use adb to grant permission")
            .setPositiveButton(android.R.string.ok, null)
            .create()
    }

    fun isGrayscaleEnable(context: Context): Boolean {
        val contentResolver = context.contentResolver
        return (Secure.getInt(contentResolver, DISPLAY_DALTONIZER_ENABLED, 0) == 1
                && Secure.getInt(contentResolver, DISPLAY_DALTONIZER, 0) == 0)
    }

    fun toggleGrayscale(context: Context, grayscale: Boolean) {
        val contentResolver = context.contentResolver
        Secure.putInt(contentResolver, DISPLAY_DALTONIZER_ENABLED, if (grayscale) 1 else 0)
        Secure.putInt(contentResolver, DISPLAY_DALTONIZER, if (grayscale) 0 else -1)
    }
}