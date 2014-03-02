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
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class XLemScanner extends RuleBasedScanner {

	public XLemScanner(XLemColorManager manager) {
		
			IToken lemInstr =
				new Token(
					new TextAttribute(
						manager.getColor(IXLemColorConstants.LEM_COMMENT_TAG),
						null,
						SWT.ITALIC));
			
			IToken htmlInstr =
				new Token(
					new TextAttribute(
						manager.getColor(IXLemColorConstants.HTML_TAG),
						manager.getColor(new RGB(255,255,200)),
						SWT.ITALIC));
			
			
			
			IToken xslInstr =
					new Token(
						new TextAttribute(
							manager.getColor(IXLemColorConstants.XSL_TAG),
							manager.getColor(new RGB(200,200,0)),
							SWT.BOLD|TextAttribute.UNDERLINE));
			

			IRule[] rules = new IRule[7];
			//Add rule for processing instructions
			
			int k=0;
			
			rules[k++] = new MultiLineRule("<@--", "--@>", lemInstr);
			
			
			rules[k++] = new SingleLineRule("<@//", "@>", lemInstr);
			
			// Add generic whitespace rule.
			rules[k++] = new WhitespaceRule(new LemWhitespaceDetector());
						
			
			

			rules[k++] = new MultiLineRule("<xsl:", ">", xslInstr);
			rules[k++] = new MultiLineRule("</xsl:", ">", xslInstr);
			
			rules[k++] = new MultiLineRule("<!--", "-->", xslInstr);
			
			
			rules[k++] = new MultiLineRule("<", ">", htmlInstr);
			
			
			
			
			setRules(rules);
		}
		
		
	
}
