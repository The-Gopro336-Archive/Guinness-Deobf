package org.newdawn.slick.geom;

class Triangle
{
    int[] v;
    
    Triangle(final int i, final int j, final int k) {
        (this.v = new int[3])[0] = i;
        this.v[1] = j;
        this.v[2] = k;
    }
}
