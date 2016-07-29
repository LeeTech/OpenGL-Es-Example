package com.lee.opengles.mesh;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: Star
 * Description TODO Star
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/29 15:46
 */
public class Star {

    protected float vertices[];

    protected FloatBuffer vertexBuffer;


    public Star() {
        float a = (float) (1.0f / (2.0f - 2f * Math.cos(72f * Math.PI / 180.f)));
        float bx = (float) (a * Math.cos(18 * Math.PI / 180.0f));
        float by = (float) (a * Math.sin(18 * Math.PI / 180f));
        float cy = (float) (-a * Math.cos(18 * Math.PI / 180f));

        vertices = new float[]{
                0, a, 0.5f, cy, -bx, by, bx, by, -0.5f, cy
        };

        ByteBuffer vbb
                = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // 只绘制直线,所以顶点数设置为2
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);
        // 绘制首尾相连的线
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 5);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }

}
