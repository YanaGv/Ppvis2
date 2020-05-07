package model;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class Model {
	protected static ArrayList<Student> studentList = new ArrayList<>();

	protected static int listCurrent = 0;
	protected static double listCount = 0;
	protected static int tableSize = 10;
	
	//обновление содержимого table
	protected static void updateTable(Table table, Label labelRecordCount, Label labelListCount) {
		table.removeAll();
		listCount = Math.ceil((studentList.size() - 1) / tableSize);
		labelRecordCount.setText("Всего записей: " + studentList.size());
		labelListCount.setText("Страница " + (listCurrent + 1) + " из " + ((int)listCount + 1));
		for (int i = tableSize * listCurrent; i < tableSize * listCurrent + tableSize && i < studentList.size(); i++) {
			addTableItem(table, studentList.get(i));
		}
	}
	
	//добавление в table нового item
	protected static void addTableItem(Table table, Student student) {
		TableItem item = new TableItem(table, SWT.NULL);
		item.setText(0, student.getName());
		item.setText(1, ""+student.getCourse());
		item.setText(2, ""+student.getGroup());
		item.setText(3, ""+student.getTasks());
		item.setText(4, ""+student.getCompletedTasks());
		item.setText(5, student.getLanguage());
	}
	
	//проверка на содержние строки string в элементах combo
	protected boolean checkComboContent(Combo combo, String string) {
		String[] comboContent = combo.getItems();
		for(String content: comboContent) {
			if (string.equals(content)) return false;
		}
		return true;
	}
	
	//заполнение combo элементами с использованием studentList
	protected void fillCombo(Combo combo, int checkField) {
		combo.removeAll();
		for (int i = tableSize * listCurrent; i < tableSize * listCurrent + tableSize && i < studentList.size(); i++) {
			if (checkField == 1 && checkComboContent(combo, studentList.get(i).getLanguage()))
				combo.add(studentList.get(i).getLanguage());
			else if (checkField == 2 && checkComboContent(combo, ""+studentList.get(i).getCompletedTasks()))
				combo.add(""+studentList.get(i).getCompletedTasks());
			else if (checkField == 3 && checkComboContent(combo, ""+studentList.get(i).getTasks()))
				combo.add(""+studentList.get(i).getTasks());
		}
	}
	
	//поиск записей в массиве studentList
	protected void search(Table table, int searchOptionCheck, Text textName, Text textCourse, 
					Text textGroup, Text textNotCompletedTasks, Combo comboTasks, Combo comboCompletedTasks, Combo comboLanguage) {
		table.removeAll();
		for (int i = tableSize * listCurrent; i < tableSize * listCurrent + tableSize && i < studentList.size(); i++) {
			if (searchOptionCheck == 0 && studentList.get(i).getName().startsWith(textName.getText()))
				addTableItem(table, studentList.get(i));
			else if (searchOptionCheck == 1 && studentList.get(i).getCourse() == Integer.parseInt(textCourse.getText()))
				addTableItem(table, studentList.get(i));
			else if (searchOptionCheck == 2 && studentList.get(i).getCompletedTasks() == Integer.parseInt(comboCompletedTasks.getText()))
				addTableItem(table, studentList.get(i));
			else if (searchOptionCheck == 3) {
				int notCompletedTasksCount = studentList.get(i).getTasks() - studentList.get(i).getCompletedTasks();
				if(notCompletedTasksCount == Integer.parseInt(textNotCompletedTasks.getText()))
					addTableItem(table, studentList.get(i));
			}
			else if (searchOptionCheck == 4 && studentList.get(i).getGroup() == Integer.parseInt(textGroup.getText()))
				addTableItem(table, studentList.get(i));
			else if (searchOptionCheck == 5 && studentList.get(i).getLanguage().equals(comboLanguage.getText()))
				addTableItem(table, studentList.get(i));
			else if (searchOptionCheck == 6 && studentList.get(i).getTasks() == Integer.parseInt(comboTasks.getText()))
				addTableItem(table, studentList.get(i));
		}
	}
}
