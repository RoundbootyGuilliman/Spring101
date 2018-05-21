package hello.logger.impl;

import hello.entity.Event;
import hello.logger.EventLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class FileEventLogger implements EventLogger {

	@Value("log.txt")
	private String filename;

	private File file;

	@PostConstruct
	private void init() {
		file = new File(filename);
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
