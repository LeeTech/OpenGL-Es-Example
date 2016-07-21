package com.lee.opengles.tutorial;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: Square
 * Description TODO 正方形
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/12 17:57
 */
public class Square {

    // 要绘制的顶点
    private float vertices[] = {
            -1.0f, 1.0f, 0.0f, // v0 Top Left
            -1.0f, -1.0f, 0.f, // v1 Bottom Left
            1.0f, -1.0f, 0.0f, // v2 Bottom Right
            1.0f, 1.0f, 0.0f // v3 Top Right
    };

    // 要绘制顶点的顺序
    private short indices[] = {0, 1, 2, 0, 2, 3};

    // 顶点定义Buffer 使用Buffer提供性能
    private FloatBuffer mVertexBuffer;

    // 顶点绘制顺序 Buffer
    private ShortBuffer mIndexBuffer;

    public Square() {

        // 直接放入缓存区,提供性能,避免回收
        // float类型占4个字节,所以乘以4
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        // 返回当前硬件平台的字节序,保证数据正常解析 (http://blog.sina.com.cn/s/blog_4150f50c0100gfa3.html)
        vbb.order(ByteOrder.nativeOrder());
        // 创建Buffer
        mVertexBuffer = vbb.asFloatBuffer();
        // 设置顶点
        mVertexBuffer.put(vertices);
        // 将buffer内游标置回0，否则OpenGL会从最后一次put的下一个位置开始读取。
        mVertexBuffer.position(0);

        // short类型占2个字节,所以乘以2
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        mIndexBuffer = ibb.asShortBuffer();
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }

    // 绘制正方形方法
    public void draw(GL10 gl) {
        // 设置逆时针为前面
        gl.glFrontFace(GL10.GL_CCW);
        // 启用忽略设置
        gl.glEnable(GL10.GL_CULL_FACE);
        // 指点忽略后面
        gl.glCullFace(GL10.GL_BACK);

        // 启用写入的顶点缓存区,并在使用过程中渲染
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // 设置顶点数据
        //参数1：size, 用于描述顶点的尺寸，(x,y,z)坐标系，所以是3

        //参数2：type, 描述顶点的类型

        //参数3：stride, 描述了步长

        //参数4：pointer, 顶点缓存，即我们创建的顶点数组
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);

        // 开始绘制图形
        // 该方法可以重新定义顶点的顺序，顶点的顺序由indices Buffer 指定。
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, mIndexBuffer);

        // 绘制完成后关闭顶点缓存区
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // 关闭忽略设置
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
