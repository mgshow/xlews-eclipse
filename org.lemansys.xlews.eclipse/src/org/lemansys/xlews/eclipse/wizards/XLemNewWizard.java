/**
 * Copyright (c) 2000-2012 Lemansys, Ltd. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package org.lemansys.xlews.eclipse.wizards;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "lem". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class XLemNewWizard extends Wizard implements INewWizard {
	private XLemNewWizardPage page;
	private ISelection selection;

	/**
	 * Constructor for LemNewWizard.
	 */
	public XLemNewWizard() {
		super();
		setNeedsProgressMonitor(true);
		this.setWindowTitle("Create a new XLEM Page...");
	}
	
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		page = new XLemNewWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		final String pageTitle=page.getPageTitle();
		final String outputType=page.getOutputType();
		final String encodingHTML=page.getHTMLEncoding();
		final boolean hideContent=page.hideContentPage();
		final boolean useSession=page.pageUseSession();
		final boolean createSessionOnFail=page.pageCreateSessionOnFail();
		final boolean singleThread=page.pageIsSingleThread();
		
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName+".xlem", pageTitle, outputType,encodingHTML, hideContent,
							useSession,createSessionOnFail,singleThread,
							monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(
		String containerName,
		String fileName,
		String pageTitle,
		String outputType,
		String htmlEncoding,
		boolean hideContent,
		boolean useSession,
		boolean createSessionOnFail,
		boolean singleThread,
		/*boolean useSOAP,*/
		IProgressMonitor monitor)
		throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		try {
			InputStream stream = openContentStream(pageTitle,outputType,htmlEncoding,hideContent,
					useSession,createSessionOnFail,singleThread);
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(1);
	}
	
	/**
	 * We will initialize file contents with a sample text.
	 */

	private InputStream openContentStream(
			String pageTitle,
			String outputType,
			String encoding,
			boolean hideContent,
			boolean useSession,
			boolean createSessionOnFail,
			boolean singleThread) {
		
		
		// Compose here default Lemansys Web Page...
		
		StringBuffer bfr=new StringBuffer();
		
		bfr.append("<@// This page has been generated automatically by XLEWS Plugin for Eclipse@>\r\n");
		bfr.append("<@// released under APACHE 2.0 license@>\r\n\r\n");
		
		bfr.append("<@// Created by ");
		bfr.append("");
		bfr.append(" on ");
		bfr.append(new java.util.Date());
		bfr.append(" @>\r\n");
		
		
		bfr.append("<@page version=\"1.0\"");
		
		// Check for session setup...
		if(useSession) {
			
			
			
		
			bfr.append(" session=\"true\"");
			
			if(!createSessionOnFail) bfr.append(" createonfail=\"false\"");
		
		}
		
		// Check for single-thread specific...
		if(singleThread) bfr.append(" multithread=\"false\"");
		
		bfr.append("@>\r\n\r\n");
		bfr.append("\t<@//Insert declarations and hidden operations here...@>\r\n\r\n");
		
		
		bfr.append("<@/page@>\r\n\r\n");
		
		
		if(!hideContent) {
		
			if(outputType.equalsIgnoreCase("XML 1.0")) {
				
				bfr.append("<@// !!! Please make no space(s) or new line(s) between ...@>\r\n");
				bfr.append("<@// ... \"content\" tag and \"<?xml?>\" tag ...@>\r\n");
				bfr.append("<@// ... otherwise with some browser your final XML does not properly work!!!!@>\r\n");
				
				bfr.append("<@content type=\"text/xml");
				if(!encoding.trim().equals("")) bfr.append(" encoding=\""+encoding+"\"");
				bfr.append("@>");
				
				bfr.append("<?xml version=\"1.0\"");
				
				if(!encoding.trim().equals("")) bfr.append(" encoding=\""+encoding+"\"");
				
				bfr.append("?>\r\n\t<firstTag>This is an XML output!</firstTag>\r\n");
				
				bfr.append("<@/content@>\r\n\r\n");
				
			}
			
			else if(outputType.equalsIgnoreCase("XHTML 1.0")) {
				
			
				bfr.append("<@content");
				
				if(!outputType.trim().equals("")) {
					bfr.append(" type=\"");bfr.append("text/html");bfr.append("\"");
				}
				
				
				if(!encoding.trim().equals("")) {
					bfr.append(" encoding=\"");bfr.append(encoding);bfr.append("\"");
				}
				
				bfr.append("@><?xml version=\"1.0\" encoding=\"UTF-8\"");
				if(!encoding.trim().equals("")) bfr.append(" encoding=\""+encoding+"\"");
				bfr.append("?>\r\n\r\n");

				bfr.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"DTD/xhtml1-strict.dtd\">\r\n");
				
				bfr.append("\t<@//HTML (dynamic) code goes here...@>\r\n\r\n");
				
				bfr.append("\t<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n\r\n");
				
				bfr.append("\t <head>\r\n");
				
				bfr.append("\t\t<title>"+pageTitle+"</title>\r\n");
				bfr.append("\t </head>\r\n\r\n");
				
				bfr.append("\t <body>\r\n");
				bfr.append("\t\tBody goes here...\r\n");
				bfr.append("\t </body>\r\n\r\n");
				
				bfr.append("\t</html>\r\n\r\n");
				
				bfr.append("<@/content@>\r\n\r\n");
				
				
				
				
			}
			
			else if(outputType.equalsIgnoreCase("XSL 1.0")) {
				
				bfr.append("<@// !!! Please make no space(s) or new line(s) between ...@>\r\n");
				bfr.append("<@// ... \"content\" tag and \"<?xml?>\" tag ...@>\r\n");
				bfr.append("<@// ... otherwise with some browser your final XSL does not properly work!!!!@>\r\n");
				
				bfr.append("<@content type=\"text/xml");
				if(!encoding.trim().equals("")) bfr.append(" encoding=\""+encoding+"\"");
				bfr.append("@>");
				
				bfr.append("<?xml version=\"1.0\"");
				
				if(!encoding.trim().equals("")) bfr.append(" encoding=\""+encoding+"\"");
				
				bfr.append("?>\r\n");
				
				bfr.append("<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" >\r\n");
				
				bfr.append("\t<xsl:output method=\"html\" indent=\"yes\" doctype-public=\"-//W3C//DTD HTML 4.01//EN\" />\r\n");
				
				
				// HTML syntax...
				
				bfr.append(" <xsl:template match=\"yourMainXMLtag\">\r\n");
				
				bfr.append("\t<html>\r\n\r\n");
				
				bfr.append("\t <head>\r\n");
				bfr.append("\t\t<meta HTTP-EQUIV=\"content-type\" CONTENT=\"text/html;");
				
				if(!encoding.trim().equals("")) bfr.append(" charset="+encoding);
				
				bfr.append("\"/>\r\n");
				bfr.append("\t\t<title>"+pageTitle+"</title>\r\n");
				bfr.append("\t </head>\r\n\r\n");
				
				bfr.append("\t <body>\r\n");
				bfr.append("\t\tImplements your templates...\r\n");
				bfr.append("\t\t<xsl:apply-templates/>\r\n");
				bfr.append("\t </body>\r\n\r\n");
				
				bfr.append("\t</html>\r\n\r\n");
				
				
				// End of HTML syntax
				
				
				bfr.append(" </xsl:template>\r\n</xsl:stylesheet>\r\n");
				
				bfr.append("<@/content@>\r\n\r\n");
				
			}
			
			
			
			
			else if(outputType.equalsIgnoreCase("HTML")){
				bfr.append("<@content");
				
				if(!outputType.trim().equals("")) {
					bfr.append(" type=\"");bfr.append("text/html");bfr.append("\"");
				}
				
				
				if(!encoding.trim().equals("")) {
					bfr.append(" encoding=\"");bfr.append(encoding);bfr.append("\"");
				}
				
				bfr.append("@>\r\n\r\n");

				bfr.append("\t<@//HTML (dynamic) code goes here...@>\r\n\r\n");
				
				bfr.append("\t<html>\r\n\r\n");
				
				bfr.append("\t <head>\r\n");
				bfr.append("\t\t<meta HTTP-EQUIV=\"content-type\" CONTENT=\"text/html;");
				
				if(!encoding.trim().equals("")) bfr.append(" charset="+encoding);
				
				bfr.append("\"/>\r\n");
				bfr.append("\t\t<title>"+pageTitle+"</title>\r\n");
				bfr.append("\t </head>\r\n\r\n");
				
				bfr.append("\t <body>\r\n");
				bfr.append("\t\tBody goes here...\r\n");
				bfr.append("\t </body>\r\n\r\n");
				
				bfr.append("\t</html>\r\n\r\n");
				
				bfr.append("<@/content@>\r\n\r\n"); }
			
			else if(outputType.equalsIgnoreCase("HTML 5.0")){
				bfr.append("<@content");
				
				if(!outputType.trim().equals("")) {
					bfr.append(" type=\"");bfr.append("text/html");bfr.append("\"");
				}
				
				
				if(!encoding.trim().equals("")) {
					bfr.append(" encoding=\"");bfr.append(encoding);bfr.append("\"");
				}
				
				bfr.append("@>\r\n\r\n");

				
				bfr.append("\t<@//HTML 5.0 (dynamic) code goes here...@>\r\n\r\n");
				
				bfr.append("\t<!doctype html>\r\n<html lang=\"en\">\r\n\r\n");
				
				bfr.append("\t <head>\r\n");
				bfr.append("\t\t<meta ");
				
				if(!encoding.trim().equals("")) bfr.append(" charset=\""+encoding);
				
				bfr.append("\"/>\r\n");
				bfr.append("\t\t<title>"+pageTitle+"</title>\r\n");
				bfr.append("\t </head>\r\n\r\n");
				
				bfr.append("\t <body>\r\n");
				bfr.append("\t\tBody goes here...\r\n");
				bfr.append("\t </body>\r\n\r\n");
				
				bfr.append("\t</html>\r\n\r\n");
				
				bfr.append("<@/content@>\r\n\r\n"); }
			
			else {bfr.append("<@content ");
					if(!outputType.trim().equals("")) bfr.append("type=\""+outputType+"\"");
					bfr.append("@>Put something here!<@/content@>");}
		
		} // End of "hideContent" check...
		
		bfr.append("<@// End of Lemansys Web Page Code...@>");
		
		String contents =bfr.toString();
		
			
		return new ByteArrayInputStream(contents.getBytes());
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "org.lemansys.xlews.eclipse.wizards.XLemNewWizard", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		
	}

}