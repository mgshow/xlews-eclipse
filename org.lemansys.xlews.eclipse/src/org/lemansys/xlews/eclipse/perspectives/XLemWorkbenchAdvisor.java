package org.lemansys.xlews.eclipse.perspectives;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;

public class XLemWorkbenchAdvisor extends WorkbenchAdvisor {

	@Override
	public String getInitialWindowPerspectiveId() {
		return "org.lemansys.xlews.eclipse.perspectives.XLemPerspective";
	}
	
	public void preWindowOpen(IWorkbenchWindowConfigurer configurer) {
		//super.preWindowOpen(configurer);
		configurer.setTitle("XLem");
		configurer.setInitialSize(new Point(300, 300));
		configurer.setShowMenuBar(false);
		configurer.setShowStatusLine(false);
		configurer.setShowCoolBar(false);
	}
	
	
	
	


}
