package com.amazecare.service.impl;

import com.amazecare.entity.Bill;
import com.amazecare.repository.BillRepository;
import com.amazecare.service.BillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill createBill(Bill bill) {
        bill.setTotalAmount(
                bill.getConsultationFee() +
                bill.getMedicineCharges() +
                bill.getOtherCharges()
        );
        return billRepository.save(bill);
    }

    @Override
    public Bill updateBill(Long id, Bill updatedBill) {
        Bill bill = getBillById(id);
        bill.setPatientName(updatedBill.getPatientName());
        bill.setBillingDate(updatedBill.getBillingDate());
        bill.setConsultationFee(updatedBill.getConsultationFee());
        bill.setMedicineCharges(updatedBill.getMedicineCharges());
        bill.setOtherCharges(updatedBill.getOtherCharges());
        bill.setTotalAmount(
                updatedBill.getConsultationFee() +
                updatedBill.getMedicineCharges() +
                updatedBill.getOtherCharges()
        );
        return billRepository.save(bill);
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
}
