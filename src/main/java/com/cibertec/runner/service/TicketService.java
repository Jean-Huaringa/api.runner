package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.TicketDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Ticket;

public interface TicketService {

	ResponseEntity<SuccessResponse<List<Ticket>>> findAllTickets();

	ResponseEntity<SuccessResponse<Ticket>> findByIdTicket(Integer id);

	ResponseEntity<SuccessResponse<Ticket>> saveTicket(TicketDTO ticketDTO);

	ResponseEntity<SuccessResponse<Ticket>> updateTicket(TicketDTO ticketDTO, Integer id);

	ResponseEntity<SuccessResponse<String>> deleteTicket(Integer id);

}
