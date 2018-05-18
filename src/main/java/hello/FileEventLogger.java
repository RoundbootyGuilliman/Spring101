package hello;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

	protected String filename;
	protected File file;

	public FileEventLogger(String filename) {
		System.out.println("FileEventLogger constructor");
		this.filename = filename;
	}

	public void init() throws IOException {
		System.out.println("FileEventLogger init");
		System.out.println("this.file = " + file);
		this.file = new File(filename);
		System.out.println("this.file is now = " + file);
	}

	@Override
	public void logEvent(Event event) {
		try {
			FileUtils.writeStringToFile(file, event.toString(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
