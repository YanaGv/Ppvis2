import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class Ppvis2 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Главное окно");

		Table table = new Table(shell, SWT.V_SCROLL | SWT.MULTI);
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

		Button ButtonDelete = new Button(shell, SWT.NONE);
		ButtonDelete.setText("Удаление записей");
		ButtonDelete.setBounds(640, 90, 130, 30);

		Button ButtonSave = new Button(shell, SWT.NONE);
		ButtonSave.setText("Сохранить");
		ButtonSave.setBounds(640, 180, 130, 30);

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
