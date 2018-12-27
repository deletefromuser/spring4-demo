package bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dog {
	final static Logger logger = LoggerFactory.getLogger(Dog.class);

	public void shout() {
		System.out.println("wang~");
		logger.debug("Did it again!");
	}
}
