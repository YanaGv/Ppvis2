import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class Ppvis2 {
	static ArrayList<Student> studentList = new ArrayList<>();
	static Table table;
	static Label labelRecordCount;
	static Label labelListCount;
	static int listCurrent = 0;
	static double listCount = 0;
	static int tableSize = 10;
	
	protected static void updateTable() {
		table.removeAll();
		listCount = Math.ceil((studentList.size() - 1) / tableSize);
		labelRecordCount.setText("Всего записей: " + studentList.size());
		labelListCount.setText("Страница " + (listCurrent + 1) + " из " + ((int)listCount + 1));
		for (int i = tableSize * listCurrent; i < tableSize * listCurrent + tableSize && i < studentList.size(); i++) {
			addTableItem(table, studentList.get(i));
		}
	}
	
	protected static void addTableItem(Table table, Student student) {
		TableItem item = new TableItem(table, SWT.NULL);
		item.setText(0, student.getName());
		item.setText(1, ""+student.getCourse());
		item.setText(2, ""+student.getGroup());
		item.setText(3, ""+student.getTasks());
		item.setText(4, ""+student.getCompletedTasks());
		item.setText(5, student.getLanguage());
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Главное окно");

		table = new Table(shell, SWT.V_SCROLL | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		String[] titles = { "Фамилия И.О.", "Курс", "Группа", "Общее число работ", "Кол-во выполненных работ",
				"Язык программирования" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
		}
		for (int i = 0; i < titles.length; i++)
			table.getColumn(i).pack();
		table.setBounds(10, 10, 650, 240);
		
		Label labelTableSize = new Label(shell, SWT.NONE);
		labelTableSize.setText("Записей на одной странице:");
		labelTableSize.setBounds(10, 260, 160, 20);
		
		Text textTableSize = new Text(shell, SWT.BORDER);
		textTableSize.setText("10");
		textTableSize.setBounds(170, 260, 30, 20);
		
		Button buttonSetTableSize = new Button(shell, SWT.NONE);
		buttonSetTableSize.setText("Применить");
		buttonSetTableSize.setBounds(205, 259, 80, 22);
		
		buttonSetTableSize.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				tableSize = Integer.parseInt(textTableSize.getText());
				listCurrent = 0;
				updateTable();
			}
		});
		
		labelRecordCount = new Label(shell, SWT.NONE);
		labelRecordCount.setText("Всего записей: "+studentList.size());
		labelRecordCount.setBounds(290, 260, 100, 20);
		
		labelListCount = new Label(shell, SWT.NONE);
		labelListCount.setText("Страница "+listCurrent+" из "+(int)listCount);
		labelListCount.setBounds(500, 260, 100, 20);
		
		Button buttonFirstList = new Button(shell, SWT.NONE);
		buttonFirstList.setText("<<");
		buttonFirstList.setBounds(440, 259, 25, 22);
		
		buttonFirstList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				listCurrent = 0;
				updateTable();
			}
		});
		
		Button buttonPastList = new Button(shell, SWT.NONE);
		buttonPastList.setText("<");
		buttonPastList.setBounds(470, 259, 20, 22);
		
		buttonPastList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (listCurrent != 0) {
					listCurrent--;
					updateTable();
				}
			}
		});
		
		Button buttonNextList = new Button(shell, SWT.NONE);
		buttonNextList.setText(">");
		buttonNextList.setBounds(600, 259, 20, 22);
		
		buttonNextList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (listCurrent != listCount) {
					listCurrent++;
					updateTable();
				}
			}
		});
		
		Button buttonLastList = new Button(shell, SWT.NONE);
		buttonLastList.setText(">>");
		buttonLastList.setBounds(625, 259, 25, 22);
		
		buttonLastList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				listCurrent = (int) listCount;
				updateTable();
			}
		});

		Button buttonAddRecord = new Button(shell, SWT.NONE);
		buttonAddRecord.setText("Добавить запись");
		buttonAddRecord.setBounds(680, 10, 130, 30);

		buttonAddRecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				AddRecordWindow AddRecordWindow = new AddRecordWindow(shell);
			}
		});

		Button ButtonSearch = new Button(shell, SWT.NONE);
		ButtonSearch.setText("Поиск");
		ButtonSearch.setBounds(680, 50, 130, 30);

		ButtonSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SearchWindow searchWindow = new SearchWindow(shell);
			}
		});

		Button ButtonDelete = new Button(shell, SWT.NONE);
		ButtonDelete.setText("Удаление записей");
		ButtonDelete.setBounds(680, 90, 130, 30);

		ButtonDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeleteWindow deleteWindow = new DeleteWindow(shell);
			}
		});

		Button ButtonSave = new Button(shell, SWT.NONE);
		ButtonSave.setText("Сохранить");
		ButtonSave.setBounds(680, 180, 130, 30);
		
		ButtonSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fileSaveDialog = new FileDialog(shell, SWT.SAVE);
				fileSaveDialog.setFilterNames(new String[] { "XML Files", "All Files (*.*)" });
				fileSaveDialog.setFilterExtensions(new String[] { "*.xml", "*.*" });
				fileSaveDialog.setFilterPath("C:\\");
				fileSaveDialog.setFileName("data.xml");
				
				WriteXMLFile domXML = new WriteXMLFile();
				
				String savePathString = fileSaveDialog.open();
				if (savePathString != null)
					domXML.saveXML(savePathString);
			}
		});

		Button ButtonLoad = new Button(shell, SWT.NONE);
		ButtonLoad.setText("Загрузить");
		ButtonLoad.setBounds(680, 220, 130, 30);
		
		ButtonLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fileOpenDialog = new FileDialog(shell, SWT.OPEN);
				fileOpenDialog.setFilterNames(new String[] { "XML Files", "All Files (*.*)" });
				fileOpenDialog.setFilterExtensions(new String[] { "*.xml", "*.*" });
				fileOpenDialog.setFilterPath("C:\\");
				fileOpenDialog.setFileName("data.xml");
				
				ReadXMLFile saxXML = new ReadXMLFile();
				
				String filePathString = fileOpenDialog.open();
				if (filePathString != null)
					saxXML.readXML(filePathString);
			}
		});

		shell.setSize(850, 330);
		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

}
