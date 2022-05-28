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
		Assertions.assertEquals(23, store.getPersons().size());
		Assertions.assertEquals(13, store.getFirestations().size());
		Assertions.assertEquals(23, store.getMedicalrecords().size());
	}
}
