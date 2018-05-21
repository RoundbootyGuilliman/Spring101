package hello.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Client {

	@Value("2")
	private String id;

	@Value("John Smith")
	private String fullName;

	@Value("Hello there!")
	private String greeting;

	public String getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}
}
