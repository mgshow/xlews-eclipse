package org.lemansys.xlews.eclipse.perspectives;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class XLemNature implements IProjectNature {

	private IProject project;
	
	public static final String NATURE_ID="org.lemansys.xlem.eclipse.nature.xlemNature";
	
	@Override
	public void configure() throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deconfigure() throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public void setProject(IProject project) {
		this.project=project;

	}

}
