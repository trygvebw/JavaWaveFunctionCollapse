package com.aqwis.models;

import java.awt.image.BufferedImage;

/**
 * Created by trygvewiig on 03/10/16.
 */
public class SimpleTiledWFCModel extends WFCModel {
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

    public SimpleTiledWFCModel(String name, String subsetName, int width, int height, boolean periodic, boolean black) {
        //
    }
}
