package org.lemansys.xlews.eclipse.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.lemansys.xlews.eclipse.views.XLemServerView;

public class XLemPerspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {

		layout.setEditorAreaVisible(true);

		layout.addView(IPageLayout.ID_PROJECT_EXPLORER, IPageLayout.LEFT,
				new Float(0.20).floatValue(), IPageLayout.ID_EDITOR_AREA);

		IFolderLayout btmz = layout.createFolder("bottomz", IPageLayout.BOTTOM,
				new Float(0.70), IPageLayout.ID_EDITOR_AREA);

		btmz.addView(IPageLayout.ID_PROBLEM_VIEW);
		btmz.addView(XLemServerView.ID);
		btmz.addView(IPageLayout.ID_TASK_LIST);
		btmz.addView(IPageLayout.ID_BOOKMARKS);

	}

}
