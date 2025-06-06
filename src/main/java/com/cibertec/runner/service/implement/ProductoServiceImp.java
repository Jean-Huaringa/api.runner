package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.runner.dto.request.FiltroProductoDTO;
import com.cibertec.runner.dto.request.ProductoDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Producto;
import com.cibertec.runner.repository.IProductoRepository;
import com.cibertec.runner.service.ProductoService;

import jakarta.persistence.NoResultException;

@Service

public class ProductoServiceImp implements ProductoService {

	@Autowired
	private IProductoRepository prorepo;

    @Override
    public ResponseEntity<SuccessResponse<List<Producto>>> findAllProductos() {
        List<Producto> productos = prorepo.findAll();

        if (productos.isEmpty()) {
        	throw new NoResultException("No se encontro ningun producto");
        }
        
        SuccessResponse<List<Producto>> success = SuccessResponse.<List<Producto>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(productos)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Producto>> findByIdProducto(Integer id) {
        Producto producto = prorepo.findById(id).orElse(null);

        if (producto == null) {
        	throw new NoResultException("No se encontro ningun producto");
        }

        SuccessResponse<Producto> success = SuccessResponse.<Producto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(producto)
                .build();

        return ResponseEntity.ok(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Producto>> saveProducto(ProductoDTO productoDTO) {
    	
        Producto producto = new Producto();
        producto.setStock(productoDTO.getStock());
        producto.setIdClr(productoDTO.getIdClr());
        producto.setIdTll(productoDTO.getIdTll());
        producto.setIdMdl(productoDTO.getIdMdl());

        Producto productoGuardado = prorepo.save(producto);

        SuccessResponse<Producto> success = SuccessResponse.<Producto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(productoGuardado)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Producto>> updateProducto(Producto producto, Integer id) {
        Producto productoExistente = prorepo.findById(id).orElse(null);

        if (productoExistente == null) {
        	throw new NoResultException("No se encontro el codigo del producto");
        }
        
        productoExistente.setStock(producto.getStock());
        productoExistente.setIdClr(producto.getIdClr());
        productoExistente.setIdTll(producto.getIdTll());
        productoExistente.setIdMdl(producto.getIdMdl());

        Producto productoGuardado = prorepo.save(productoExistente);

        SuccessResponse<Producto> success = SuccessResponse.<Producto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(productoGuardado)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdProducto(Integer id) {
        Producto productoExiste = prorepo.findById(id).orElse(null);

        if (productoExiste == null) {
        	throw new NoResultException("No se encontro el codigo del producto");
        }
        
        prorepo.delete(productoExiste);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Producto eliminado correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<List<Producto>>> findByIdMdl(Integer idMdl) {
        List<Producto> productos = prorepo.findByIdMdl(idMdl);

        if (!productos.isEmpty()) {
            SuccessResponse<List<Producto>> success = SuccessResponse.<List<Producto>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(productos)
                    .build();

            return ResponseEntity.ok(success);
        } else {
            throw new RuntimeException("No se encontraron productos para el modelo con ID: " + idMdl);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<SuccessResponse<List<Producto>>> findByAttributes(FiltroProductoDTO filtro) {

        String idClrCsv = listToCsv(filtro.getIdClr());
        String idTllCsv = listToCsv(filtro.getIdTll());
        String idCtgCsv = listToCsv(filtro.getIdCtg());
        String idMrcCsv = listToCsv(filtro.getIdMrc());
        String idPrnCsv = listToCsv(filtro.getIdPrn());
        String idMtlCsv = listToCsv(filtro.getIdMtl());
        
        System.out.println(filtro.getIdClr().toString());

        List<Producto> productos = prorepo.filtrarProductos(
            idClrCsv, 
            idTllCsv, 
            idCtgCsv, 
            idMrcCsv, 
            idPrnCsv, 
            idMtlCsv
        );;
        

        if (productos.isEmpty()) {
            throw new NoResultException("No se encontraron productos para el filtro enviado ");
        }
        
        SuccessResponse<List<Producto>> success = SuccessResponse.<List<Producto>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(productos)
                .build();

        return ResponseEntity.ok(success);
    }

    private String listToCsv(List<Integer> list) {
        return (list == null || list.isEmpty()) ? null : list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

}
