package com.lee.opengles.renderer;

import com.lee.opengles.tutorial.SmoothColoredSquare;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: SmoothColoredSquareRenderer
 * Description TODO SmoothColoredSquareRenderer
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/22 15:48
 */
public class SmoothColoredSquareRenderer extends OpenGLRenderer {

    private SmoothColoredSquare smoothColoredSquare;

    private float angle = 10.0f;

    public SmoothColoredSquareRenderer() {
        smoothColoredSquare = new SmoothColoredSquare();
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);
        // 三角形 A
        // 保存当前矩阵
        gl10.glPushMatrix();
        gl10.glRotatef(angle, 0.0f, 0.0f, 1.0f);
        smoothColoredSquare.draw(gl10);
        // 恢复最后一次的矩阵设置
        gl10.glPopMatrix();


        // 三角形 B
        // 保存当前矩阵
        gl10.glPushMatrix();
        gl10.glRotatef(-angle, 0.0f, 0.0f, 1.0f);
        gl10.glTranslatef(2.0f, 0, 0);
        gl10.glScalef(0.5f, 0.5f, 0.5f);
        smoothColoredSquare.draw(gl10);

        // 三角形 C
        // 保存当前矩阵
        gl10.glPushMatrix();
        gl10.glRotatef(-angle, 0, 0, 1);
        gl10.glTranslatef(2, 0, 0);
        gl10.glScalef(0.5f, 0.5f, 0.5f);
        gl10.glRotatef(angle * 10, 0, 0, 1);
        smoothColoredSquare.draw(gl10);

        // 恢复C的矩阵设置
        gl10.glPopMatrix();

        // 恢复B的矩阵设置
        gl10.glPopMatrix();

        angle++;
    }
}
