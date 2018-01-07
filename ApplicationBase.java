package com.example.kang.playground;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by kang on 2018. 1. 3..
 */

//폰트 적용하는법
    // project structure 에서 dependencies -> com.tsengvn:typekit:1.0.1 라이브러리 추가한 후에 이 클래스 만들기.
    // 폰트들은 assets 폴더에 담기
public class ApplicationBase extends Application {
    @Override public void onCreate(){
        super.onCreate();

        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "NanumBarunpenR.ttf")).addBold(Typekit.createFromAsset(this, "NanumBarunpenB.ttf"));
    }
}
