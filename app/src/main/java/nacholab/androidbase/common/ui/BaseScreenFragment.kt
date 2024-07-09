package nacholab.androidbase.common.ui

import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewbinding.ViewBinding

abstract class BaseScreenFragment<T: ViewBinding> : BaseFragment<T>() {

    private val insetsController: WindowInsetsControllerCompat? by lazy {
        activity?.window?.let { window -> WindowInsetsControllerCompat(window, window.decorView) }
    }

    abstract fun getLightStatusBar(): Boolean
    abstract fun isFullScreen(): Boolean

    private var mainLayout: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLightStatusBar(getLightStatusBar())
        setFullScreen(isFullScreen())
    }

    private fun setFullScreen(fullscreen: Boolean){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R)
            if (fullscreen)
                activity?.window?.addFlags(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
            else
                activity?.window?.clearFlags(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        else{
            activity?.window?.setDecorFitsSystemWindows(!fullscreen)
            activity?.window?.navigationBarColor = Color.argb(0,0,0,0)
            activity?.window?.statusBarColor = Color.argb(0,0,0,0)
        }
    }

    private fun setLightStatusBar(light: Boolean) {
        insetsController?.isAppearanceLightStatusBars = light
    }

    fun setupAndroidUI(navbarBackground: View?, statusbarBackground: View?){
        if (hasNavBar()) {
            navbarBackground?.visibility = View.VISIBLE
            val nblp = navbarBackground?.layoutParams
            nblp?.height = getNavbarHeight()
            navbarBackground?.layoutParams = nblp
        }else{
            navbarBackground?.visibility = View.GONE
        }

        val sblp = statusbarBackground?.layoutParams
        sblp?.height = getStatusbarHeight()
        statusbarBackground?.layoutParams = sblp
    }

    private fun hasNavBar(): Boolean {
        val size = Point()
        val realSize = Point()

        activity?.windowManager?.defaultDisplay?.getRealSize(realSize)
        activity?.windowManager?.defaultDisplay?.getSize(size)

        return size.y != realSize.y
    }

    protected fun getStatusbarHeight(): Int {
        var result = 0

        resources.getIdentifier("status_bar_height", "dimen", "android")
            .let { resId ->
                if (resId > 0)
                    result = resources.getDimensionPixelSize(resId)
            }

        return result
    }

    protected fun getNavbarHeight(): Int {
        var result = 0

        if (hasNavBar()) {
            resources.getIdentifier("navigation_bar_height", "dimen", "android")
                .let { resId ->
                    if (resId > 0)
                        result = resources.getDimensionPixelSize(resId)
                }
        }

        return result
    }
}