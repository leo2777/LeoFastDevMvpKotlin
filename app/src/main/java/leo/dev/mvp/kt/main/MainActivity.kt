package com.leo.mvp.main

import leo.study.lib_base.mvp.BaseMvpActivity
import com.leo.mvp.main.Contract
import com.leo.mvp.main.Presenter

/**
 *
 * ***********************************************************************
 * the project desc:
 *
 *
 * this name is MainActivity
 * this packageName is
 * this path is com.leo.mvp.main.MainActivity
 * this desc: 本自动生成代码，基于 leoMvpKotlin 框架，请添加相对应的依赖
 * this URL: https://github.com/leo2777/leo_kotlin_mvp_demo
 * ***********************************************************************
 */
class MainActivity : BaseMvpActivity<ActivityMainBinding, Contract.View, Contract.Presenter>(),
    Contract.View {

    override var presenter: Contract.Presenter = Presenter()

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initData() {

    }
}