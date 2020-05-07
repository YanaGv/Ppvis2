package controller;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import model.Model;
import model.Student;

public class Controller{
	Model model = new Model();
	
	public void addRecord(String name, String course, String group, 
										String tasks, String complitedTasks, String language) {
		Student student = new Student();
		student.setName(name);
		
		int parsedText = Integer.parseInt(course);
		student.setCourse(parsedText);
		
		parsedText = Integer.parseInt(group);
		student.setGroup(parsedText);
		
		parsedText = Integer.parseInt(tasks);
		student.setTasks(parsedText);
		
		parsedText = Integer.parseInt(complitedTasks);
		student.setCompletedTasks(parsedText);
		
		student.setLanguage(language);
		model.getStudentList().add(student);
	}
	
	//удаление записей
	public void delete(Table table, int deleteOptionCheck, Label labelResult, Text textName, Text textCourse, 
						Text textGroup, Text textNotCompletedTasks, Combo comboTasks,
						Combo comboCompletedTasks, Combo comboLanguage) {
		table.removeAll();
		int allRecordCount = model.getStudentList().size();
		int recordCount = 0;
		for (int i = model.getTableSize() * model.getListCurrent(); i < model.getTableSize() * model.getListCurrent() + model.getTableSize() && i < model.getStudentList().size(); i++) {
			if (deleteOptionCheck == 0 && model.getStudentList().get(i).getName().startsWith(textName.getText()))
				i = deleteRecord(i);
			else if (deleteOptionCheck == 1 && model.getStudentList().get(i).getCourse() == Integer.parseInt(textCourse.getText()))
				i = deleteRecord(i);
			else if (deleteOptionCheck == 2 && model.getStudentList().get(i).getCompletedTasks() == Integer.parseInt(comboCompletedTasks.getText()))
				i = deleteRecord(i);
			else if (deleteOptionCheck == 3) {
				int notCompletedTasksCount = model.getStudentList().get(i).getTasks() - model.getStudentList().get(i).getCompletedTasks();
				if(notCompletedTasksCount == Integer.parseInt(textNotCompletedTasks.getText()))
					i = deleteRecord(i);
			}
			else if (deleteOptionCheck == 4 && model.getStudentList().get(i).getGroup() == Integer.parseInt(textGroup.getText()))
				i = deleteRecord(i);
			else if (deleteOptionCheck == 5 && model.getStudentList().get(i).getLanguage().equals(comboLanguage.getText()))
				i = deleteRecord(i);
			else if (deleteOptionCheck == 6 && model.getStudentList().get(i).getTasks() == Integer.parseInt(comboTasks.getText()))
				i = deleteRecord(i);
			recordCount++;
			if (recordCount == model.getTableSize()) break;
		}
		labelResult.setText("Результат удаления: " + (allRecordCount - model.getStudentList().size()));
		
	}

	//удаление записи из studentList
	private int deleteRecord(int count) {
		model.getStudentList().remove(count);
		return count - 1;
	}
}
