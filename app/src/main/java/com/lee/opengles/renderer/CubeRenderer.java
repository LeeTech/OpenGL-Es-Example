package com.lee.opengles.renderer;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.lee.opengles.mesh.Cube;
import com.lee.opengles.mesh.Group;
import com.lee.opengles.mesh.Mesh;
import com.lee.opengles.mesh.Plane;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: CubeRenderer
 * Description TODO 正方体
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/20 10:39
 */
public class CubeRenderer implements GLSurfaceView.Renderer {

    private Mesh root;

    public CubeRenderer() {
        Group group = new Group();
        Cube cube = new Cube(1, 1, 1);
        cube.rx = 45;
        cube.ry = 45;
        group.add(cube);

//        正方形
//        Plane plane = new Plane(1, 1, 1, 1);
//        group.add(plane);
        root = group;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        gl10.glShadeModel(GL10.GL_SMOOTH);
        gl10.glClearDepthf(1.0f);
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        gl10.glDepthFunc(GL10.GL_LEQUAL);
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        gl10.glViewport(0, 0, width, height);
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();
        GLU.gluPerspective(gl10, 45.0f, (float) width / (float) height, 0.1f,
                100.0f);
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl10.glLoadIdentity();
        gl10.glTranslatef(0, 0, -4);
        root.draw(gl10);
    }
}
