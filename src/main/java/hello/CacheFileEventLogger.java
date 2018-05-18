package hello;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

	private int cacheSize;
	private List<Event> cache;

	public CacheFileEventLogger(int cacheSize, String filename) {
		super(filename);
		System.out.println("CacheFileEventLogger constructor");
		this.cacheSize = cacheSize;
	}

	public void init() throws IOException {
		System.out.println("CacheFileEventLogger init");
		this.cache = new ArrayList<>(cacheSize);
		file = new File(filename);

	}

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