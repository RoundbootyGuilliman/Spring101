package hello.entity;

import hello.util.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Scope("prototype")
public class Event {

	private int id;
	private String message;
	private EventType type;

	@Autowired
	private Date date;

	@Autowired
	private DateFormat dateFormat;

	public Event() {
		id = ThreadLocalRandom.current().nextInt(1, 999);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type + ": Event id is " + id + ", message is \"" + message + "\", date is " + dateFormat.format(date) + "\r\n";
	}
}
