package com.ls.ai.ml;

import java.io.File;
import java.net.URL;
import java.util.Properties;

/**
 * Created by aludlow on 4/10/2015.
 */
public interface LearningAlgorithm {
    public void setDataStream(DataStream dataStream);
    public void learn();
}
