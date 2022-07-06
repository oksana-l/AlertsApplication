package com.SafetyNet.alerts.configuration;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.SafetyNet.alerts.model.Store;

public class StoreConfigurationTest {

	@Test
	public void shouldBuildStore() throws IOException {
		StoreConfiguration storeConfiguration = new StoreConfiguration();
		Store store = storeConfiguration.store();
		
		Assertions.assertNotNull(store);
		Assertions.assertTrue(0 < store.getPersons().size());
		Assertions.assertTrue(0 <  store.getFirestations().size());
		Assertions.assertTrue(0 <  store.getMedicalrecords().size());
	}
}
