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
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

public class LemTagScanner extends RuleBasedScanner {

	public LemTagScanner(XLemColorManager manager) {
		IToken string = new Token(new TextAttribute(
				manager.getColor(IXLemColorConstants.STRING), null, SWT.ITALIC));

		/*IToken numbers = new Token(
				new TextAttribute(
						manager.getColor(IXLemColorConstants.NUMBERS), null,
						SWT.ITALIC));*/

		IToken tags = new Token(new TextAttribute(
				manager.getColor(IXLemColorConstants.XSL_TAG), null,
				SWT.ITALIC));
		
		IToken functions = new Token(new TextAttribute(
				manager.getColor(IXLemColorConstants.FUNCTIONS), null,
				SWT.ITALIC));

		IToken preloaded = new Token(new TextAttribute(
				manager.getColor(IXLemColorConstants.PRELOADED), null,
				SWT.ITALIC));

		/*
		 * IToken variables = new Token( new TextAttribute(manager.getColor(
		 * ILemColorConstants.VARIABLES), null, SWT.ITALIC));
		 */

		IRule[] rules = new IRule[5];

		// Add rule for double quotes
		rules[0] = new SingleLineRule("\"", "\"", string, '\\');
		// Add a rule for single quotes
		// rules[1] = new SingleLineRule("'", "'", string, '\\');
		// Add generic whitespace rule.
		
		WordRule tagz= new WordRule(new IWordDetector() {
			public boolean isWordStart(char c) {
				return c=='<';//Character.isJavaIdentifierStart(c);
			}

			public boolean isWordPart(char c) {
				return c=='/' || c=='@' || c=='=' ||  c=='>' || c==':' ||Character.isJavaIdentifierPart(c);
			}
			
			

		});
		
		for (int kk = 0; kk < XLemConstants.TAGS.length; kk++) {
			tagz.addWord("<@"+XLemConstants.TAGS[kk], tags);
			tagz.addWord("<@"+XLemConstants.TAGS[kk]+"@>", tags);
		}
		
		tagz.addWord("<@=", tags);
		
		rules[1] = tagz;//new WhitespaceRule(new LemWhitespaceDetector());

		// rules[2] = new NumberRule(numbers); // Useful to display numbers with
		// a different color...

		// Ad hoc rule for functions...
		WordRule rule = new WordRule(new IWordDetector() {
			public boolean isWordStart(char c) {
				return Character.isJavaIdentifierStart(c);
			}

			public boolean isWordPart(char c) {
				return Character.isJavaIdentifierPart(c);
			}

		});

		for (int kk = 0; kk < XLemConstants.LEM_FUNCTIONS.length; kk++) {
			rule.addWord(XLemConstants.LEM_FUNCTIONS[kk], functions);
		}

		rules[2] = rule;

		// Ad hoc rule for functions for preloaded objects and vars...
		WordRule rule2 = new WordRule(new IWordDetector() {
			public boolean isWordStart(char c) {
				return Character.isJavaIdentifierStart(c);
			}

			public boolean isWordPart(char c) {
				return Character.isJavaIdentifierPart(c);
			}

		});

		for (int kk = 0; kk < XLemConstants.LEM_PAGE_PREPARED_INSTANCES.length; kk++) {
			rule2.addWord(XLemConstants.LEM_PAGE_PREPARED_INSTANCES[kk],
					preloaded);
		}

		rules[3] = rule2;

		// Ad hoc rule for functions for preloaded ars...

		rules[4] = new VariablesRule();
		
		//rules[5] = new OtherWordsRule();
		
		setRules(rules);
	}

}
