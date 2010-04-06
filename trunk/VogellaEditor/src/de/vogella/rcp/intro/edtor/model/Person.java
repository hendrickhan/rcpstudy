package de.vogella.rcp.intro.edtor.model;

public class Person {
	private String firstName;
	private String lastName;
	private Address address;
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String toString() {
		return firstName + " " + lastName;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		
		return result;
	}
	
	/**
	 * return boolean value for equals
	 */
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(obj == null) {
			return false;
		}
		
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		Person other = (Person) obj;
		if(firstName == null) {
			if(other.firstName != null) {
				return false;
			}
		} else if(!firstName.equals(other.firstName)) {
			return false;
		}
		
		if(lastName == null) {
			if(other.lastName != null) {
				return false;
			}
		} else if(!lastName.equals(other.lastName)) {
			return false;
		}
		
		return true;
	}
}
