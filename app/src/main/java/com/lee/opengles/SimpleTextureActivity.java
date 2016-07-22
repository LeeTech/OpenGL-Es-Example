package com.lee.opengles;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lee.opengles.renderer.SimpleTextureRenderer;

/**
 * ClassName: SimpleTextureActivity
 * Description TODO 请描述这个文件
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/22 11:46
 */
public class SimpleTextureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new SimpleTextureRenderer(getResources()));
        setContentView(glSurfaceView);
    }
}
