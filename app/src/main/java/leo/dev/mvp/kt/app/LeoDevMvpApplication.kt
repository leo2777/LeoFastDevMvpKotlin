package leo.dev.mvp.kt.app

import android.view.Gravity
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import es.dmoral.toasty.Toasty
import leo.dev.mvp.kt.common.GlideLoaderProcessor
import leo.dev.mvp.kt.common.LoggerAdapter
import leo.dev.mvp.kt.constants.Constants
import leo.study.lib_base.base.BaseApplication
import leo.study.lib_base.image.ImageLoaderHelper
import leo.study.lib_base.image.ImageOptions


/**
 *
 * ***********************************************************************
 *the project desc: 应用 application
 *this name is LeoDevMvpApplication
 *this from package LeoDevMvpKotlinDemo
 *this create by machine leo mark
 *this full time on 2023年04月19日 14:53:09
 *this developer is 冯立仁
 *this developer QQ is 2549732107
 * ***********************************************************************
 */
class LeoDevMvpApplication : BaseApplication() {

    private val tag: String = "leo_dev_mvp_kotlin_demo"

    override var dataStoreName: String = Constants.DataStoreNameValue
    override var sharedPrfName: String = Constants.SharedPrfNameValue

    override fun init() {
        //初始化logger
        initLogger()
        //初始化toasty
        initToasty()
        //初始化 图片加载
        initImage()
    }



    /**
     * 初始化 logger 日志
     *
     * 其他具体使用请查看：[logger GitHub网址](https://github.com/orhanobut/logger)
     */
    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) //（可选）是否显示线程信息。 默认值为true
            .methodCount(2)        //（可选）要显示的方法行数。 默认2
            .methodOffset(0)       //（可选）设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
            .tag(tag)                  //（可选）每个日志的全局标记。 默认PRETTY_LOGGER（如上图）
            .build()
        Logger.addLogAdapter(LoggerAdapter(formatStrategy))
    }

    /**
     * 初始化 颜色 toast
     *
     * 其他具体使用请查看：[toasty Github网址](https://github.com/GrenderG/Toasty)
     */
    private fun initToasty() {
        Toasty.Config.getInstance()
//            .tintIcon(boolean tintIcon) // optional (apply textColor also to the icon)
//            .setToastTypeface(@NonNull Typeface typeface) // optional
            .setTextSize(14) // optional
//            .allowQueue(boolean allowQueue) // optional (prevents several Toastys from queuing)
            .setGravity(Gravity.CENTER) // optional (set toast gravity, offsets are optional)
//            .supportDarkTheme(boolean supportDarkTheme) // optional (whether to support dark theme or not)
//            .setRTL(boolean isRTL) // optional (icon is on the right)
            .apply(); // required
    }


    /**
     * 初始化图片加载代理
     *
     */
    private fun initImage() {
        //设置配置类
        val imageOptions = ImageOptions.Builder(this)
            .placeholderResId(leo.study.lib_base.R.drawable.icon_default_image_loading) //预览图片
            .errorResId(leo.study.lib_base.R.drawable.icon_default_image_error)  //错误图片
//            .width(300)   //目标宽度
//            .height(300)  //目标高度
//            .isCenterCrop(false)  //是否居中裁剪
//            .isCenterInside(true) //是否是显示所有居中
//            .config(Bitmap.Config.ARGB_8888) //Bitmap类型
            .build()
        //设置代理类
        ImageLoaderHelper.instance.setImgLoaderProxy(GlideLoaderProcessor(imageOptions))


    }


}