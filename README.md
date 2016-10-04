# JavaWaveFunctionCollapse

This is a straight port of [mxgmn](https://github.com/mxgmn)'s [WaveFunctionCollapse](https://github.com/mxgmn/WaveFunctionCollapse) to Java. It should be fully functional, with the exception of the following bugs:

* `overlapping` models with the `foundation` parameter set to a non-default value do not work for an unknown reason. Insight into the cause of this bug would be appreciated.
* The `3Bricks` sample does not work due to a bug in `javax.imageio` which prevents loading of the `.bmp` file.