package com.leo.mvp.main

import leo.study.lib_base.mvp.IModel
import leo.study.lib_base.mvp.IPresenter
import leo.study.lib_base.mvp.IView

/**
 *
 * ***********************************************************************
 * the project desc:
 *
 *
 * this name is Contract
 * this path is com.leo.mvp.main.Contract
 * this desc: 本自动生成代码，基于 leoMvpKotlin 框架，请添加相对应的依赖
 * this URL: https://github.com/leo2777/leo_kotlin_mvp_demo
 * ***********************************************************************
 */
interface Contract {

    /**
     * 此处编写View层回调方法，用于 Activity/Fragment 当中
     *
     **/
    interface View : IView<Presenter> {

    }

    /**
     * 此处编写Presenter层方法，逻辑处理
     *
     **/
    interface Presenter : IPresenter<View, Model> {

    }

    /**
     * 此处编写Model层方法，获取数据
     *
     **/
    interface Model : IModel {
    }
}