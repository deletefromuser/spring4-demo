package lombokBean;

import lombok.val;

public class Main {

	public static void main(String[] args) {
		val animal = Animal.build("tom");
		animal.setAge(20);
		System.out.println(animal);
		animal.logtest();
	}

}
