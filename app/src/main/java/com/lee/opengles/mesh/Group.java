package com.lee.opengles.mesh;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

/**
 * ClassName: Group
 * Description TODO Group可以用来管理多个空间几何形体，如果把Mesh比作Android的View ,Group可以看作Android的ViewGroup，Android的View的设计也是采用的“Composite Pattern”。
 * Auther lijun lee mingyangnc@163.com
 * Date 2016/7/18 18:03
 */
public class Group extends Mesh {

    private Vector<Mesh> children = new Vector<>();

    @Override
    public void draw(GL10 gl) {
        int size = children.size();
        for (int i = 0; i < size; i++) {
            children.get(i).draw(gl);
        }
    }

    public void add(int location, Mesh object) {
        children.add(location, object);
    }

    public boolean add(Mesh object) {
        return children.add(object);
    }

    public void clear() {
        children.clear();
    }

    public Mesh get(int location) {
        return children.get(location);
    }

    public Mesh remove(int location) {
        return children.remove(location);
    }

    public boolean remove(Object object) {
        return children.remove(object);
    }

    public int size() {
        return children.size();
    }

}
