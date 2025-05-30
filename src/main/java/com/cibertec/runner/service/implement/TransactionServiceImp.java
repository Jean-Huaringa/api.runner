package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Transaction;
import com.cibertec.runner.model.TransactionId;
import com.cibertec.runner.repository.ITransactionRepository;
import com.cibertec.runner.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private ITransactionRepository iTransaccionRepository;

   	@Override
	public List<Transaction> findAllListTransaccion() {
		return iTransaccionRepository.findAll();
	}

	@Override
	@Transactional
	public Transaction saveTransaccion(Transaction transaccion) {
		  return iTransaccionRepository.save(transaccion);
	}

	@Override
	public ResponseEntity<SuccessResponse<String>> deleteByIdTransaccion(TransactionId id) {
	    Optional<Transaction> transaccionExiste = iTransaccionRepository.findById(id);

	    if (transaccionExiste.isPresent()) {
	        iTransaccionRepository.delete(transaccionExiste.get());

	        SuccessResponse<String> success = SuccessResponse.<String>builder()
	                .timestamp(LocalDateTime.now())
	                .status(HttpStatus.NO_CONTENT.value())
	                .success(HttpStatus.NO_CONTENT.getReasonPhrase())
	                .response("Transacción eliminada con éxito")
	                .build();

	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success);
	    } else {
	        throw new RuntimeException("No se realizó la eliminación, transacción no encontrada");
	    }
	}
	
	
}
