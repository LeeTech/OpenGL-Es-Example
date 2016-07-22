package com.lee.opengles.mesh;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: Mesh
 * Description Mesh(所有空间形体最基本的构成元素 三角形网格)
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/15 16:48
 */
public class Mesh {

    // 顶点定义
    private FloatBuffer mVerticesBuffer = null;

    // 顶点绘制顺序定义
    private ShortBuffer mIndicesBuffer = null;

    // 材质渲染UV坐标定义
    private FloatBuffer mTextureBuffer;

    private int mNumOfIndices = -1;

    // 颜色定义
    private float rgba[] = new float[]{1.0f, 1.0f, 1.0f, 1.0f};

    // 颜色矩阵定义
    private FloatBuffer mColorBuffer = null;

    // 材质id,通过此id绑定,并渲染此材质
    private int mTextureId = -1;

    // 需要渲染的bitmap对象
    private Bitmap mTextureBitmap;

    // 是否渲染材质
    private boolean mShouldLoadTexture = false;

    // 平移参数
    public float x = 0;

    public float y = 0;

    public float z = 0;

    // 旋转参数
    public float rx = 0;

    public float ry = 0;

    public float rz = 0;

    public void draw(GL10 gl) {
        // 设置逆时针为前面
        gl.glFrontFace(GL10.GL_CCW);
        // 启用忽略设置
        gl.glEnable(GL10.GL_CULL_FACE);
        // 指点忽略后面
        gl.glCullFace(GL10.GL_BACK);

        // 启用写入的顶点缓存区,并在使用过程中渲染
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVerticesBuffer);

        gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);

        if (mColorBuffer != null) {
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);

        }

        if (mShouldLoadTexture) {
            loadGLTexture(gl);
            mShouldLoadTexture = false;
        }

        if (mTextureId != -1 && mTextureBuffer != null) {
            // 启用材质渲染
            gl.glEnable(GL10.GL_TEXTURE_2D);
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
        }

        gl.glTranslatef(x, y, z);

        gl.glRotatef(rx, 1.0f, 0.0f, 0.0f);

        gl.glRotatef(ry, 0.0f, 1.0f, 0.0f);

        gl.glRotatef(rz, 0.0f, 0.0f, 1.0f);

        // 开始绘制图形
        // 该方法可以重新定义顶点的顺序，顶点的顺序由indices Buffer 指定。
        gl.glDrawElements(GL10.GL_TRIANGLES, mNumOfIndices, GL10.GL_UNSIGNED_SHORT, mIndicesBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        if (mTextureId != -1 && mTextureBuffer != null) {
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        }

        gl.glDisable(GL10.GL_CULL_FACE);
    }

    protected void setVertices(float[] vertices) {
        // 直接放入缓存区,提供性能,避免回收
        // float类型占4个字节,所以乘以4
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        // 返回当前硬件平台的字节序,保证数据正常解析 (http://blog.sina.com.cn/s/blog_4150f50c0100gfa3.html)
        vbb.order(ByteOrder.nativeOrder());
        // 创建Buffer
        mVerticesBuffer = vbb.asFloatBuffer();
        // 设置顶点
        mVerticesBuffer.put(vertices);
        // 将buffer内游标置回0，否则OpenGL会从最后一次put的下一个位置开始读取。
        mVerticesBuffer.position(0);
    }

    protected void setIndices(short[] indices) {
        // short类型占2个字节,所以乘以2
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        mIndicesBuffer = ibb.asShortBuffer();
        mIndicesBuffer.put(indices);
        mIndicesBuffer.position(0);
        mNumOfIndices = indices.length;
    }

    protected void setColor(float red, float green, float blue, float alpha) {
        rgba[0] = red;
        rgba[1] = green;
        rgba[2] = blue;
        rgba[3] = alpha;
    }

    protected void setColors(float[] colors) {
        ByteBuffer cbb
                = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asFloatBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);
    }

    /**
     * 设置材质渲染坐标
     *
     * @param textureCoordinates
     */
    protected void setTextureCoordinates(float[] textureCoordinates) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(textureCoordinates.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        mTextureBuffer = byteBuffer.asFloatBuffer();
        mTextureBuffer.put(textureCoordinates);
        mTextureBuffer.position(0);
    }

    /**
     * 设置材质渲染位图
     *
     * @param textureBitmap
     */
    public void loadTextureBitmap(Bitmap textureBitmap) {
        this.mTextureBitmap = textureBitmap;
        mShouldLoadTexture = true;
    }

    /**
     * 加载材质
     *
     * @param gl
     */
    private void loadGLTexture(GL10 gl) {
        int[] textures = new int[1];
        // 生成材质,只生成一个
        gl.glGenTextures(1, textures, 0);
        mTextureId = textures[0];
        // 通过id绑定此材质
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);
        // 设置填充材质参数
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

        /**
         * target ---- 操作的目标类型，设为 GL_TEXTURE_2D 即可

         level ---- 表示多级分辨率的纹理图像的级数，若只有一种分辨率，则level设为0

         bitmap ---- 图像

         border ---- 边框，一般设为0
         */
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mTextureBitmap, 0);
    }
}
