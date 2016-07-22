package com.lee.opengles;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lee.opengles.renderer.OpenGLRenderer;
import com.lee.opengles.renderer.SmoothColoredSquareRenderer;

public class SquareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new SmoothColoredSquareRenderer());
        setContentView(glSurfaceView);
    }
}
