package com.lee.opengles.renderer;

import android.opengl.GLU;

import com.lee.opengles.mesh.Star;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: StartRenderer
 * Description TODO StartRenderer
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/29 16:42
 */
public class StartRenderer extends OpenGLRenderer {

    private Star sun = new Star();
    private Star earth = new Star();
    private Star moon = new Star();

    private int angle = 0;


    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);

        gl.glTranslatef(0, 0, 6);

        /**
         * 第一组eyex, eyey,eyez 相机在世界坐标的位置(距离物体的位置)
         *
         * 第二组centerx,centery,centerz 相机镜头对准的物体在世界坐标的位置(眼睛看的物体的位置)
         *
         * 第三组upx,upy,upz 相机向上的方向在世界坐标中的方向(头顶朝向的方向)
         */
        GLU.gluLookAt(gl,
                0.0f, 10.0f, 5.0f,
                0.0f, 0.0f, 0.0f,
                1.0f, 1.0f, 0.0f);

        gl.glPushMatrix();
        gl.glRotatef(angle, 0, 0, 1);
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        sun.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(3, 0, 0);
        gl.glScalef(.5f, .5f, .5f);
        gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
        earth.draw(gl);


        gl.glPushMatrix();
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(2, 0, 0);
        gl.glScalef(.5f, .5f, .5f);
        gl.glRotatef(angle * 10, 0, 0, 1);
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        moon.draw(gl);


        gl.glPopMatrix();
        gl.glPopMatrix();


        angle++;

    }
}
