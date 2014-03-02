package org.lemansys.xlews.eclipse.perspectives;


import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;

public class XLemApplication implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		WorkbenchAdvisor workbenchAdvisor = new XLemWorkbenchAdvisor();
		Display display = PlatformUI.createDisplay();
		int returnCode = PlatformUI.createAndRunWorkbench(display,
		workbenchAdvisor);
		if (returnCode == PlatformUI.RETURN_RESTART)
		return IApplication.EXIT_RESTART;
		else
		return IApplication.EXIT_OK;

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	
}
