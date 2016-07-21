package com.lee.opengles;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lee.opengles.renderer.ExampleSquareRenderer;

/**
 * ClassName: ExampleActivity
 * Description TODO OpenGl 基本用法(绘制一个正方形)
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/21 11:31
 */
public class ExampleActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new ExampleSquareRenderer());
        setContentView(glSurfaceView);
    }
}
