package hello;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Event {

	private int id;
	private String message;
	private Date date;
	private DateFormat dateFormat;
	private EventType type;

	public Event(Date date, DateFormat dateFormat) {
		this.id = ThreadLocalRandom.current().nextInt(1, 999);
		this.date = date;
		this.dateFormat = dateFormat;
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
