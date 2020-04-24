import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SearchWindow {
	
	private void clearComposite(Composite composite) {
		Control[] array = composite.getChildren();
		for (int i = 0; i < array.length; i++) {
			array[i].dispose();
		}
	}
	
	public SearchWindow(Shell parent) {
		Shell shell = new Shell(parent, SWT.PRIMARY_MODAL | SWT.DIALOG_TRIM);
		shell.setText("Окно поиска");
		
		Composite compositeRadio = new Composite(shell, SWT.BORDER);
		GridLayout gridLayout = new GridLayout(4, false);
		compositeRadio.setLayout(gridLayout);
		compositeRadio.setBounds(10,10,500,70);
		
		Composite compositeSearchElements = new Composite(shell, SWT.BORDER);
		compositeSearchElements.setBounds(10,100,500,60);
		
		Label labelChoose = new Label(compositeRadio, SWT.NONE);
		labelChoose.setText("Выберите условие поиска:");
		labelChoose.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));
		
		Button buttonName = new Button(compositeRadio, SWT.RADIO);
		buttonName.setText("Ф.И.О.");
		buttonName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		buttonName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source =  (Button) arg0.getSource();
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					Label labelName = new Label(compositeSearchElements, SWT.NONE);
					labelName.setText("Введите Ф.И.О.:");
					labelName.setBounds(5, 5, 485, 20);
					Text textNameText = new Text(compositeSearchElements, SWT.BORDER);
					textNameText.setBounds(5, 30, 485, 20);
				}
			}
		});
		
		Button buttonCourse = new Button(compositeRadio, SWT.RADIO);
		buttonCourse.setText("Курс");
		buttonCourse.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		buttonCourse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source =  (Button) arg0.getSource();
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					Label labelName = new Label(compositeSearchElements, SWT.NONE);
					labelName.setText("Введите Курс:");
					labelName.setBounds(5, 5, 485, 20);
					Text textNameText = new Text(compositeSearchElements, SWT.BORDER);
					textNameText.setBounds(5, 30, 485, 20);
				}
			}
		});
		
		Button buttonCompletedTasks = new Button(compositeRadio, SWT.RADIO);
		buttonCompletedTasks.setText("Выполненные работы");
		buttonCompletedTasks.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Button buttonNotCompletedTasks = new Button(compositeRadio, SWT.RADIO);
		buttonNotCompletedTasks.setText("Не выполненные работы");
		buttonNotCompletedTasks.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		buttonNotCompletedTasks.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source =  (Button) arg0.getSource();
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					Label labelName = new Label(compositeSearchElements, SWT.NONE);
					labelName.setText("Введите число не выполненных работ:");
					labelName.setBounds(5, 5, 485, 20);
					Text textNameText = new Text(compositeSearchElements, SWT.BORDER);
					textNameText.setBounds(5, 30, 485, 20);
				}
			}
		});
		
		Button buttonGroup = new Button(compositeRadio, SWT.RADIO);
		buttonGroup.setText("Группа");
		buttonGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		buttonGroup.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source =  (Button) arg0.getSource();
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					Label labelName = new Label(compositeSearchElements, SWT.NONE);
					labelName.setText("Введите Группу:");
					labelName.setBounds(5, 5, 485, 20);
					Text textNameText = new Text(compositeSearchElements, SWT.BORDER);
					textNameText.setBounds(5, 30, 485, 20);
				}
			}
		});
		
		Button buttonLanguage = new Button(compositeRadio, SWT.RADIO);
		buttonLanguage.setText("Язык");
		buttonLanguage.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Button buttonTasks = new Button(compositeRadio, SWT.RADIO);
		buttonTasks.setText("Общее число работ");
		buttonTasks.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Button buttonSearch = new Button(shell, SWT.PUSH);
		buttonSearch.setText("Начать поиск");
		buttonSearch.setBounds(10,180,500,30);
		
		Composite compositeResult = new Composite(shell, SWT.BORDER);
		compositeResult.setBounds(10,230,500,180);
		
		Label labelResult = new Label(compositeResult, SWT.NONE);
		labelResult.setText("Результат поиска:");
		labelResult.setBounds(5,5,485,20);
		
		List listResultList = new List(compositeResult, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER);
		listResultList.setBounds(5,30,485,140);
		
		Button buttonCancel = new Button(shell, SWT.NONE);
		buttonCancel.setBounds(10, 430, 500, 30);
		buttonCancel.setText("Закрыть окно поиска");

		buttonCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});
		
		compositeResult.layout();
		compositeRadio.layout();
		shell.setSize(530,500);
		shell.open();
	}
}
