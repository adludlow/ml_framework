package com.ls.ai.ml;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aludlow on 28/10/2015.
 */
public class BatchGradientDescent implements LearningAlgorithm{
    private Double learningRate;
    private Integer numberOfIterations;
    private Double zeroWeight;
    private List<Weight> weights;
    private DataStream dataStream;
    private URL propertyFile;

    public void setDataStream(DataStream dataStream) {
        this.dataStream = dataStream;
    }

    public Double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(Double learningRate) {
        this.learningRate = learningRate;
    }

    public Integer getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(Integer numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public List<Weight> getWeights() {
        return weights;
    }

    public void setWeights(List<Weight> weights) {
        this.weights = weights;
    }

    public Double getZeroWeight() {
        return zeroWeight;
    }

    public void setZeroWeight(Double zeroWeight) {
        this.zeroWeight = zeroWeight;
    }

    public void learn() {
        if(dataStream == null){
            throw new IllegalArgumentException("dataStream not initialised.");
        }

        int numTrainingExamples = dataStream.getNumberOfTrainingExamples();
        for(int c = 0; c < numberOfIterations; c++) {
            for(int j = 0; j < weights.size(); j++) {
                Double sumCost = 0.0;
                for(int m = 0; m < numTrainingExamples; m++) {
                    List<byte[]> currentTrainingExample = new ArrayList<byte[]>(dataStream.getNextRecord());
                    currentTrainingExample.add(0, new String("1.0").getBytes());
                    int trainingExampleIndexOffset = 1;
                    Double hypothesis = 0.0;
                    for(int i=0; i < dataStream.getNumberOfFeatures()+trainingExampleIndexOffset; i++) {
                        Double currentWeight = weights.get(i).getValue();
                        Double currentFeatureVal = 0.0;
                        if(i == 0) {
                            currentFeatureVal = Double.parseDouble(new String(currentTrainingExample.get(i)));
                        }
                        else {
                            int currentFeatureIndex = dataStream.getFeatureIndexList().get(i-1);
                            currentFeatureVal = Double.parseDouble(new String(currentTrainingExample.get(currentFeatureIndex+1)));
                        }
                        hypothesis += currentWeight * currentFeatureVal;
                    }
                    Double targetValue = Double.parseDouble(new String(currentTrainingExample.get(dataStream.getTargetIndex()+trainingExampleIndexOffset)));
                    Double localCost = hypothesis - targetValue;
                    Double activeFeature = Double.parseDouble(new String(currentTrainingExample.get(j)));
                    if(activeFeature == 0.0)
                        activeFeature = 1.0;

                    localCost = localCost * activeFeature;
                    sumCost += localCost;
                }
                System.out.format("Iteration: %d sumCost: %.15f%n", c, sumCost);
                Double newWeight = weights.get(j).getValue() - (learningRate * sumCost);
                weights.get(j).setValue(newWeight);
                dataStream.reset();
            }
        }
        printWeights();
    }

    public void printWeights(){
        for(Weight w : weights){
            System.out.format("%.5f ", w.getValue());
        }
    }
}
