package controller;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import model.Model;
import model.Student;

public class Controller extends Model{
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
		studentList.add(student);
	}
	
	//удаление записей
	protected void delete(Table table, int deleteOptionCheck, Label labelResult, Text textName, Text textCourse, 
						Text textGroup, Text textNotCompletedTasks, Combo comboTasks,
						Combo comboCompletedTasks, Combo comboLanguage) {
		table.removeAll();
		int allRecordCount = studentList.size();
		int recordCount = 0;
		for (int i = tableSize * listCurrent; i < tableSize * listCurrent + tableSize && i < studentList.size(); i++) {
			if (deleteOptionCheck == 0 && studentList.get(i).getName().startsWith(textName.getText()))
				i = deleteRecord(i);
			else if (deleteOptionCheck == 1 && studentList.get(i).getCourse() == Integer.parseInt(textCourse.getText()))
				i = deleteRecord(i);
			else if (deleteOptionCheck == 2 && studentList.get(i).getCompletedTasks() == Integer.parseInt(comboCompletedTasks.getText()))
				i = deleteRecord(i);
			else if (deleteOptionCheck == 3) {
				int notCompletedTasksCount = studentList.get(i).getTasks() - studentList.get(i).getCompletedTasks();
				if(notCompletedTasksCount == Integer.parseInt(textNotCompletedTasks.getText()))
					i = deleteRecord(i);
			}
			else if (deleteOptionCheck == 4 && studentList.get(i).getGroup() == Integer.parseInt(textGroup.getText()))
				i = deleteRecord(i);
			else if (deleteOptionCheck == 5 && studentList.get(i).getLanguage().equals(comboLanguage.getText()))
				i = deleteRecord(i);
			else if (deleteOptionCheck == 6 && studentList.get(i).getTasks() == Integer.parseInt(comboTasks.getText()))
				i = deleteRecord(i);
			recordCount++;
			if (recordCount == tableSize) break;
		}
		labelResult.setText("Результат удаления: " + (allRecordCount - studentList.size()));
		
	}

	//удаление записи из studentList
	private int deleteRecord(int count) {
		studentList.remove(count);
		return count - 1;
	}
}
