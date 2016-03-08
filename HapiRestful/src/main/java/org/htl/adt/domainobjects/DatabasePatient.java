package org.htl.adt.domainobjects;



public class DatabasePatient {
	
	int patient_id;
	
	String socialSecurityNumber;
	
	String firstName;
	
	String lastName;
	
	String fhirMessage;
	
	
	
	public DatabasePatient() {
		super();
	}

	public DatabasePatient(String socialSecurityNumber, String firstName, String lastName, String fhirMessage) {
		super();
		this.socialSecurityNumber = socialSecurityNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fhirMessage = fhirMessage;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFhirMessage() {
		return fhirMessage;
	}

	public void setFhirMessage(String fhirMessage) {
		this.fhirMessage = fhirMessage;
	}
	
	
	
	
	
	
	

}