package com.ls.ai.ml;

/**
 * Created by aludlow on 4/10/2015.
 */
public interface ParameterSet<T> {
    public int numParams();
    public T get(int index);
    public void add(T param);
}
