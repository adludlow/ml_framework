import com.ls.ai.ml.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by aludlow on 28/10/2015.
 */
public class TestGradientDescent {

    private TestHelper helper;
    public TestGradientDescent() {
        helper = new TestHelper();
    }
    @Test
    public void testLearn() {
        String dataStreamConfigString = helper.getFile("testDatasetProperties.json");
        CSVDataStream dataStream = (CSVDataStream) DataStreamFactory.getDataStream(DataStreamFactory.DataStreamType.CSVDataStream,
                dataStreamConfigString);
        dataStream.setCSVString(helper.getFile("testDataset-Small.csv"));

        String configString = helper.getFile("GradientDescentAlgorithmProperties.json");
        LearningAlgorithm gd = LearningAlgorithmFactory.getLearningAlgorithm(LearningAlgorithmFactory.AlgorithmType.GRADIENT_DESCENT,
                configString);
        gd.setDataStream(dataStream);
        gd.learn();
    }
}
