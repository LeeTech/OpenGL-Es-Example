package com.lee.opengles.mesh;

/**
 * ClassName: TexturePlane
 * Description TODO TexturePlane
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/22 11:32
 */
public class TexturePlane extends Plane {

    public TexturePlane() {
        super(3, 3);

        float textureCoordinates[] = {0.0f, 2.0f,
                2.0f, 2.0f,
                0.0f, 0.0f,
                2.0f, 0.0f};

        setTextureCoordinates(textureCoordinates);
    }
}
