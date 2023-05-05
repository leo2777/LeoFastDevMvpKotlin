# LeoFastDevMvpKotlin

一款基于MVP架构的快速应用开发框架，kotlin版本    **目前正在持续更新文档中**

## 添加基础module到项目中
### 1. 集成
#### 方法一：通过 导入依赖方式集成

Android Studio 4.0+：
```groovy
pluginManagement {
    repositories {
        ......
        maven { url "https://jitpack.io" }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ......
        maven { url "https://jitpack.io" }
    }
}
```
Android Studio 4.0-：
```groovy
allprojects {
    repositories {
        ......
        maven { url 'https://jitpack.io' }
    }
}
```

//todo：此处需要添加依赖代码（待完成）

#### 方法二：下载demo之后倒入module方式集成

首先下载本仓库代码，并解压文件（可不打开本项目），新建一个项目之后，点击 import Module...


![import module](https://github.com/leo2777/LeoFastDevMvpKotlin/blob/main/show_imgs/Snipaste_2023-04-18_16-06-04.png)

然后找到下载仓库里面的 **lib_fast_dev_mvp_kt** module 添加完之后可以重命名添加的module名字

![选中module](https://github.com/leo2777/LeoFastDevMvpKotlin/blob/main/show_imgs/Snipaste_2023-04-18_16-08-47.png)

### 2. 添加必要文件

上面两种集成方法，添加完module之后，都会报错，如下图，暂时不理，继续进行我们的下一步

![导入报错](https://github.com/leo2777/LeoFastDevMvpKotlin/blob/main/show_imgs/Snipaste_2023-04-18_16-11-41.png)

因为这个框架采用的是统一的依赖方式，所以需要添加一个 **config.gradle** 文件，当然这个文件可以随便命名。建议直接从下载的代码里（或者直接从仓库里下载）**config.gradle** 文件到
你所新建的项目当中（注意：放在根目录下）

//todo：图片

然后我们需要添加这个文件到我们项目的 **build.gradle** 文件当中（也就是上图的圈中的**config。gradle**上面那一个），具体添加一行代码。

> apply from:"config.gradle"

如下代码块所示，将我们刚刚复制进来的文件 **config.gradle** 导入项目当中。

```groovy
plugins {
    id 'com.android.application' version '7.2.2' apply false
    id 'com.android.library' version '7.2.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.7.10' apply false
    ......
}

apply from:"config.gradle"

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

到这一步的时候，我们点击一下 **Sync Now** 就可以正常构建项目了。

### 3. 添加必要代码
到这里之后，虽然是构建成功了，但是项目App里面并没有导入我们的Module，所以我们需要在 app 目录下的 **build.gradle** 文件里面添加一行导入代码：

```groovy
dependencies {

    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation project(':lib_fast_dev_mvp_kt')
    
    ......
}
```
需要注意的是，导入的这个 “**lib_fast_dev_mvp_kt**” 就是你之前
[**方法二**](https://github.com/leo2777/LeoFastDevMvpKotlin/new/main?readme=1#%E6%96%B9%E6%B3%95%E4%BA%8C%E4%B8%8B%E8%BD%BDdemo%E4%B9%8B%E5%90%8E%E5%80%92%E5%85%A5module%E6%96%B9%E5%BC%8F%E9%9B%86%E6%88%90)
的那个名字（如果添加的时候没有重命名，那就不用管），如果是使用
[**方法一**](https://github.com/leo2777/LeoFastDevMvpKotlin/new/main?readme=1#%E6%96%B9%E6%B3%95%E4%B8%80%E9%80%9A%E8%BF%87-%E5%AF%BC%E5%85%A5%E4%BE%9D%E8%B5%96%E6%96%B9%E5%BC%8F%E9%9B%86%E6%88%90)的，直接复制即可。
添加完之后，再 **Sync Now** 一下，就搞定了。

## 使用方法

### 统一依赖管理

框架采用 config.gradle 进行依赖管理，详细请看此篇文章 [《ndroid - 统一依赖管理(config.gradle)》](https://juejin.cn/post/7224007334513770551) 这里仅仅说明操作步骤：

> - 新建或者下载项目的 「config.gradle」 文件并编写引用
> - 根目录的 「build.gradle」文件引入 「config.gradle」也就是 **apply from "config.gradle"**
> - 在所有module当中的 「build.gradle」文件下，添加活着依赖代码

这里提供对应的示例代码：「config.gradle」

```groovy

ext {

    /**
     * 基础配置 对应 build.gradle 当中 android 括号里面的值
     */
    android = [
            compileSdk               : 32,
            minSdk                   : 21,
            targetSdk                : 32,
            versionCode              : 1,
            versionName              : "1.0.0",
            testInstrumentationRunner: "androidx.test.runner.AndroidJUnitRunner",
            consumerProguardFiles    : "consumer-rules.pro"
    ]

    /**
     * 版本号 包含每一个依赖的版本号，仅仅作用于下面的 dependencies
     */
    version = [
            coreKtx              : "1.7.0",
            appcompat            : "1.6.1",
            material             : "1.8.0",
            constraintLayout     : "2.1.3",
            navigationFragmentKtx: "2.3.5",
            navigationUiKtx      : "2.3.5",
            junit                : "4.13.2",
            testJunit            : "1.1.5",
            espresso             : "3.4.0",
    ]

    /**
     * 项目依赖 可根据项目增加删除，但是可不删除本文件里的，在 build.gradle 不写依赖即可
     * 因为MVP框架默认依赖的也在次文件中，建议只添加，不要删除
     */
    dependencies = [

            coreKtx                    : "androidx.core:core-ktx:$version.coreKtx",
            appcompat                  : "androidx.appcompat:appcompat:$version.appcompat",
            material                   : "com.google.android.material:material:$version.material",
            constraintLayout           : "androidx.constraintlayout:constraintlayout:$version.constraintLayout",
            navigationFragmentKtx      : "androidx.navigation:navigation-fragment-ktx:$version.navigationFragmentKtx",
            navigationUiKtx            : "androidx.navigation:navigation-ui-ktx:$version.navigationUiKtx",
            junit                      : "junit:junit:$version.junit",
            testJunit                  : "androidx.test.ext:junit:$version.testJunit",
            espresso                   : "androidx.test.espresso:espresso-core:$version.espresso",
    ]

}

```

根目录的 「build,gradle」

```groovy

plugins {
    id 'com.android.application' version '7.2.2' apply false
    id 'com.android.library' version '7.2.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.7.10' apply false
}


apply from:"config.gradle"

```
项目 app 下的 「build.gradle」:

```groovy

plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
}

android {
    namespace 'leo.dev.mvp.kt'
    compileSdk rootProject.ext.android.compileSdk

    defaultConfig {
        applicationId "leo.dev.mvp.kt"
        minSdk rootProject.ext.android.minSdk
        targetSdk rootProject.ext.android.targetSdk
        versionCode rootProject.ext.android.versionCode
        versionName rootProject.ext.android.versionName

        testInstrumentationRunner rootProject.ext.android.testInstrumentationRunner

    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    buildFeatures{
        viewBinding true
    }
}

dependencies {

    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation project(':lib_fast_dev_mvp_kt')

    implementation rootProject.ext.dependencies.coreKtx
    implementation rootProject.ext.dependencies.appcompat
    implementation rootProject.ext.dependencies.material

    testImplementation rootProject.ext.dependencies.junit
    androidTestImplementation rootProject.ext.dependencies.testJunit
    androidTestImplementation rootProject.ext.dependencies.espresso
}

```
### 创建项目的 Application

#### 1.继承BaseApplication

创建自己的 Application 继承 **BaseApplication**。注意自己创建的Application不要命名为 **BaseApplication** 因为框架当中已经定义了这个名字。

```kotlin
package leo.dev.mvp.kt.app

import android.view.Gravity
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import es.dmoral.toasty.Toasty
import leo.dev.mvp.kt.common.LoggerAdapter
import leo.dev.mvp.kt.constants.Constants
import leo.study.lib_base.base.BaseApplication
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
```

其中，dataStoreName，sharedPrfName 为扩展函数必备的存储持久化数据保存名字，一个是 dataStore，另外一个是 SharedPreferences，建议两个都写上自定义的名字。

另外如果复制上面的代码，还会有两个地方报错，分别是 LoggerAdapter logger 适配器以及GlideLoaderProcessor glide图片加载代理类，不用着急，继续往下看。

#### 2.创建 logger adapter log日志适配器

随便创建一个类，然后继承 **AndroidLogAdapter**   代码如下（可直接复制，修改文件名）:

```kotlin 

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

```

如果项目是第一次创建，BuildConfig.DEBUG 可能会爆红，直接rebuild一下项目，或者打包一下项目APP即可。

#### 3.创建图片加载代理类

创建一个类，继承 **ImageProxy** 类，实现里面所有的加载方法即可。代码如下：

```kotlin
open class GlideLoaderProcessor(options: ImageOptions?) : ImageProxy {

    private var proxy: ImageProxy? = null;

    private val options: ImageOptions;

    private val requestOptions: RequestOptions = RequestOptions()


    init {
        this.options = options!!
        setShareOptions()
    }

    /**
     * 设置共享的options
     */
    private fun setShareOptions() {
        //设置是否局部裁剪显示
        if (options.isCenterCrop) requestOptions.centerCrop().options
        //设置是否全图居中
        if (options.isCenterInside) requestOptions.centerInside().options
        //设置加载错误图片
        if (options.errorResId != 0) requestOptions.error(options.errorResId).options
        //设置加载中显示的图片
        if (options.placeholderResId != 0) requestOptions.placeholder(options.placeholderResId).options
        //设置加载的宽高
        if (options.targetHeight != 0 && options.targetWidth != 0) requestOptions.override(
            options.targetWidth,
            options.targetHeight
        ).options


        //·······  如果还需要更多属性设置，请完善ImgLoaderOptions类之后，再进行操作
    }


    private fun obtain(): ImageProxy {
        return this
    }


    /**
     * 加载图片  网络地址
     * @param [view] 显示的view
     * @param [path] 加载地址
     * @return 当前的代理类
     */
    override fun loadImage(view: View?, path: String?): ImageProxy {
        if (view is ImageView) {
            options.context?.let {
                Glide
                    .with(it)
                    .setDefaultRequestOptions(requestOptions)
                    .load(path)
                    .into(view)
            }
        }
        return obtain()
    }

    /**
     * 加载资源图， R.drawable...
     * @param [view] 显示的view
     * @param [drawable] 资源地址
     * @return 当前的代理类
     */
    override fun loadImage(view: View?, drawable: Int): ImageProxy {
        if (view is ImageView) {
            options.context?.let {
                Glide
                    .with(it)
                    .setDefaultRequestOptions(requestOptions)
                    .asDrawable()
                    .load(drawable)
                    .into(view)
            }
        }
        return obtain()
    }

    /**
     * 显示图片文件
     * @param [view] 显示的view
     * @param [file] 资源文件
     * @return 当前的代理类
     */
    override fun loadImage(view: View?, file: File?): ImageProxy {
        if (view is ImageView) {
            options.context?.let {
                Glide
                    .with(it)
                    .setDefaultRequestOptions(requestOptions)
                    .asFile()
                    .load(file)
                    .into(view)
            }
        }
        return obtain()
    }

    /**
     * 加载GIF
     * @param [view] 显示的view
     * @param [url] 图片地址
     * @return 当前代理类
     */
    override fun loadGif(view: View?, url: String?): ImageProxy {
        if (view is ImageView) {
            options.context?.let {
                Glide
                    .with(it)
                    .setDefaultRequestOptions(requestOptions)
                    .asGif()
                    .load(url)
                    .into(view)
            }
        }

        return obtain()
    }

    /**
     * 加载自定义高宽图片
     * @param [view] 显示的view
     * @param [url] 图片地址
     * @param [width] 图片宽度 px
     * @param [height] 图片高度 px
     * @return 当前代理类
     */
    override fun loadTargetWidthAndHeight(
        view: View?,
        url: String?,
        width: Int,
        height: Int,
    ): ImageProxy {
        if (view is ImageView) {
            options.context?.let {
                Glide
                    .with(it)
                    .setDefaultRequestOptions(requestOptions)
                    .load(url)
                    .override(width, height)
                    .into(view)
            }
        }
        return obtain()
    }

    /**
     * 加载缩略图
     * @param [view] 显示的view
     * @param [url] 图片地址
     * @param [scan] 缩略数，百分比
     * @return 当前代理类
     */
    override fun loadThumb(view: View?, url: String?, scan: Float): ImageProxy {
        if (view is ImageView) {
            options.context?.let {
                Glide
                    .with(it)
                    .setDefaultRequestOptions(requestOptions)
                    .load(url)
                    .thumbnail(scan)
                    .into(view)
            }
        }
        return obtain()
    }

    /**
     * 加载圆形图片
     * @param [view] 显示view
     * @param [url] 图片地址
     * @return 当前代理类
     */
    override fun loadCircleImage(view: View?, url: String?): ImageProxy {
        if (view is ImageView) {
            options.context?.let {
                Glide
                    .with(it)
                    .setDefaultRequestOptions(requestOptions)
                    .applyDefaultRequestOptions(RequestOptions.bitmapTransform(CircleCrop()))
                    .load(url)
                    .into(view)
            }
        }
        return obtain()
    }

    override fun loadCircleImage(view: View?, url: Int?): ImageProxy {
        if (view is ImageView) {
            options.context?.let {
                Glide
                    .with(it)
                    .setDefaultRequestOptions(requestOptions)
                    .applyDefaultRequestOptions(RequestOptions.bitmapTransform(CircleCrop()))
                    .load(url)
                    .into(view)
            }
        }
        return obtain()
    }

    /**
     * 加载圆角图片
     * @param [view] 显示的view
     * @param [url] 图片地址
     * @param [shapeValue] 半径值
     * @return 当前代理类
     */
    override fun loadRoundImage(view: View?, url: String?, shapeValue: Int): ImageProxy {
        if (view is ImageView) {
            options.context?.let {
                Glide
                    .with(it)
                    .setDefaultRequestOptions(requestOptions)
                    .applyDefaultRequestOptions(
                        RequestOptions.bitmapTransform(
                            RoundedCorners(
                                shapeValue
                            )
                        )
                    )
                    .load(url)
                    .into(view)
            }
        }
        return obtain()
    }

    /**
     * 获取drawable
     *
     * @param [url]      地址
     * @param [loaderCallback] 结果回调
     * @return 当前代理类
     */
    override fun getDrawable(url: String?, loaderCallback: ImageLoaderCallback?): ImageProxy {
        Glide
            .with(options.context!!)
            .load(url)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    loaderCallback!!.onFail("图片下载失败！")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    if (resource != null) {
                        loaderCallback!!.onSuccess(resource, "图片下载成功！")
                        return false
                    }
                    loaderCallback!!.onFail("图片下载失败！")
                    return false
                }
            })
            .submit()
        return obtain()
    }

    /**
     * 设置图片加载参数
     *
     * @return 返回当前
     */
    override fun setImgLoaderOptions(options: ImageOptions?): ImageProxy {
        return proxy!!.setImgLoaderOptions(options)
    }

    /**
     * 清理内存缓存
     * @return 当前代理类
     */
    override fun clearMemoryCache(): ImageProxy {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                options.context?.let {
                    Glide.get(it)
                        .clearMemory()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return obtain()
    }

    /**
     * 清理磁盘缓存
     * @return 当前代理类
     */
    override fun clearDiskCache(): ImageProxy {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Thread {
                    options.context?.let { Glide.get(it).clearDiskCache() }
                }.start()
            } else {
                options.context?.let {
                    Glide.get(it)
                        .clearDiskCache()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return obtain()
    }

}
```

示例代码当中采用的是 glide 图片加载，如果项目是其他图片加载框架，只需要把实现方法切换即可。关于图片加载，下文进阶使用会讲， 可先跳转观看。

### 推荐的MVP项目结构

步骤到这里，基本就可直接创建Activity了，但是推荐采用如下的包结构，并且有自动创建模版插件使用。

