package com.SafetyNet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SafetyNet.alerts.dto.UpdateMedicalRecordRequest;
import com.SafetyNet.alerts.model.MedicalRecord;
import com.SafetyNet.alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@PostMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalrecord) {
    	try {
    		return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService
    				.createMedicalRecord(medicalrecord)); 
    	} catch (Exception e) {
 			return ResponseEntity.status(HttpStatus.CONFLICT).build();
 		}
	}
	
	@PutMapping("/medicalRecord/{name}")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable("name") String name, 
			@RequestBody UpdateMedicalRecordRequest medicalRecord) {
			return medicalRecordService.updateMedicalRecord(name, medicalRecord).map(mr -> ResponseEntity.ok(mr))
					.orElse(ResponseEntity.notFound().build());
		}
	
	@DeleteMapping("/medicalRecord/{name}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMedicalRecord(@PathVariable("name") final String name) {
		medicalRecordService.deleteMedicalRecord(name);
	}
}
