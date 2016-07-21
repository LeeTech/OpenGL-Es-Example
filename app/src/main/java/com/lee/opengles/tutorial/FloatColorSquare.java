package com.lee.opengles.tutorial;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: FloatColorSquare
 * Description TODO FloatColorSquare
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/14 15:41
 */
public class FloatColorSquare extends Square {
    @Override
    public void draw(GL10 gl) {
        gl.glColor4f(0.5f, 0.5f, 1f, 1f);
        super.draw(gl);
    }
}
