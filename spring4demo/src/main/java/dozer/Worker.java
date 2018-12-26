package dozer;

public class Worker {
	private Integer age;
	private String name;
	private Integer studentId;
	private Integer workerId;

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	@Override
	public String toString() {
		return "student ID : " + getStudentId() + ", worker Id : " + getWorkerId() + ", Name : " + getName()
				+ ", Age : " + getAge();
	}
}
