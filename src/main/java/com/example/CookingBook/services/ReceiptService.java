package com.example.CookingBook.services;

import com.example.CookingBook.models.entity.ReceiptEntity;
import com.example.CookingBook.models.entity.UserEntity;
import com.example.CookingBook.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReceiptService {


    private final ReceiptRepository receiptRepository;
    private final MeasureUnitsService measureUnitsService;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository, MeasureUnitsService measureUnitsService) {
        this.receiptRepository = receiptRepository;
        this.measureUnitsService = measureUnitsService;
    }


    public void saveAndFlush(ReceiptEntity receiptEntity) {
        receiptRepository.saveAndFlush(receiptEntity);
    }

    public ReceiptEntity findById(Long id) {
        return receiptRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Page<ReceiptEntity> getPageableReceiptEntityPage(int page){
        return receiptRepository.findAll(PageRequest.of(page, 12, Sort.by(Sort.Direction.ASC, "id")));
    }
    public Page<ReceiptEntity> getPageableReceiptEntityPageByUser(UserEntity user, int page){
        return receiptRepository.findAllByUser(user,PageRequest.of(page, 12, Sort.by(Sort.Direction.ASC, "id")));
    }

    public void deleteReceipt(ReceiptEntity receiptEntity){
        receiptRepository.delete(receiptEntity);
    }

}
