package com.chaochaowu.facedetect.dagger;

import com.chaochaowu.facedetect.ui.MainActivity;

import dagger.Component;


/**
 * MainActivity 的组件
 */
@Component(modules = {MainPresenterModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
