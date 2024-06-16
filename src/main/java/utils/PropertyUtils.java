package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

	public static String getTestData(String Key) throws IOException {
		FileReader reader = new FileReader("./src/test/resources/testdata.properties");
		Properties p = new Properties();
		p.load(reader);
		String value = p.getProperty(Key);
		return value;
	}
}
