public class Student {
	private String name;
	private int course;
	private int group;
	private int tasks;
	private int completedTasks;
	private String language;
	
	public void setName(String inputName) {
		name = inputName;
	}
	public String getName() {
		return name;
	}
	
	public void setCourse(int inputCourse) {
		course = inputCourse;
	}
	public int getCourse() {
		return course;
	}
	
	public void setGroup(int inputGroup) {
		group = inputGroup;
	}
	public int getGroup() {
		return group;
	}
	
	public void setTasks(int inputTasks) {
		tasks = inputTasks;
	}
	public int getTasks() {
		return tasks;
	}
	
	public void setCompletedTasks(int inputCompletedTasks) {
		completedTasks = inputCompletedTasks;
	}
	public int getCompletedTasks() {
		return completedTasks;
	}
	
	public void setLanguage(String inputLanguage) {
		language = inputLanguage;
	}
	public String getLanguage() {
		return language;
	}
}
