package org.htl.adt.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.htl.adt.exception.CommunicationException;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.model.dstu2.resource.Location;
import ca.uhn.fhir.model.dstu2.resource.OperationOutcome;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.client.exceptions.FhirClientConnectionException;

public class RestfulClient {
	FhirContext context = FhirContext.forDstu2();
	String serverURL = "http://127.0.0.1:8081/Ausfallsystem/hapiservlet/";
	IGenericClient client = context.newRestfulGenericClient(serverURL);

	public RestfulClient() {

	}

	/**
	 * Übergibt der Ziel-URL des Clients einen Patienten, der neu erstellt
	 * werden soll
	 * 
	 * @param toCreatePatient
	 */
	public void createPatient(Patient toCreatePatient) {
		try {
			MethodOutcome createStatment = client.create().resource(toCreatePatient).prettyPrint().encodedJson()
					.execute();
		} catch (FhirClientConnectionException e) {
			throw new InternalError("Es konnte keine Verbindung zum Kommunikationsserver hergestellt werden.");
		}

	}

	/**
	 * Übergibt der Ziel-URL des Clients einen Patienten, der aktulisiert werden
	 * soll
	 * 
	 * @param toUpdatePatient
	 */
	public void updatePatient(Patient toUpdatePatient) {
		try {
			MethodOutcome updateStatement = client.update().resource(toUpdatePatient).execute();
		} catch (FhirClientConnectionException e) {
			throw new InternalError("Es konnte keine Verbindung zum Kommunikationsserver hergestellt werden.");
		}

	}

	public Patient readPatientWithID(Patient toReadPatient) {
		try {
		Patient patient = client.read().resource(Patient.class).withId(toReadPatient.getId()).execute();
		return patient;
		} catch (FhirClientConnectionException e) {
			throw new InternalError("Es konnte keine Verbindung zum Kommunikationsserver hergestellt werden.");
		}
	}

	public Location readLocationWithID(Location toReadLoction) {
		try {
		return client.read().resource(Location.class).withId(toReadLoction.getId()).execute();
		} catch (FhirClientConnectionException e) {
			throw new InternalError("Es konnte keine Verbindung zum Kommunikationsserver hergestellt werden.");
		}
	}
	
	public List<Patient> searchPatientWithFamilyName(Patient toSearchPatient) {
		try {
		Bundle results = client.search().forResource(Patient.class)
				.where(Patient.FAMILY.matches().value(toSearchPatient.getNameFirstRep().getFamilyAsSingleString()))
				.returnBundle(Bundle.class).execute();
		List<Patient> allPatients = new ArrayList();
		for (Entry entry : results.getEntry()) {
			allPatients.add((Patient) entry.getResource());
		}
		return allPatients;
		} catch (FhirClientConnectionException e) {
			throw new InternalError("Es konnte keine Verbindung zum Kommunikationsserver hergestellt werden.");
		}
	}
}
