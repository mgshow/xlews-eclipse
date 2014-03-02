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
package org.lemansys.xlews.editors.xlem;

import org.eclipse.ui.editors.text.TextEditor;

public class XLemEditor extends TextEditor {

	private XLemColorManager colorManager;

	public XLemEditor() {
		super();
		colorManager = new XLemColorManager();
		setSourceViewerConfiguration(new LemConfiguration(colorManager));
		setDocumentProvider(new LemDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
