package com.amazecare.service;

import com.amazecare.entity.Bill;

import java.util.List;

public interface BillService {
    Bill createBill(Bill bill);
    Bill updateBill(Long id, Bill bill);
    void deleteBill(Long id);
    Bill getBillById(Long id);
    List<Bill> getAllBills();
}
