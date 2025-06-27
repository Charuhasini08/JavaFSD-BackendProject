package com.amazecare.service;

import com.amazecare.entity.Staff;

import java.util.List;

public interface StaffService {
    Staff addStaff(Staff staff);
    Staff updateStaff(Long id, Staff staff);
    void deleteStaff(Long id);
    Staff getStaffById(Long id);
    List<Staff> getAllStaff();
}
