package hello;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

	private Client client;
	private Event event;
	private Map<EventType, EventLogger> loggerMap;

	public void setClient(Client client) {
		this.client = client;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setLoggerMap(Map<EventType, EventLogger> loggerMap) {
		this.loggerMap = loggerMap;
	}

	private void logEvent(String msg, EventType type) {
		String message = msg.replaceAll(client.getId(), client.getFullName());
		event.setMessage(message);
		event.setType(type);
		loggerMap.get(type).logEvent(event);
	}

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

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
