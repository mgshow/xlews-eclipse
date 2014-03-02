/*
 * Created on 10-apr-2006
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.lemansys.xlews.editors.xlem;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class LemCompletionAssistant implements IContentAssistProcessor {

	protected final static String[] myProposals = XLemConstants.TAGS;
	protected final static String[] myProposalsHTML = XLemConstants.HTML_TAGS;
	protected final static String[] myProposalsXSL = XLemConstants.XSL_TAGS;

	protected int real_offset = 0;

	private Display display = Display.getCurrent();
	private Image xslImage = new Image(display, this.getClass()
			.getResourceAsStream("/icons/xsl.png"));
	private Image htmlImage = new Image(display, this.getClass()
			.getResourceAsStream("/icons/html.png"));
	
	private Image html5Image = new Image(display, this.getClass()
			.getResourceAsStream("/icons/html5.png"));

	private Image lemImage = new Image(display, this.getClass()
			.getResourceAsStream("/icons/web_page.gif"));

	/*
	 * (non-Javadoc) Method declared on IContentAssistProcessor
	 */
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int documentOffset) {
		
		String key = "";

		key = getPartialTag(viewer.getDocument(), documentOffset);

		int finded = 0, i = 0;
		String kk[] = new String[myProposalsHTML.length + myProposalsXSL.length
				+ myProposals.length];

		int lmt = myProposalsHTML.length;
		int lmt2 = lmt + myProposalsXSL.length;

		for (i = 0; i < kk.length; i++) {
			if (i >= lmt2) {

				kk[i] = "<@" + myProposals[i - lmt2] + "@>";

				if (!kk[i].startsWith(key)) {
					kk[i] = "";
				} else
					finded++;

			} else if (i >= lmt) {

				kk[i] = "<" + myProposalsXSL[i - lmt] + ">";

				if (!kk[i].toUpperCase().startsWith(key.toUpperCase())) {
					kk[i] = "";
				} else
					finded++;

			} else {

				
				kk[i] = "<" + myProposalsHTML[i].toLowerCase() + ">";

				if (!kk[i].toUpperCase().startsWith(key.toUpperCase())) {
					kk[i] = "";
				} else
					finded++;

			}

		}

		ICompletionProposal[] result = new ICompletionProposal[finded];

		int fnd = 0;
		
		boolean standAlone=false;
		
		for (i = 0; i < kk.length; i++) {

			
			standAlone=kk[i].endsWith("/>");
			
			if (!kk[i].equals("")) {
				{
					if (kk[i].indexOf("@") >= 0) {

						// Case of Lemansys Tag...
						if (kk[i].startsWith("<@/"))
							result[fnd++] = new CompletionProposal(kk[i],
									real_offset + 1, key.length(),
									kk[i].length() - 0, lemImage, kk[i], null,
									null);
						else
							result[fnd++] = new CompletionProposal(
									kk[i].replaceFirst("@>", " @>"),
									real_offset + 1, key.length(),
									kk[i].length() - 1, lemImage, kk[i], null,
									null);

					} else if (kk[i].startsWith("<xsl:")) {

						result[fnd++] = new CompletionProposal(kk[i]
								+ (standAlone?"":kk[i].replaceFirst("<", "</")),
								real_offset + 1, key.length(),
								kk[i].length() -1,

								xslImage,
								// null,//new Image(new Device(new
								// DeviceData()),"icons/sample.gif"),//null,//Image
								// image
								kk[i], null,
								// null,//IContextInformation
								// contextInformation,
								null// String additionalProposalInfo
						);

					} else if (kk[i].startsWith("</xsl:")) {

						result[fnd++] = new CompletionProposal(kk[i],
								real_offset + 1, key.length(), kk[i].length(),
								xslImage, kk[i], null, null);

					} else {

						// Case of HTML...
						if (kk[i].startsWith("</"))
							if(kk[i].endsWith("5>")) {
								String x=kk[i].replaceFirst("5", "");
								result[fnd++] = new CompletionProposal(x,
										real_offset + 1, key.length(),
										x.length() - 0, html5Image, x, null,
										null);
							}else {
							result[fnd++] = new CompletionProposal(kk[i],
									real_offset + 1, key.length(),
									kk[i].length() - 0, htmlImage, kk[i], null,
									null);
							}
						else {
							if(kk[i].endsWith("5>")) {
								String x=kk[i].replaceFirst("5", "");
								standAlone=x.endsWith("/>");
								result[fnd++] = new CompletionProposal(x+(standAlone?"":x.replaceFirst("<", "</")),
										real_offset + 1, key.length(),
										x.length() - (standAlone?0:1), html5Image, x, null,
										null);
							} else {
							result[fnd++] = new CompletionProposal(kk[i]+(standAlone?"":kk[i].replaceFirst("<", "</")),
									real_offset + 1, key.length(),
									kk[i].length() - (standAlone?0:1), htmlImage, kk[i], null,
									null);
							}
						}
					}
				}
			}

		}

		return result;
	}

	/*
	 * (non-Javadoc) Method declared on IContentAssistProcessor
	 */
	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[] { '<' };
	}

	/*
	 * (non-Javadoc) Method declared on IContentAssistProcessor
	 */
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	// For Context information
	/*
	 * (non-Javadoc) Method declared on IContentAssistProcessor
	 */

	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	/*
	 * (non-Javadoc) Method declared on IContentAssistProcessor
	 */
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int documentOffset) {

		return null;
	}

	/*
	 * (non-Javadoc) Method declared on IContentAssistProcessor
	 */
	public String getErrorMessage() {
		return null;
	}

	public String getPartialTag(IDocument doc, int pos) {

		if (doc == null)
			return "";

		int p = pos - 1;

		String ret = "";

		try {

			boolean done = false;
			char c;
			StringBuffer buff = new StringBuffer();
			while (!done) {
				c = doc.getChar(p--);
				buff.append(c);
				if (c == '<' || p < 0)
					done = true;

			}

			real_offset = p;

			String source = buff.toString();

			int i, len = source.length();
			StringBuffer dest = new StringBuffer(len);

			for (i = (len - 1); i >= 0; i--)
				dest.append(source.charAt(i));
			return dest.toString();

		}

		catch (Exception e) {
		}

		return ret;

	}
}
