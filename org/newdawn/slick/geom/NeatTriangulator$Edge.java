package org.newdawn.slick.geom;

class Edge
{
    int v0;
    int v1;
    int t0;
    int t1;
    boolean suspect;
    
    Edge() {
        this.v0 = -1;
        this.v1 = -1;
        this.t0 = -1;
        this.t1 = -1;
    }
}
