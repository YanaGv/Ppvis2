import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DeleteWindow extends Ppvis2 {
	private int deleteOptionCheck = 0;
	private Label label;
	private Text textName;
	private Text textCourse;
	private Combo comboCompletedTasks;
	private Text textNotCompletedTasks;
	private Text textGroup;
	private Combo comboLanguage;
	private Combo comboTasks;
	private Label labelResult;
	
	private void clearComposite(Composite composite) {
		Control[] array = composite.getChildren();
		for (int i = 0; i < array.length; i++) {
			array[i].dispose();
		}
	}
	
	private boolean checkComboContent(Combo combo, String string) {
		String[] comboContent = combo.getItems();
		for(String content: comboContent) {
			if (string.equals(content)) return false;
		}
		return true;
	}
	
	private void fillCombo(Combo combo, int checkField) {
		for (int i = tableSize * listCurrent; i < tableSize * listCurrent + tableSize && i < studentList.size(); i++) {
			if (checkField == 1 && checkComboContent(combo, studentList.get(i).getLanguage()))
				combo.add(studentList.get(i).getLanguage());
			if (checkField == 2 && checkComboContent(combo, ""+studentList.get(i).getCompletedTasks()))
				combo.add(""+studentList.get(i).getCompletedTasks());
			if (checkField == 3 && checkComboContent(combo, ""+studentList.get(i).getTasks()))
				combo.add(""+studentList.get(i).getTasks());
		}
	}
	
	private int deleteRecord(int count) {
		studentList.remove(count);
		return count - 1;
	}
	
	private void delete() {
		table.removeAll();
		int allRecordCount = studentList.size();
		int recordCount = 0;
		for (int i = tableSize * listCurrent; i < tableSize * listCurrent + tableSize && i < studentList.size(); i++) {
			if (deleteOptionCheck == 0 && studentList.get(i).getName().startsWith(textName.getText()))
				i = deleteRecord(i);
			if (deleteOptionCheck == 1 && studentList.get(i).getCourse() == Integer.parseInt(textCourse.getText()))
				i = deleteRecord(i);
			if (deleteOptionCheck == 2 && studentList.get(i).getCompletedTasks() == Integer.parseInt(comboCompletedTasks.getText()))
				i = deleteRecord(i);
			if (deleteOptionCheck == 3) {
				int notCompletedTasks = studentList.get(i).getTasks() - studentList.get(i).getCompletedTasks();
				if(notCompletedTasks == Integer.parseInt(textNotCompletedTasks.getText()))
					i = deleteRecord(i);
			}
			if (deleteOptionCheck == 4 && studentList.get(i).getGroup() == Integer.parseInt(textGroup.getText()))
				i = deleteRecord(i);
			if (deleteOptionCheck == 5 && studentList.get(i).getLanguage().equals(comboLanguage.getText()))
				i = deleteRecord(i);
			if (deleteOptionCheck == 6 && studentList.get(i).getTasks() == Integer.parseInt(comboTasks.getText()))
				i = deleteRecord(i);
			recordCount++;
			if (recordCount == tableSize) break;
		}
		labelResult.setText("Результат удаления: " + (allRecordCount - studentList.size()));
		updateTable();
	}
	
	public DeleteWindow(Shell parent) {
		Shell shell = new Shell(parent, SWT.PRIMARY_MODAL | SWT.DIALOG_TRIM);
		shell.setText("Окно удаления записей");

		Composite compositeRadio = new Composite(shell, SWT.BORDER);
		GridLayout gridLayout = new GridLayout(4, false);
		compositeRadio.setLayout(gridLayout);
		compositeRadio.setBounds(10, 10, 500, 70);

		Composite compositeSearchElements = new Composite(shell, SWT.BORDER);
		compositeSearchElements.setBounds(10, 100, 500, 60);

		Label labelChoose = new Label(compositeRadio, SWT.NONE);
		labelChoose.setText("Выберите условие удаления:");
		labelChoose.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));

		Button buttonName = new Button(compositeRadio, SWT.RADIO);
		buttonName.setText("Ф.И.О.");
		buttonName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		buttonName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source = (Button) arg0.getSource();
				deleteOptionCheck = 0;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Введите Ф.И.О.:");
					label.setBounds(5, 5, 485, 20);
					textName = new Text(compositeSearchElements, SWT.BORDER);
					textName.setBounds(5, 30, 485, 20);
				}
			}
		});

		Button buttonCourse = new Button(compositeRadio, SWT.RADIO);
		buttonCourse.setText("Курс");
		buttonCourse.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		buttonCourse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source = (Button) arg0.getSource();
				deleteOptionCheck = 1;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Введите Курс:");
					label.setBounds(5, 5, 485, 20);
					textCourse = new Text(compositeSearchElements, SWT.BORDER);
					textCourse.setBounds(5, 30, 485, 20);
				}
			}
		});

		Button buttonCompletedTasks = new Button(compositeRadio, SWT.RADIO);
		buttonCompletedTasks.setText("Выполненные работы");
		buttonCompletedTasks.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		buttonCompletedTasks.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source = (Button) arg0.getSource();
				deleteOptionCheck = 2;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Выберите число выполненных работ:");
					label.setBounds(5, 5, 485, 20);
					comboCompletedTasks = new Combo(compositeSearchElements, SWT.DROP_DOWN | SWT.READ_ONLY);
					comboCompletedTasks.setBounds(5, 30, 485, 20);
					fillCombo(comboCompletedTasks, 2);
				}
			}
		});

		Button buttonNotCompletedTasks = new Button(compositeRadio, SWT.RADIO);
		buttonNotCompletedTasks.setText("Не выполненные работы");
		buttonNotCompletedTasks.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		buttonNotCompletedTasks.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source = (Button) arg0.getSource();
				deleteOptionCheck = 3;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Введите число не выполненных работ:");
					label.setBounds(5, 5, 485, 20);
					textNotCompletedTasks = new Text(compositeSearchElements, SWT.BORDER);
					textNotCompletedTasks.setBounds(5, 30, 485, 20);
				}
			}
		});

		Button buttonGroup = new Button(compositeRadio, SWT.RADIO);
		buttonGroup.setText("Группа");
		buttonGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		buttonGroup.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source = (Button) arg0.getSource();
				deleteOptionCheck = 4;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Введите Группу:");
					label.setBounds(5, 5, 485, 20);
					textGroup = new Text(compositeSearchElements, SWT.BORDER);
					textGroup.setBounds(5, 30, 485, 20);
				}
			}
		});

		Button buttonLanguage = new Button(compositeRadio, SWT.RADIO);
		buttonLanguage.setText("Язык");
		buttonLanguage.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		buttonLanguage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source = (Button) arg0.getSource();
				deleteOptionCheck = 5;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Выберите язык:");
					label.setBounds(5, 5, 485, 20);
					comboLanguage = new Combo(compositeSearchElements, SWT.DROP_DOWN | SWT.READ_ONLY);
					comboLanguage.setBounds(5, 30, 485, 20);
					fillCombo(comboLanguage, 1);
				}
			}
		});

		Button buttonTasks = new Button(compositeRadio, SWT.RADIO);
		buttonTasks.setText("Общее число работ");
		buttonTasks.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		buttonTasks.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source = (Button) arg0.getSource();
				deleteOptionCheck = 6;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Выберите число работ:");
					label.setBounds(5, 5, 485, 20);
					comboTasks = new Combo(compositeSearchElements, SWT.DROP_DOWN | SWT.READ_ONLY);
					comboTasks.setBounds(5, 30, 485, 20);
					fillCombo(comboTasks, 3);
				}
			}
		});

		Button buttonDelete = new Button(shell, SWT.PUSH);
		buttonDelete.setText("Удалить записи");
		buttonDelete.setBounds(10, 180, 500, 30);
		buttonDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				delete();
			}
		});

		labelResult = new Label(shell, SWT.NONE);
		labelResult.setText("Результат удаления: ");
		labelResult.setBounds(10, 230, 500, 20);

		Button buttonCancel = new Button(shell, SWT.NONE);
		buttonCancel.setBounds(10, 270, 500, 30);
		buttonCancel.setText("Закрыть окно удаления");

		buttonCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});

		compositeRadio.layout();
		shell.setSize(530, 350);
		shell.open();
	}
}
