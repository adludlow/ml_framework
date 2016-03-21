import com.ls.ai.ml.CSVDataStream;
import com.ls.ai.ml.DataStreamFactory;
import org.junit.Test;

import java.util.List;

/**
 * Created by work on 18/10/2015.
 */
public class TestCSVDataStream {
    private TestHelper helper;

    public TestCSVDataStream() {
        helper = new TestHelper();
    }

    @Test
    public void testGetNextRecord() {
        String configString = helper.getFile("testDatasetProperties.json");
        CSVDataStream dataStream = (CSVDataStream)DataStreamFactory.getDataStream(DataStreamFactory.DataStreamType.CSVDataStream, configString);

        dataStream.setCSVString(helper.getFile("testDataset.csv"));
        List<byte[]> record = dataStream.getNextRecord();
        while(record != null) {
            for(int i = 0; i < record.size(); i++) {
                System.out.format("%s ", new String(record.get(i)));
            }
            System.out.println();
            record = dataStream.getNextRecord();
        }
        dataStream.reset();
        record = dataStream.getNextRecord();
        while(record != null) {
            for(int i = 0; i < record.size(); i++) {
                System.out.format("%s ", new String(record.get(i)));
            }
            System.out.println();
            record = dataStream.getNextRecord();
        }
    }

}
