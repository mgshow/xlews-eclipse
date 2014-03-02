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

public class LemPartitionScanner extends RuleBasedPartitionScanner {
	public final static String LEM_COMMENT = "__lem_comment";
	public final static String LEM_TAG = "__lem_tag";
	public final static String HTML_TAG = "__html_tag";

	public LemPartitionScanner() {

		IToken xmlComment = new Token(LEM_COMMENT);
		IToken tag = new Token(LEM_TAG);
		IToken html = new Token(HTML_TAG);

		IPredicateRule[] rules = new IPredicateRule[3];

		int k=0;
		
		rules[k++] = new MultiLineRule("<@--", "--@>", xmlComment);
		rules[k++] = new SingleLineRule("<@//", "@>", xmlComment);
		rules[k++] = new LemTagRule(tag);
		//rules[k++] = new MultiLineRule("<", ">", html);
		
		
		//rules[k++] = new LemTagRule(tag);

		setPredicateRules(rules);
	}
}
