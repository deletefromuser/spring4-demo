package slf4jdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.Dog;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class Main {

	final static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		// assume SLF4J is bound to logback in the current environment
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		// print logback's internal status
		StatusPrinter.print(lc);

		logger.info("Entering application.");

		Dog foo = new Dog();
		foo.shout();
		logger.info("Exiting application.");
	}

}
