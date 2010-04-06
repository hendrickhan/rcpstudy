package de.vogella.rcp.intro.edtor.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import de.vogella.rcp.intro.edtor.model.Person;

public class MyPersonEditorInput implements IEditorInput {
	
	private final Person person;
	
	public MyPersonEditorInput(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return person;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean equals(Object obj) {
		if(super.equals(obj)) {
			return true;
		}
		if(obj instanceof MyPersonEditorInput) {
			return person.equals(((MyPersonEditorInput) obj).getPerson());
		}
		return false;
	}
	
	public int hashCode() {
		return person.hashCode();
	}
}
