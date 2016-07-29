package com.lee.opengles.mesh;

/**
 * ClassName: Icosahedron
 * Description TODO 二十面体
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/28 18:23
 */
public class Icosahedron extends Mesh {

    static final float X = .525731112119133606f;
    static final float Z = .850650808352039932f;

    float[] colors = {
            0f, 0f, 0f, 1f,
            0f, 0f, 1f, 1f,
            0f, 1f, 0f, 1f,
            0f, 1f, 1f, 1f,
            1f, 0f, 0f, 1f,
            1f, 0f, 1f, 1f,
            1f, 1f, 0f, 1f,
            1f, 1f, 1f, 1f,
            1f, 0f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 0f, 1f, 1f,
            1f, 0f, 1f, 1f

    };

    static float vertices[] = new float[]{
            -X, 0.0f, Z,
            X, 0.0f, Z,
            -X, 0.0f, -Z,
            X, 0.0f, -Z,
            0.0f, Z,
            X, 0.0f, Z,
            -X, 0.0f, -Z,
            X, 0.0f, -Z,
            -X, Z,
            X, 0.0f, -Z,
            X, 0.0f, Z,
            -X, 0.0f, -Z,
            -X, 0.0f
    };

    static short indices[] = new short[]{
            0, 4, 1, 0, 9, 4, 9, 5, 4, 4, 5, 8, 4, 8, 1,
            8, 10, 1, 8, 3, 10, 5, 3, 8, 5, 2, 3, 2, 7, 3,
            7, 10, 3, 7, 6, 10, 7, 11, 6, 11, 0, 6, 0, 1, 6,
            6, 1, 10, 9, 0, 11, 9, 11, 2, 9, 2, 5, 7, 2, 11};

    public Icosahedron() {
        setVertices(vertices);
        setIndices(indices);
        setColors(colors);
    }
}
