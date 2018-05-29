package hello.app;

import hello.config.AppConfig;
import hello.entity.Client;
import hello.entity.Event;
import hello.util.EventType;
import hello.logger.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class App {

	@Autowired
	private Client client;

	@Autowired
	private Event event;

	@Autowired
	private Map<EventType, EventLogger> loggerMap;

	private void logEvent(String msg, EventType type) {
		String message = msg.replaceAll(client.getId(), client.getFullName());
		event.setMessage(message);
		event.setType(type);
		loggerMap.get(type).logEvent(event);
	}

	public static void main(String[] args) throws InterruptedException {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		App app = (App) context.getBean("app");

		for (int i = 0; i < 4; i++) {
			System.out.println("Invoking log method:");
			app.logEvent("Some event for user 1", EventType.ERROR);
			Thread.sleep(1000);
		}
		for (int i = 0; i < 4; i++) {
			System.out.println("Invoking log method:");
			app.logEvent("Some event for user 1", EventType.INFO);
			Thread.sleep(1000);
		}
		System.out.println("Closing context:");
		context.close();
	}
}