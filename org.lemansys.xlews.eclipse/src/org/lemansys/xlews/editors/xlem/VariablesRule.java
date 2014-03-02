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

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

/**
 * @author mgshow
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class VariablesRule extends WordRule{
	
	
	private XLemColorManager manager=new XLemColorManager();
	
	/*private Token nullToken= new Token(
			new TextAttribute(manager.getColor(ILemColorConstants.DEFAULT)));*/
	
	private IToken variables =
		new Token(
			new TextAttribute(manager.getColor(IXLemColorConstants.VARIABLES)));
	
	
	
	public VariablesRule() {
		
		
		super(new IWordDetector() {
            public boolean isWordStart(char c) {
            	
         	   return Character.isJavaIdentifierStart(c); 
            }
            public boolean isWordPart(char c) {
            	
            	return Character.isJavaIdentifierPart(c);
            	
            }
            
            
		});	
		
		
		for(int i=0;i<XLemConstants.LEM_PAGE_VARIABLES.length;i++) {
			addWord(XLemConstants.LEM_PAGE_VARIABLES[i],variables);
		}
		
		 
		
		
	}
	


	
	
}
