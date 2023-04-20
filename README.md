# LeoFastDevMvpKotlin

一款基于MVP架构的快速应用开发框架，kotlin版本

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







    
    
