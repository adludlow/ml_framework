import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Created by aludlow on 24/10/2015.
 */
public class TestHelper {

    public String getFile(String fileName){
        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
