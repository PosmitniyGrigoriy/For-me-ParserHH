package parser.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DataUtils {

    private DataUtils() { }

    public static List<String> readDataFromFile(final String path) throws IOException { // O(1)
        List<String> data = null;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        if (bufferedReader.ready()) {
            data = List.of(bufferedReader.readLine().split("\\|"));
        }
        bufferedReader.close();
        return data;
    }

}
