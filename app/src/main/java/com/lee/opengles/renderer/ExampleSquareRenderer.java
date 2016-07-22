package com.lee.opengles.renderer;

import com.lee.opengles.tutorial.FloatColorSquare;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: ExampleSquareRenderer
 * Description TODO ExampleSquareRenderer
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/21 11:33
 */
public class ExampleSquareRenderer extends OpenGLRenderer {

    private FloatColorSquare square;

    public ExampleSquareRenderer() {
        square = new FloatColorSquare();
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);
        square.draw(gl10);
    }
}
