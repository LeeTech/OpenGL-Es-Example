package com.lee.opengles.renderer;

import com.lee.opengles.mesh.Cube;

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
        super.onDrawFrame(gl10);
        gl10.glTranslatef(0, 0, 6);
        getRoot().draw(gl10);
    }
}
