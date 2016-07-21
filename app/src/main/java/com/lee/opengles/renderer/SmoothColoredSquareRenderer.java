package com.lee.opengles.renderer;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import com.lee.opengles.tutorial.FloatColorSquare;
import com.lee.opengles.tutorial.SmoothColoredSquare;
import com.lee.opengles.tutorial.Square;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: SmoothColoredSquareRenderer
 * Description TODO SmoothColoredSquareRenderer
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/11 14:52
 */
public class SmoothColoredSquareRenderer implements GLSurfaceView.Renderer {

    private final static String TAG = "SmoothColorRenderer";
    private FloatColorSquare square;
    private SmoothColoredSquare smoothColoredSquare;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        // 设置背景 黑色
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        // 设置阴影样式 平滑样式
        gl10.glShadeModel(GL10.GL_SMOOTH);
        // 指定深度缓冲区的清除值,该值将被用于glClear函数清理深度缓冲区。被glClearDepthf指明的值会被clamp至区间[0, 1]
        gl10.glClearDepthf(1.0f);
        // 用于启用各种功能。功能由参数决定
        // 启用深度测试。
        // 根据坐标的远近自动隐藏被遮住的图形（材料）
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        // 指定用于深度缓冲比较值
        // 如果输入的深度值小于或等于参考值，则通过
        gl10.glDepthFunc(GL10.GL_LEQUAL);
        //  该函数控制OpenGL在某一方面有解释的余地时，所采取的操作行为?
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

//        square = new FloatColorSquare();
        smoothColoredSquare = new SmoothColoredSquare();
        Log.d(TAG, "onSurfaceCreated");
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        // 将当前视图设置为新的大小。
        gl10.glViewport(0, 0, width, height);
        // 选择投影矩阵
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        // 重置投影矩阵
        gl10.glLoadIdentity();
        // 计算窗口的纵横比
        GLU.gluPerspective(gl10, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        // 选择的模型视图矩阵
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        // 重置投影矩阵
        gl10.glLoadIdentity();

        Log.d(TAG, "onSurfaceChanged");
    }

    private float angle = 10.0f;

    @Override
    public void onDrawFrame(GL10 gl10) {
        // 颜色和深度数据分别从颜色缓冲区和深度缓冲区中删除。
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl10.glLoadIdentity();
        // 向后移动10个单位
        gl10.glTranslatef(0, 0, -10);

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

        Log.d(TAG, "onDrawFrame");
    }
}
