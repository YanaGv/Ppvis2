import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddRecordWindow extends Ppvis2 {
	public AddRecordWindow(Shell parent) {
		Shell shell = new Shell(parent, SWT.PRIMARY_MODAL | SWT.DIALOG_TRIM);
		shell.setText("Добавление записи");

		Label labelName = new Label(shell, SWT.NONE);
		labelName.setText("Ф.И.О.:");
		labelName.setBounds(10, 10, 160, 20);
		Text textName = new Text(shell, SWT.BORDER);
		textName.setBounds(180, 10, 150, 20);

		Label labelCourse = new Label(shell, SWT.NONE);
		labelCourse.setText("Курс:");
		labelCourse.setBounds(10, 40, 160, 20);
		Text textCourse = new Text(shell, SWT.BORDER);
		textCourse.setBounds(180, 40, 150, 20);

		Label labelGroup = new Label(shell, SWT.NONE);
		labelGroup.setText("Группа:");
		labelGroup.setBounds(10, 70, 160, 20);
		Text textGroup = new Text(shell, SWT.BORDER);
		textGroup.setBounds(180, 70, 150, 20);

		Label labelTasks = new Label(shell, SWT.NONE);
		labelTasks.setText("Общее число работ:");
		labelTasks.setBounds(10, 100, 160, 20);
		Text textTasks = new Text(shell, SWT.BORDER);
		textTasks.setBounds(180, 100, 150, 20);

		Label labelCompletedTasks = new Label(shell, SWT.NONE);
		labelCompletedTasks.setText("Кол-во выполненных работ:");
		labelCompletedTasks.setBounds(10, 130, 160, 20);
		Text textCompletedTasks = new Text(shell, SWT.BORDER);
		textCompletedTasks.setBounds(180, 130, 150, 20);

		Label labelLanguage = new Label(shell, SWT.NONE);
		labelLanguage.setText("Язык программирования:");
		labelLanguage.setBounds(10, 160, 160, 20);
		Text textLanguage = new Text(shell, SWT.BORDER);
		textLanguage.setBounds(180, 160, 150, 20);

		Button buttonAddRecord = new Button(shell, SWT.NONE);
		buttonAddRecord.setBounds(10, 200, 155, 30);
		buttonAddRecord.setText("Добавить запись");
		
		buttonAddRecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				writeXML.addRecord(textName.getText(), textCourse.getText(), textGroup.getText(), textTasks.getText(), textCompletedTasks.getText(), textLanguage.getText());
			}
		});

		Button buttonCancel = new Button(shell, SWT.NONE);
		buttonCancel.setBounds(175, 200, 155, 30);
		buttonCancel.setText("Отменить");

		buttonCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});

		shell.setSize(350, 280);
		shell.open();
	}
}