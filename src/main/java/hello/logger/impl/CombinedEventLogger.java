package hello.logger.impl;

import hello.entity.Event;
import hello.logger.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CombinedEventLogger implements EventLogger {

	@Autowired
	@Qualifier("loggers")
	private List<EventLogger> loggers;

	@Override
	public void logEvent(Event event) {
		for (EventLogger logger : loggers) {
			logger.logEvent(event);
		}
	}
}
