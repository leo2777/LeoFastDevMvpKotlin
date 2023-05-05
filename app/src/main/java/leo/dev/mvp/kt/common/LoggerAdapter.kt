package leo.dev.mvp.kt.common

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import leo.dev.mvp.kt.BuildConfig


/**
 *
 * ***********************************************************************
 *the project desc: logger 适配器
 *this name is LoggerAdadpter
 *this from package LeoDevMvpKotlinDemo
 *this create by machine leo mark
 *this full time on 2023年04月19日 15:52:50
 *this developer is 冯立仁
 *this developer QQ is 2549732107
 * ***********************************************************************
 */
class LoggerAdapter(formatStrategy: FormatStrategy) : AndroidLogAdapter(formatStrategy) {
    /**
     * 是否开启log日志，true 开启， false 关闭
     * 上线版本的时候需要关闭日志，
     * 采用 [BuildConfig.DEBUG] 来判断
     *
     * @param [priority] 给框架使用，无需理会，只需修改返回值
     * @param [tag] 给框架使用，无需理会，只需修改返回值
     * @return true 开启， false 关闭
     */
    override fun isLoggable(priority: Int, tag: String?): Boolean {
        return BuildConfig.DEBUG;
    }
}