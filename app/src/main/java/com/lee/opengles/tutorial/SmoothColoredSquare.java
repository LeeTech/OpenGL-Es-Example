package com.lee.opengles.tutorial;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: SmoothColoredSquare
 * Description TODO SmoothColoredSquare
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/14 16:01
 */
public class SmoothColoredSquare extends Square {

    // 遵循RGBA模式,颜色定义顺序与顶点顺序是一致的
    private float colors[] = {
            1.0f, 0.0f, 0.0f, 1.0f, // v0 color
            0.0f, 1.0f, 0.0f, 1.0f, // v2 color
            0.0f, 0.0f, 1.0f, 1.0f, // v2 color
            1.0f, 0.0f, 0.0f, 1.0f  // v3 color
    };

    private FloatBuffer mColorBuffer;

    public SmoothColoredSquare() {
        // 把颜色放入Buffer中,提供性能
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asFloatBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);
    }

    @Override
    public void draw(GL10 gl) {
        // 启动颜色数组定义
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
        super.draw(gl);
        // 关闭颜色数组定义
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
