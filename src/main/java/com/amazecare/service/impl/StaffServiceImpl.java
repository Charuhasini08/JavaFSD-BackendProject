package com.amazecare.service.impl;

import com.amazecare.entity.Staff;
import com.amazecare.repository.StaffRepository;
import com.amazecare.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Long id, Staff updatedStaff) {
        Staff staff = getStaffById(id);
        staff.setName(updatedStaff.getName());
        staff.setRole(updatedStaff.getRole());
        staff.setDepartment(updatedStaff.getDepartment());
        staff.setContactNumber(updatedStaff.getContactNumber());
        return staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }
}
