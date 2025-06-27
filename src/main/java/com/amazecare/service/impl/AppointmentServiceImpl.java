package com.amazecare.service.impl;

import com.amazecare.entity.Appointment;
import com.amazecare.repository.AppointmentRepository;
import com.amazecare.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment updated) {
        Appointment existing = getAppointmentById(id);
        existing.setPatientName(updated.getPatientName());
        existing.setDoctorName(updated.getDoctorName());
        existing.setAppointmentDate(updated.getAppointmentDate());
        existing.setReason(updated.getReason());
        return appointmentRepository.save(existing);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
