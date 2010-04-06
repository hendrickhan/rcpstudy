package de.vogella.rcp.intro.edtor.provider;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.vogella.rcp.intro.edtor.model.MyModel;

public class MyContentProvider implements IStructuredContentProvider, PropertyChangeListener {
	private final Viewer viewer;
	
	public MyContentProvider(Viewer viewer) {
		this.viewer = viewer;
	}

	public Object[] getElements(Object inputElement) {
		MyModel content = (MyModel) inputElement;
		return content.getPersons().toArray();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void propertyChange(PropertyChangeEvent evt) {
		viewer.refresh();
	}

}
