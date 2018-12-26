package lombokBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data(staticConstructor = "build")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Animal {
	@NonNull
	private String name;
	int age;
}
