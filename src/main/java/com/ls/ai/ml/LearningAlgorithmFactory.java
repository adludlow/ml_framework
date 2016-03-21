package com.ls.ai.ml;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by aludlow on 28/11/2015.
 */
public class LearningAlgorithmFactory {
    public enum AlgorithmType {
        GRADIENT_DESCENT
    }

    public static LearningAlgorithm getLearningAlgorithm(AlgorithmType algorithm, String config) {
        LearningAlgorithm learningAlgorithm = null;
        try {
            switch (algorithm) {
                case GRADIENT_DESCENT:
                    learningAlgorithm = new ObjectMapper().readValue(config, BatchGradientDescent.class);
                    break;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return learningAlgorithm;
    }
}
