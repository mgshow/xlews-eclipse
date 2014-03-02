package org.lemansys.xlews.eclipse.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (lem).
 */

public class XLemNewWizardPage extends WizardPage {
	

	private Text containerText;
	private Text fileText;
	private Text pageTitle;
	private Combo encodingCombo;
	private Label encodingLabel;
	private Combo typeCombo;
	private Label typeLabel;
	
	private Button hideContent;
	
	private ISelection selection;
	
	private Button useSession;
	private Button createSessionOnFail;
	private Label labelCreateSessionOnFail;
	private Button isSingleThread;
	
	//private Button useSOAPClient;

	/**
	 * Constructor for SampleNewWizardPage.
	 * @param pageName
	 */
	public XLemNewWizardPage(ISelection selection) {
		
		
		super("wizardPage");
		System.out.println("Inizializzo PLUGINZ");
		
		setTitle("XLEWS Server Page (.xlem)");
		setDescription("This wizard creates a new file with *.xlem extension that can be opened by a XML Page editor.");
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		
		label.setText("&Container:");

		
		
		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		
		label = new Label(container, SWT.NULL);
		label.setText("&File name:");

		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		
		label=new Label(container,SWT.NULL);
		label.setText(".xlem");
		
		
		label=new Label(container,SWT.NULL);
		label.setText("&Hide <@content@>:");
		
		hideContent=new Button(container,SWT.CHECK);
		hideContent.setToolTipText("Hide <@content@> ... <@/content@> sequence, if your page does not necessarly requires a final output...");
		
		hideContent.addSelectionListener(new SelectionListener() {
		
			public void widgetSelected(SelectionEvent e) {
				makeHideSelection();
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				makeHideSelection();
			}
		
		});
		
		
		label=new Label(container,SWT.NULL);
		label.setText("");
			
		typeLabel=new Label(container,SWT.NULL);
		typeLabel.setText("&Output type:");
		
		String encT[]={"HTML","HTML 5.0","XHTML 1.0", "XML 1.0", "XSL 1.0","plain/text","<other...>"};
		
		typeCombo=new Combo(container,SWT.NULL);
		
		for(int j=0;j<encT.length;j++) typeCombo.add(encT[j]);
		
		typeCombo.select(0);
		
		
		
		label=new Label(container,SWT.NULL);
		label.setText("");
			
		encodingLabel=new Label(container,SWT.NULL);
		encodingLabel.setText("Output &encoding:");
		
		String encS[]={"UTF-8","iso-8859-1","US-ASCII","<none>"};
		
		encodingCombo=new Combo(container,SWT.NULL);
		
		for(int j=0;j<encS.length;j++) encodingCombo.add(encS[j]);
		
		encodingCombo.select(0);
		
		
		
		label=new Label(container,SWT.NULL);
		label.setText("");
		
		

		label=new Label(container,SWT.NULL);
		label.setText("Page &title:");
		
		gd = new GridData(GridData.FILL_HORIZONTAL);
		pageTitle = new Text(container, SWT.BORDER | SWT.SINGLE);
		
		pageTitle.setLayoutData(gd);
		pageTitle.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		
		
		label=new Label(container,SWT.NULL);
		label.setText("");
		
		
		
		
		label=new Label(container,SWT.NULL);
		label.setText("Use HTTP(S) &session:");
		
		useSession=new Button(container,SWT.CHECK);
		useSession.setToolTipText("Automatic use/instantiate session into your new .lem page...");
		
		useSession.addSelectionListener(new SelectionListener() {
			
				public void widgetSelected(SelectionEvent e) {
					makeCreateSessionOnFail();
				}
				
				public void widgetDefaultSelected(SelectionEvent e) {
					makeCreateSessionOnFail();
				}
			
			});
		
		
		label=new Label(container,SWT.NULL);
		label.setText("");
		
		labelCreateSessionOnFail=new Label(container,SWT.NULL);
		labelCreateSessionOnFail.setText("Create session on &fail:");
		labelCreateSessionOnFail.setEnabled(false);
		
		createSessionOnFail=new Button(container,SWT.CHECK);
		createSessionOnFail.setToolTipText("Create a new session if not already existing...");
		createSessionOnFail.setEnabled(false);
		createSessionOnFail.setSelection(true);
		
		label=new Label(container,SWT.NULL);
		label.setText("");
		
		label=new Label(container,SWT.NULL);
		label.setText("Sin&gle thread:");
		
		isSingleThread=new Button(container,SWT.CHECK);
		isSingleThread.setToolTipText("Define if this page is single thread or multithreaded ...");
		
		label=new Label(container,SWT.NULL);
		label.setText("");
		
		
		initialize();
		dialogChanged();
		setControl(container);
	}
	
