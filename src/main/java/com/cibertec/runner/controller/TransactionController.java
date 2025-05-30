package com.cibertec.runner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Transaction;
import com.cibertec.runner.model.TransactionId;
import com.cibertec.runner.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	
	@Autowired
    private TransactionService service;

    @GetMapping
    public ResponseEntity<List<Transaction>> findAllListTransaccion() {
        return ResponseEntity.ok(service.findAllListTransaccion());
    }

    @PostMapping
    public ResponseEntity<Transaction> saveTransaccion(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(service.saveTransaccion(transaction));
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<SuccessResponse<String>> deleteByIdTransaccion(@RequestBody TransactionId id) {
    	System.out.println("Entrando DeleteMapping");
        return service.deleteByIdTransaccion(id);
    }
}
