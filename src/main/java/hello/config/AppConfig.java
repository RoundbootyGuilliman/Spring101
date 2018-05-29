package hello.config;

import hello.logger.EventLogger;
import hello.logger.impl.CacheFileEventLogger;
import hello.logger.impl.CombinedEventLogger;
import hello.logger.impl.ConsoleEventLogger;
import hello.logger.impl.FileEventLogger;
import hello.util.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.*;

@Configuration
@ComponentScan(basePackages = "hello")
public class AppConfig {

	@Autowired
	private EventLogger consoleEventLogger;

	@Autowired
	private EventLogger fileEventLogger;

	@Autowired
	private EventLogger cacheFileEventLogger;

	@Autowired
	private EventLogger combinedEventLogger;

	@Bean
	public Map<EventType, EventLogger> loggerMap() {
		Map<EventType, EventLogger> loggerMap = new HashMap<>();
		loggerMap.put(EventType.ERROR, combinedEventLogger);
		loggerMap.put(EventType.INFO, consoleEventLogger);
		return loggerMap;
	}

	@Bean
	public List<EventLogger> loggers() {
		List<EventLogger> loggers = new ArrayList<>();
		loggers.add(consoleEventLogger);
		loggers.add(fileEventLogger);
		return loggers;
	}
}