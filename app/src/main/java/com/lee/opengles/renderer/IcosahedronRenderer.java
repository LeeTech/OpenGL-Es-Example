package com.lee.opengles.renderer;

import com.lee.opengles.mesh.Icosahedron;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: IcosahedronRenderer
 * Description TODO IcosahedronRenderer
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/29 10:30
 */
public class IcosahedronRenderer extends OpenGLRenderer {

    private float angle = 0;

    public IcosahedronRenderer() {
        Icosahedron icosahedron = new Icosahedron();
        addMesh(icosahedron);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);

        gl10.glTranslatef(0, 0, 6);

        gl10.glRotatef(angle, 0, 1, 0);

        getRoot().draw(gl10);

        angle++;
    }
}
