package org.lemansys.xlews.eclipse.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.lemansys.xlews.eclipse.perspectives.XLemNature;

public class XLemNewProjectWizard extends BasicNewProjectResourceWizard {

	
	public XLemNewProjectWizard() {
		

	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		boolean b = super.performFinish();

		try {
			IProject prj = this.getNewProject();
			IProjectDescription description = prj
					.getDescription();
			String[] natures = description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = XLemNature.NATURE_ID;
		      description.setNatureIds(newNatures);
		      prj.setDescription(description, null);
		      
		      

		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;

	}

}
