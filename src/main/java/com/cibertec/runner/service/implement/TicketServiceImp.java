package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.request.TicketDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Ticket;
import com.cibertec.runner.model.User;
import com.cibertec.runner.repository.ITicketRepository;
import com.cibertec.runner.repository.IUserRepository;
import com.cibertec.runner.service.TicketService;

import jakarta.transaction.Transactional;


@Service
public class TicketServiceImp implements TicketService {

	@Autowired
	private ITicketRepository tkRepo;
	
	@Autowired
	private IUserRepository usuRepo;

    @Override
    public ResponseEntity<SuccessResponse<List<Ticket>>> findAllTickets() {
        List<Ticket> tickets = tkRepo.findAll();

        if (!tickets.isEmpty()) {
            SuccessResponse<List<Ticket>> response = SuccessResponse.<List<Ticket>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(tickets)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            throw new RuntimeException("No existen registros");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Ticket>> findByIdTicket(Integer id) {
        Optional<Ticket> ticket = tkRepo.findById(id);

        if (ticket.isPresent()) {
            SuccessResponse<Ticket> response = SuccessResponse.<Ticket>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(ticket.get())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("No se encuentra un registro para el ID: " + id);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<SuccessResponse<Ticket>> saveTicket(TicketDTO ticketDTO) {
        User usuario = usuRepo.findById(ticketDTO.getIdUsr())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Ticket ticket = new Ticket();
        ticket.setIdUsr(usuario.getId());
        ticket.setDirection(ticketDTO.getDireccion());

        Ticket ticketGuardado = tkRepo.save(ticket);

        SuccessResponse<Ticket> response = SuccessResponse.<Ticket>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(ticketGuardado)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @Transactional
    public ResponseEntity<SuccessResponse<Ticket>> updateTicket(TicketDTO ticketDTO, Integer id) {
        Optional<Ticket> ticketOpt = tkRepo.findById(id);

        User usuario = usuRepo.findById(ticketDTO.getIdUsr())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            ticket.setIdUsr(usuario.getId());
            ticket.setDirection(ticketDTO.getDireccion());

            Ticket ticketActualizado = tkRepo.save(ticket);

            SuccessResponse<Ticket> response = SuccessResponse.<Ticket>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(ticketActualizado)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            throw new RuntimeException("Ticket no encontrado para actualizar");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteTicket(Integer id) {
        Optional<Ticket> ticket = tkRepo.findById(id);

        if (ticket.isPresent()) {
            tkRepo.delete(ticket.get());

            SuccessResponse<String> response = SuccessResponse.<String>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.NO_CONTENT.value())
                    .success(HttpStatus.NO_CONTENT.getReasonPhrase())
                    .response("Ticket eliminado correctamente")
                    .build();

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            throw new RuntimeException("No se realizó la eliminación, Ticket no encontrado");
        }
    }
}
