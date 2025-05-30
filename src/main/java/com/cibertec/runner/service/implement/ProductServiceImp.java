package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.runner.dto.request.FilterProductDTO;
import com.cibertec.runner.dto.request.ProductDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Product;
import com.cibertec.runner.repository.IProductRepository;
import com.cibertec.runner.service.ProductService;

import jakarta.persistence.NoResultException;

@Service

public class ProductServiceImp implements ProductService {

	@Autowired
	private IProductRepository prorepo;

    @Override
    public ResponseEntity<SuccessResponse<List<Product>>> findAllProductos() {
        List<Product> productos = prorepo.findAll();

        if (productos.isEmpty()) {
        	throw new NoResultException("No se encontro ningun producto");
        }
        
        SuccessResponse<List<Product>> success = SuccessResponse.<List<Product>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(productos)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Product>> findByIdProducto(Integer id) {
    	Product producto = prorepo.findById(id).orElse(null);

        if (producto == null) {
        	throw new NoResultException("No se encontro ningun producto");
        }

        SuccessResponse<Product> success = SuccessResponse.<Product>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(producto)
                .build();

        return ResponseEntity.ok(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Product>> saveProducto(ProductDTO productoDTO) {
    	
    	Product producto = new Product();
        producto.setStock(productoDTO.getStock());
        producto.setIdClr(productoDTO.getIdClr());
        producto.setIdTll(productoDTO.getIdTll());
        producto.setIdMdl(productoDTO.getIdMdl());

        Product productoGuardado = prorepo.save(producto);

        SuccessResponse<Product> success = SuccessResponse.<Product>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(productoGuardado)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Product>> updateProducto(Product producto, Integer id) {
    	Product productoExistente = prorepo.findById(id).orElse(null);

        if (productoExistente == null) {
        	throw new NoResultException("No se encontro el codigo del producto");
        }
        
        productoExistente.setStock(producto.getStock());
        productoExistente.setIdClr(producto.getIdClr());
        productoExistente.setIdTll(producto.getIdTll());
        productoExistente.setIdMdl(producto.getIdMdl());

        Product productoGuardado = prorepo.save(productoExistente);

        SuccessResponse<Product> success = SuccessResponse.<Product>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(productoGuardado)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdProducto(Integer id) {
    	Product productoExiste = prorepo.findById(id).orElse(null);

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
    public ResponseEntity<SuccessResponse<List<Product>>> findByIdMdl(Integer idMdl) {
        List<Product> productos = prorepo.findByIdMdl(idMdl);

        if (!productos.isEmpty()) {
            SuccessResponse<List<Product>> success = SuccessResponse.<List<Product>>builder()
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
    public ResponseEntity<SuccessResponse<List<Product>>> findByAttributes(FilterProductDTO filtro) {

        String idClrCsv = listToCsv(filtro.getIdClr());
        String idTllCsv = listToCsv(filtro.getIdTll());
        String idCtgCsv = listToCsv(filtro.getIdCtg());
        String idMrcCsv = listToCsv(filtro.getIdMrc());
        String idPrnCsv = listToCsv(filtro.getIdPrn());
        String idMtlCsv = listToCsv(filtro.getIdMtl());
        
        System.out.println(filtro.getIdClr().toString());

        List<Product> productos = prorepo.filtrarProductos(
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
        
        SuccessResponse<List<Product>> success = SuccessResponse.<List<Product>>builder()
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
