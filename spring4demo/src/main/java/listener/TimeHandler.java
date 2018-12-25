package listener;

import org.aspectj.lang.JoinPoint;

public class TimeHandler {
	public void printTime(JoinPoint jp) {
		System.out.println("Log before method : CurrentTime = " + System.currentTimeMillis());
	}
	
	public void printTimeAfter() {
		System.out.println("Log after method : CurrentTime = " + System.currentTimeMillis());
	}
}
