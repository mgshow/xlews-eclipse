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

import org.eclipse.swt.graphics.RGB;

public interface IXLemColorConstants {
	
	RGB XML_COMMENT = new RGB(128, 0, 0);
	
	RGB STRING = new RGB(0, 200, 58);
	RGB DEFAULT = new RGB(0, 0, 0);
	RGB TAG = new RGB(0, 0, 180);
	RGB FUNCTIONS=new RGB(0,50,255);
	RGB NUMBERS=new RGB(0,200,0);
	RGB PRELOADED=new RGB(180,0,100);
	
	RGB HTML_TAG=new RGB(0,90,0);
	
	RGB	LEM_COMMENT_TAG = new RGB(128, 0, 0);
	RGB VARIABLES=new RGB(200,120,0);
	
	RGB XSL_TAG=new RGB(100,0,100);
}
