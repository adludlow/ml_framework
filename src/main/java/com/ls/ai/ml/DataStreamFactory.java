package com.ls.ai.ml;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by aludlow on 28/11/2015.
 */
public class DataStreamFactory {
    public enum DataStreamType {
        CSVDataStream
    }

    public static DataStream getDataStream(DataStreamType dataStreamType, String config) {
        DataStream dataStream = null;
        try {
            switch (dataStreamType) {
                case CSVDataStream:
                    dataStream = new ObjectMapper().readValue(config, CSVDataStream.class);
                    break;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return dataStream;
    }
}
