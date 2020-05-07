package view;

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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class SearchWindow extends Ppvis2 {
	private int searchOptionCheck = 0;
	
	private Table table;
	private Label label;
	private Text textName;
	private Text textCourse;
	private Combo comboCompletedTasks;
	private Text textNotCompletedTasks;
	private Text textGroup;
	private Combo comboLanguage;
	private Combo comboTasks;
	
	//удаление всех компонент в composite
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
		compositeRadio.setBounds(10, 10, 670, 70);

		Composite compositeSearchElements = new Composite(shell, SWT.BORDER);
		compositeSearchElements.setBounds(10, 100, 670, 60);

		Label labelChoose = new Label(compositeRadio, SWT.NONE);
		labelChoose.setText("Выберите условие поиска:");
		labelChoose.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));

		Button buttonName = new Button(compositeRadio, SWT.RADIO);
		buttonName.setText("Ф.И.О.");
		buttonName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		buttonName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button source = (Button) arg0.getSource();
				searchOptionCheck = 0;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Введите Ф.И.О.:");
					label.setBounds(5, 5, 485, 20);
					textName = new Text(compositeSearchElements, SWT.BORDER);
					textName.setBounds(5, 30, 650, 20);
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
				searchOptionCheck = 1;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Введите Курс:");
					label.setBounds(5, 5, 485, 20);
					textCourse = new Text(compositeSearchElements, SWT.BORDER);
					textCourse.setBounds(5, 30, 650, 20);
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
				searchOptionCheck = 2;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Выберите число выполненных работ:");
					label.setBounds(5, 5, 485, 20);
					comboCompletedTasks = new Combo(compositeSearchElements, SWT.DROP_DOWN | SWT.READ_ONLY);
					comboCompletedTasks.setBounds(5, 30, 650, 20);
					model.fillCombo(comboCompletedTasks, 2);
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
				searchOptionCheck = 3;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Введите число не выполненных работ:");
					label.setBounds(5, 5, 485, 20);
					textNotCompletedTasks = new Text(compositeSearchElements, SWT.BORDER);
					textNotCompletedTasks.setBounds(5, 30, 650, 20);
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
				searchOptionCheck = 4;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Введите Группу:");
					label.setBounds(5, 5, 485, 20);
					textGroup = new Text(compositeSearchElements, SWT.BORDER);
					textGroup.setBounds(5, 30, 650, 20);
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
				searchOptionCheck = 5;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Выберите язык:");
					label.setBounds(5, 5, 485, 20);
					comboLanguage = new Combo(compositeSearchElements, SWT.DROP_DOWN | SWT.READ_ONLY);
					comboLanguage.setBounds(5, 30, 650, 20);
					model.fillCombo(comboLanguage, 1);
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
				searchOptionCheck = 6;
				if (source.getSelection()) {
					clearComposite(compositeSearchElements);
					label = new Label(compositeSearchElements, SWT.NONE);
					label.setText("Выберите число работ:");
					label.setBounds(5, 5, 485, 20);
					comboTasks = new Combo(compositeSearchElements, SWT.DROP_DOWN | SWT.READ_ONLY);
					comboTasks.setBounds(5, 30, 650, 20);
					model.fillCombo(comboTasks, 3);
				}
			}
		});

		Button buttonSearch = new Button(shell, SWT.PUSH);
		buttonSearch.setText("Начать поиск");
		buttonSearch.setBounds(10, 180, 670, 30);
		buttonSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				model.search(table, searchOptionCheck, textName, textCourse, 
						textGroup, textNotCompletedTasks, comboTasks, 
						comboCompletedTasks, comboLanguage);
			}
		});

		Composite compositeResult = new Composite(shell, SWT.BORDER);
		compositeResult.setBounds(10, 230, 670, 180);

		Label labelResult = new Label(compositeResult, SWT.NONE);
		labelResult.setText("Результат поиска:");
		labelResult.setBounds(5, 5, 485, 20);

		table = new Table(compositeResult, SWT.V_SCROLL | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		String[] titles = { "Фамилия И.О.", "Курс", "Группа", "Общее число работ", "Кол-во выполненных работ",
				"Язык программирования" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
		}
		for (int i = 0; i < titles.length; i++)
			table.getColumn(i).pack();
		table.setBounds(10, 25, 650, 140);

		Button buttonCancel = new Button(shell, SWT.NONE);
		buttonCancel.setBounds(10, 430, 670, 30);
		buttonCancel.setText("Закрыть окно поиска");
		buttonCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});

		compositeResult.layout();
		compositeRadio.layout();
		shell.setSize(700, 500);
		shell.open();
	}
}
