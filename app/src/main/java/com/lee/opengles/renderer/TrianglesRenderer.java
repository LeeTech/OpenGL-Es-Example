package com.lee.opengles.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: TrianglesRenderer
 * Description TODO TrianglesRenderer
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/27 16:28
 */
public class TrianglesRenderer extends OpenGLRenderer {

    float vertexArray[] = {
            -0.8f, -0.4f * 1.732f, 0.0f,
            0.0f, -0.4f * 1.732f, 0.0f,
            -0.4f, 0.4f * 1.732f, 0.0f,

            0.0f, -0.0f * 1.732f, 0.0f,
            0.8f, -0.0f * 1.732f, 0.0f,
            0.4f, 0.4f * 1.732f, 0.0f,
    };


    private FloatBuffer vertex;

    public TrianglesRenderer() {
        ByteBuffer vbb
                = ByteBuffer.allocateDirect(vertexArray.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertex = vbb.asFloatBuffer();
        vertex.put(vertexArray);
        vertex.position(0);
    }


    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);

        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -8);


        drawTriangles(gl);

        drawTrianglesStrip(gl);

        drawTrianglesLoop(gl);
    }

    // 顶点两两连接
    private void drawTriangles(GL10 gl) {

        gl.glPushMatrix();

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
        gl.glTranslatef(-1.0f, 2.0f, 0);
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glPopMatrix();
    }

    // 绘制一系列线段
    private void drawTrianglesStrip(GL10 gl) {

        gl.glPushMatrix();

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
        gl.glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
        gl.glTranslatef(1.0f, 2.0f, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 6);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glPopMatrix();
    }

    // 首尾相连，构成一个封闭曲线
    private void drawTrianglesLoop(GL10 gl) {

        gl.glPushMatrix();

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
        gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 6);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glPopMatrix();
    }
}
