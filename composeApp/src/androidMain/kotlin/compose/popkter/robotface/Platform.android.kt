package compose.popkter.robotface

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun isLandScape(): Boolean = Resources.getSystem().configuration.orientation == Configuration.ORIENTATION_LANDSCAPE