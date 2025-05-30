package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Transaction;
import com.cibertec.runner.model.TransactionId;

public interface TransactionService {
		
	List<Transaction> findAllListTransaccion();
    
	Transaction saveTransaccion(Transaction transaccion);
    
    public  ResponseEntity<SuccessResponse<String>> deleteByIdTransaccion(TransactionId id);
	
}
