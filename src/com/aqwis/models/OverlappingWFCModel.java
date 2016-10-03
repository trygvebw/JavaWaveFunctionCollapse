package com.aqwis.models;

import java.awt.image.BufferedImage;

/**
 * Created by trygvewiig on 03/10/16.
 */
public class OverlappingWFCModel extends WFCModel {
    protected Boolean propagate() {
        return null;
    }

    public boolean run(int seed, int limit) {
        return false;
    }

    protected void clear() {

    }

    protected boolean onBoundary(int x, int y) {
        return false;
    }

    public BufferedImage graphics() {
        return null;
    }

    public OverlappingWFCModel(String name, int N, int width, int height, boolean periodicInput, boolean periodicOutput, int symmetry, int foundation) {
        //
    }
}
