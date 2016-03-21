package com.ls.ai.ml;

import java.util.List;

/**
 * Created by work on 15/10/2015.
 */
public interface DataStream {
    public List<byte[]> getNextRecord();
    public void reset();

    public List<Integer> getFeatureIndexList();

    public void setFeatureIndexList(List<Integer> parameterIndexList);

    public Integer getNumberOfTrainingExamples();
    public Integer getNumberOfFeatures();

    public void setNumberOfTrainingExamples(Integer numberOfTrainingExamples);

    public Integer getTargetIndex();

    public void setTargetIndex(Integer targetParameterIndex);
}