	/**
	 * Tests if the current workbench selection is a suitable
	 * container to use.
	 */
	
	private void initialize() {
		if (selection!=null && selection.isEmpty()==false && selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection)selection;
			if (ssel.size()>1) return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer)obj;
				else
					container = ((IResource)obj).getParent();
				containerText.setText(container.getFullPath().toString());
			}
		}
		fileText.setText("");
		pageTitle.setText("");
	}
	
	/**
	 * Uses the standard container selection dialog to
	 * choose the new value for the container field.
	 */

	private void handleBrowse() {
		ContainerSelectionDialog dialog =
			new ContainerSelectionDialog(
				getShell(),
				ResourcesPlugin.getWorkspace().getRoot(),
				false,
				"Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path)result[0]).toOSString());
			}
		}
	}
	
	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		String container = getContainerName();
		String fileName = getFileName();

		if (container.length() == 0) {
			updateStatus("File container must be specified");
			return;
		}
		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}
		
		if (!fileName.matches("[\\w\\d-_]*")) {
			updateStatus("File name must contain only letters, digit, '-' or '_' !");
			return;
		}
		
		
		/*int dotLoc = fileName.lastIndexOf('.');
		if (dotLoc != -1) {
			String ext = fileName.substring(dotLoc + 1);
			if (ext.equalsIgnoreCase("xlem") == false) {
				updateStatus("File extension must be \"lem\"");
				return;
			}
		}
		
		if (!fileName.toLowerCase().endsWith(".xlem")) {
			updateStatus("Please specify a \".xlem\" extension for your file...");
			return;
		}*/
		
		// Check for page title...
		/*String pageTitleName=getPageTitle();
		if (pageTitleName.length() == 0) {
			updateStatus("Page Title must be specified");
			return;
		}*/
		
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getContainerName() {
		return containerText.getText();
	}
	public String getFileName() {
		return fileText.getText();
	}
	public String getPageTitle() {
		return pageTitle.getText();
	}
	public String getHTMLEncoding(){
		return encodingCombo.getText();
	}
	
	public String getOutputType(){
		return typeCombo.getText();
	}
	
	public boolean hideContentPage() {
		return hideContent.getSelection();
	}
	
	public boolean pageUseSession() {
		return useSession.getSelection();
	}
	
	public boolean pageCreateSessionOnFail() {
		return createSessionOnFail.getSelection();
	}
	
	public boolean pageIsSingleThread() {
		return isSingleThread.getSelection();
	}
	
	
	
	// Operations with checkboxs...
	public void makeHideSelection() {
		
		boolean b=!hideContent.getSelection();
		
		
			this.encodingCombo.setEnabled(b);
			this.typeCombo.setEnabled(b);
			this.encodingLabel.setEnabled(b);
			this.typeLabel.setEnabled(b);
			this.pageTitle.setEnabled(b);
		
	}
	
	public void makeCreateSessionOnFail() {
		
		boolean b=useSession.getSelection();
		
		
			this.labelCreateSessionOnFail.setEnabled(b);
			this.createSessionOnFail.setEnabled(b);
			
		
	}
}