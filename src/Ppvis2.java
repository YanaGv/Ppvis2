import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Ppvis2 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Главное окно");
        
        Table table = new Table(shell, SWT.V_SCROLL |SWT.MULTI);
        table.setHeaderVisible(true);
        String[] titles = {"Ф.И.О.", "Курс", "Группа", "Общее число работ", "Кол-во выполненных работ", "Язык программирования"};
        for (int i = 0; i < titles.length; i++) {
        	TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
        }
        for (int i = 0; i < titles.length; i++) table.getColumn(i).pack();
        table.setBounds(10, 10, 610, 240);
        
        Button Button1 = new Button(shell, SWT.NONE);
        Button1.setText("Добавить запись");
        Button1.setBounds(640, 10, 130, 30);
        
        Button Button2 = new Button(shell, SWT.NONE);
        Button2.setText("Поиск");
        Button2.setBounds(640, 50, 130, 30);
        
        Button Button3 = new Button(shell, SWT.NONE);
        Button3.setText("Удаление записей");
        Button3.setBounds(640, 90, 130, 30);
        
        Button Button4 = new Button(shell, SWT.NONE);
        Button4.setText("Сохранить");
        Button4.setBounds(640, 180, 130, 30);
        
        Button Button5 = new Button(shell, SWT.NONE);
        Button5.setText("Загрузить");
        Button5.setBounds(640, 220, 130, 30);
        
		shell.setSize(800, 300);
        shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

}
