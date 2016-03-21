package com.ls.ai.ml;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by aludlow on 10/10/2015.
 */
public class CSVDataStream implements DataStream {

    private List<Integer> parameterIndexList;
    private Integer numberOfTrainingExamples;
    private Integer targetParameterIndex;

    private static final String[] EMPTY_ARRAY = new String[0];

    private File _file = null;
    private String [] _headerMapping = EMPTY_ARRAY;
    private String _csvString = "";
    private CSVParser csvParser;
    private boolean initialised = false;
    private int startIndex = 0;
    private Iterator<CSVRecord> recIterator;
    private CSVFormat csvFileFormat = null;

    private List<List<byte[]>> recordCache;
    private Iterator<List<byte[]>> cacheIterator = null;
    private boolean usingCache = false;

    public CSVDataStream(){
        recordCache = new ArrayList<List<byte[]>>();
    }

    @Override
    public List<Integer> getFeatureIndexList() {
        return parameterIndexList;
    }

    @Override
    public void setFeatureIndexList(List<Integer> parameterIndexList) {
        this.parameterIndexList = parameterIndexList;
    }

    @Override
    public Integer getNumberOfFeatures() {
       return getFeatureIndexList().size();
    }

    @Override
    public Integer getNumberOfTrainingExamples() {
        return numberOfTrainingExamples;
    }

    @Override
    public void setNumberOfTrainingExamples(Integer numberOfTrainingExamples) {
        this.numberOfTrainingExamples = numberOfTrainingExamples;
    }

    @Override
    public Integer getTargetIndex() {
        return targetParameterIndex;
    }

    @Override
    public void setTargetIndex(Integer targetParameterIndex) {
        this.targetParameterIndex = targetParameterIndex;
    }

    public CSVDataStream setFile(File file) {
        this._file= file;
        return this;
    }

    public CSVDataStream setCSVString(String csvString) {
        this._csvString = csvString;
        return this;
    }

    public CSVDataStream setHeaderMapping(String [] headerMapping) {
        this._headerMapping = headerMapping;
        return this;
    }

    @Override
    public void reset() {
        cacheIterator = recordCache.iterator();
        usingCache = true;
    }

    @Override
    public List<byte[]> getNextRecord(){
        if(!initialised){
            try {
                initStream();
            }
            catch(IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        if(usingCache){
            if(cacheIterator == null) {
                cacheIterator = recordCache.iterator();
            }
            if(cacheIterator.hasNext()) {
                return cacheIterator.next();
            }
            else {
                return null;
            }
        }
        else {
            List<byte[]> rec = new ArrayList<byte[]>();
            if (recIterator.hasNext()) {
                CSVRecord csvRec = recIterator.next();
                for (int i = 0; i < csvRec.size(); i++) {
                    rec.add(csvRec.get(i).getBytes(StandardCharsets.UTF_8));
                }
                recordCache.add(rec);
                return rec;
            }
            return null;
        }
    }

    private void initStream() throws IOException{
           if(!_headerMapping.equals(EMPTY_ARRAY)){
               csvFileFormat = CSVFormat.DEFAULT.withHeader(_headerMapping);
           }
           else {
               csvFileFormat = CSVFormat.DEFAULT;
           }

           if(!(_file == null)) {
               FileReader fileReader = new FileReader(_file);
               csvParser = new CSVParser(fileReader, csvFileFormat);
           }
           else if(!_csvString.isEmpty()) {
              csvParser = CSVParser.parse(_csvString, csvFileFormat);
           }
           //List<CSVRecord> csvRecords = csvFileParser.getRecords();
           initialised = true;

            recIterator = csvParser.iterator();

           if(!_headerMapping.equals(EMPTY_ARRAY))
               startIndex = 1;
                recIterator.next();

    }
}
