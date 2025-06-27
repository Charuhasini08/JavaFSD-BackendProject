package com.amazecare.service;

import com.amazecare.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient addPatient(Patient patient);
    Patient updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
    Patient getPatientById(Long id);
    List<Patient> getAllPatients();
}
