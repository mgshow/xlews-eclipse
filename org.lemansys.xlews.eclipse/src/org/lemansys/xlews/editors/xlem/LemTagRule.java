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

import org.eclipse.jface.text.rules.*;

public class LemTagRule extends MultiLineRule {

	
	
	
	
	
	public LemTagRule(IToken token) {
		super("<@", "@>", token);
	}
	protected boolean sequenceDetected(
		ICharacterScanner scanner,
		char[] sequence,
		boolean eofAllowed) {
		int c = scanner.read();
		if (sequence[0] == '<') {
			
			/*
			if (c == '?') {
				// processing instruction - abort
				scanner.unread();
				return false;
			}
			if (c == '!') {
				scanner.unread();
				// comment - abort
				return false;
			}*/
			
			
			
			if (c == '@') {
				
				//Check for primary word...
				
				String tmp="";
				int sz=0;
				int j=scanner.read();
				
				if(j!='=') {
				
				while(j!=' ' && j!='@' && j!=ICharacterScanner.EOF)  {
					tmp+=(char) j;
					sz++;
					j=scanner.read();
				}
				
				int i=0;
				
				for (i=0;i<sz;i++) scanner.unread();
				scanner.unread();scanner.unread();
				
				
				tmp=tmp.trim();
				boolean ok=false;
				
				boolean done=false;
				i=0;
				int ln=XLemConstants.TAGS.length;
				
				while(!done) {
					if(XLemConstants.TAGS[i].equals(tmp)) {ok=true;done=true;}	
				if(++i>=ln) done=true;
				}
				
				
				if (!ok) {return false;} 
				
				} else {scanner.unread();scanner.unread();}
				
				
				
			}
			
		} else if (sequence[0] == '@') {
			if(c=='>')
			scanner.unread();
			
		}
		return super.sequenceDetected(scanner, sequence, eofAllowed);
	}
}
