package com.leo.mvp.main

import leo.study.lib_base.mvp.BasePresenter
import com.leo.mvp.main.Contract
import com.leo.mvp.main.Model

/**
 *
 * ***********************************************************************
 * the project desc:
 *
 *
 * this name is Presenter
 * this path is com.leo.mvp.main.Presenter
 * this desc: 本自动生成代码，基于 leoMvpKotlin 框架，请添加相对应的依赖
 * this URL: https://github.com/leo2777/leo_kotlin_mvp_demo
 * ***********************************************************************
 */
class Presenter : BasePresenter<Contract.View>(), Contract.Presenter {

    // model层
    override var model: Contract.Model? = Model()
}