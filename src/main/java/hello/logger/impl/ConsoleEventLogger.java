package hello.logger.impl;

import hello.entity.Event;
import hello.logger.EventLogger;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger {

	public void logEvent(Event event) {
		System.out.println(event);
	}
}
