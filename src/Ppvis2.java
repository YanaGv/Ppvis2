import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Ppvis2 {
	static WriteXMLFile writeXML = new WriteXMLFile();

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Главное окно");

		Table table = new Table(shell, SWT.V_SCROLL | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		String[] titles = { "Ф.И.О.", "Курс", "Группа", "Общее число работ", "Кол-во выполненных работ",
				"Язык программирования" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
		}
		for (int i = 0; i < titles.length; i++)
			table.getColumn(i).pack();
		table.setBounds(10, 10, 610, 240);

		Button buttonAddRecord = new Button(shell, SWT.NONE);
		buttonAddRecord.setText("Добавить запись");
		buttonAddRecord.setBounds(640, 10, 130, 30);

		buttonAddRecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				AddRecordWindow AddRecordWindow = new AddRecordWindow(shell);
			}
		});

		Button ButtonSearch = new Button(shell, SWT.NONE);
		ButtonSearch.setText("Поиск");
		ButtonSearch.setBounds(640, 50, 130, 30);

		ButtonSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SearchWindow searchWindow = new SearchWindow(shell);
			}
		});

		Button ButtonDelete = new Button(shell, SWT.NONE);
		ButtonDelete.setText("Удаление записей");
		ButtonDelete.setBounds(640, 90, 130, 30);

		ButtonDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DeleteWindow deleteWindow = new DeleteWindow(shell);
			}
		});

		Button ButtonSave = new Button(shell, SWT.NONE);
		ButtonSave.setText("Сохранить");
		ButtonSave.setBounds(640, 180, 130, 30);
		
		ButtonSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fileSaveDialog = new FileDialog(shell, SWT.SAVE);
				fileSaveDialog.setFilterNames(new String[] { "XML Files", "All Files (*.*)" });
				fileSaveDialog.setFilterExtensions(new String[] { "*.xml", "*.*" });
				fileSaveDialog.setFilterPath("C:\\");
				fileSaveDialog.setFileName("data.xml");
				writeXML.saveXML(fileSaveDialog.open());
			}
		});

		Button ButtonLoad = new Button(shell, SWT.NONE);
		ButtonLoad.setText("Загрузить");
		ButtonLoad.setBounds(640, 220, 130, 30);

		shell.setSize(800, 300);
		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

}
