package lombokBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Data(staticConstructor = "build")
//@AllArgsConstructor
//@NoArgsConstructor
//@RequiredArgsConstructor
@Slf4j
public class Animal {
	@NonNull
	private String name;
	int age;

	public void logtest() {
		log.info("annotation slf4j actived");
	}
}
