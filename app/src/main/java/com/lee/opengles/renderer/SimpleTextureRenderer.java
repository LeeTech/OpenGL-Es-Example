package com.lee.opengles.renderer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.lee.opengles.R;
import com.lee.opengles.mesh.Cube;
import com.lee.opengles.mesh.Group;
import com.lee.opengles.mesh.Mesh;
import com.lee.opengles.mesh.TexturePlane;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: CubeRenderer
 * Description TODO SimpleTextureRenderer
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/20 10:39
 */
public class SimpleTextureRenderer extends OpenGLRenderer {

    public SimpleTextureRenderer(Resources res) {
        TexturePlane plane = new TexturePlane();
        Bitmap bitmap = BitmapFactory.decodeResource(res,
                R.mipmap.jay);
        plane.z = 1.7f;
        plane.rx = -65;
        plane.loadTextureBitmap(bitmap);
        addMesh(plane);
    }


    @Override
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);
        getRoot().draw(gl10);
    }
}
