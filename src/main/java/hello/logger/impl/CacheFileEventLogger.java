package hello.logger.impl;

import hello.entity.Event;
import hello.logger.EventLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger implements EventLogger {

	@Value("2")
	private int cacheSize;

	@Value("log.txt")
	private String filename;

	private File file;

	private List<Event> cache;

	@PostConstruct
	private void init() {
		cache = new ArrayList<>(cacheSize);
		file = new File(filename);
	}

	@PreDestroy
	public void destroy() {
		if (!cache.isEmpty()) writeEventsFromCache();
	}

	@Override
	public void logEvent(Event event) {
		cache.add(event);
		if (cache.size() == cacheSize) {
			writeEventsFromCache();
		}
	}

	private void writeEventsFromCache() {
		for (Event event : cache) {
			try {
				FileUtils.writeStringToFile(file, event.toString(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cache.clear();
	}
}