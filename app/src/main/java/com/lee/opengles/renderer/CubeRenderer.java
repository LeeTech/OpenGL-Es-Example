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
public class CubeRenderer extends OpenGLRenderer {

    public CubeRenderer() {
        Cube cube = new Cube(1, 1, 1);
        cube.rx = 45;
        cube.ry = 45;
        addMesh(cube);
    }


    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl10.glLoadIdentity();
        gl10.glTranslatef(0, 0, -4);
        getRoot().draw(gl10);
    }
}
