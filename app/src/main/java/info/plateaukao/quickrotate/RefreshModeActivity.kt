package info.plateaukao.quickrotate

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.onyx.android.sdk.api.device.epd.UpdateOption
import com.onyx.android.sdk.device.Device

private val device = Device.currentDevice

class RefreshModeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent()
        finish()
    }

    private fun handleIntent() {
        val updateOption = findUpdateOption(intent.getIntExtra(ARG_UPDATE_OPTION, UpdateOption.NORMAL.ordinal))
        device.systemRefreshMode = updateOption
    }

    private fun findUpdateOption(value: Int): UpdateOption =
        UpdateOption.values().firstOrNull { it.ordinal == value } ?: UpdateOption.NORMAL

    companion object {
        private const val ARG_UPDATE_OPTION = "update_option"

        fun createIntent(context: Context, option: UpdateOption): Intent {
            return Intent(context, RefreshModeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra(ARG_UPDATE_OPTION, option.ordinal)
            }
        }
    }
}
