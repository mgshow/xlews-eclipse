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

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class LemConfiguration extends SourceViewerConfiguration {
	private LemDoubleClickStrategy doubleClickStrategy;
	private LemTagScanner tagScanner;
	private XLemScanner scanner;
	private XLemColorManager colorManager;

	public LemConfiguration(XLemColorManager colorManager) {
		this.colorManager = colorManager;
	}

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE,
				LemPartitionScanner.LEM_COMMENT, LemPartitionScanner.LEM_TAG };
	}

	public ITextDoubleClickStrategy getDoubleClickStrategy(
			ISourceViewer sourceViewer, String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new LemDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected XLemScanner getLemScanner() {
		if (scanner == null) {
			scanner = new XLemScanner(colorManager);
			scanner.setDefaultReturnToken(new Token(new TextAttribute(
					colorManager.getColor(IXLemColorConstants.DEFAULT))));
		}
		return scanner;
	}

	protected LemTagScanner getLemTagScanner() {
		if (tagScanner == null) {
			tagScanner = new LemTagScanner(colorManager);
			tagScanner.setDefaultReturnToken(new Token(new TextAttribute(
					colorManager.getColor(IXLemColorConstants.TAG))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
				getLemTagScanner());
		reconciler.setDamager(dr, LemPartitionScanner.LEM_TAG);
		reconciler.setRepairer(dr, LemPartitionScanner.LEM_TAG);

		dr = new DefaultDamagerRepairer(getLemScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(
				new TextAttribute(
						colorManager.getColor(IXLemColorConstants.XML_COMMENT)));
		reconciler.setDamager(ndr, LemPartitionScanner.LEM_COMMENT);
		reconciler.setRepairer(ndr, LemPartitionScanner.LEM_COMMENT);
		
		

		return reconciler;
	}

	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {

		ContentAssistant assistant = new ContentAssistant();
		
		assistant.setContentAssistProcessor(new LemCompletionAssistant(),
				IDocument.DEFAULT_CONTENT_TYPE);

		assistant.enableAutoActivation(true);
		assistant.setAutoActivationDelay(200);

		assistant.enableAutoInsert(true);

		assistant
				.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
		

		return assistant;
	}

}