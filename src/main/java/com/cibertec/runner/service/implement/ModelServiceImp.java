package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.runner.dto.request.FilterModelDTO;
import com.cibertec.runner.dto.request.ModelDTO;
import com.cibertec.runner.dto.response.ModeloProductoResponse;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Garment;
import com.cibertec.runner.repository.IModelRepository;
import com.cibertec.runner.repository.IProductRepository;
import com.cibertec.runner.service.ModelService;

import jakarta.persistence.NoResultException;

@Service
public class ModelServiceImp implements ModelService{

	@Autowired
	private IModelRepository dao;
	@Autowired
	private IProductRepository repositoryProducto;
	

    @Override
    public ResponseEntity<SuccessResponse<List<Garment>>> findAllModel() {
        List<Garment> modelos = dao.findAll();

        if (modelos.isEmpty()) {
        	throw new NoResultException("No se encontro ningun modelo");
        }
        
        SuccessResponse<List<Garment>> success = SuccessResponse.<List<Garment>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(modelos)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Garment>> saveModel(ModelDTO modeloDTO) {
    	
    	Garment modelo = new Garment();
        modelo.setState(true);
        modelo.setPrice(modeloDTO.getPrice());
        modelo.setIdCtg(modeloDTO.getIdCtg());
        modelo.setIdBrd(modeloDTO.getIdMrc());
        modelo.setIdMtl(modeloDTO.getIdMtl());

        Garment modeloGuardado = dao.save(modelo);

        SuccessResponse<Garment> success = SuccessResponse.<Garment>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(modeloGuardado)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Garment>> updateModel(ModelDTO modeloDTO, Integer id) {

    	Garment modEncontrada = dao.findById(id).orElse(null);

        if (modEncontrada == null) {
        	throw new NoResultException("No se encontro el codigo del modelo");
        } 

        modEncontrada.setState(true);
        modEncontrada.setPrice(modeloDTO.getPrice());
        modEncontrada.setIdCtg(modeloDTO.getIdCtg());
        modEncontrada.setIdBrd(modeloDTO.getIdMrc());
        modEncontrada.setIdMtl(modeloDTO.getIdMtl());

        Garment modeloActualizado = dao.save(modEncontrada);

        SuccessResponse<Garment> success = SuccessResponse.<Garment>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(modeloActualizado)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdModel(Integer id) {
    	Garment modEncontrado = dao.findById(id).orElse(null);

        if (modEncontrado == null) {
        	throw new NoResultException("No se encontro el codigo del modelo");
        }        	
        modEncontrado.setState(false);
        
        dao.save(modEncontrado);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NO_CONTENT.value())
                .success(HttpStatus.NO_CONTENT.getReasonPhrase())
                .response("Modelo eliminado correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success);
    }

	@Override
	public ResponseEntity<SuccessResponse<Garment>> findByIdModel(Integer id) {
		Garment modEncontrado = dao.findById(id).orElse(null);
		if (modEncontrado == null) {
        	throw new NoResultException("No se encontro el codigo del modelo");
		}
		
		SuccessResponse<Garment> success = SuccessResponse.<Garment>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(modEncontrado)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<List<Garment>>> findByIdMrc(Integer id) {
		List<Garment> modelos = dao.findByIdBrd(id);

        if (!modelos.isEmpty()) {
            SuccessResponse<List<Garment>> success = SuccessResponse.<List<Garment>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(modelos)
                    .build();

            return ResponseEntity.ok(success);
        } else {
            throw new RuntimeException("No se encontraron modelos para la marca con ID: " + id);
        }
	}
	
	@Override
	@Transactional
    public ResponseEntity<SuccessResponse<List<Garment>>> findByAttributes(FilterModelDTO filtro) {

        String idClrCsv = listToCsv(filtro.getIdClr());
        String idTllCsv = listToCsv(filtro.getIdTll());
        String idCtgCsv = listToCsv(filtro.getIdCtg());
        String idMrcCsv = listToCsv(filtro.getIdMrc());
        String idPrnCsv = listToCsv(filtro.getIdPrn());
        String idMtlCsv = listToCsv(filtro.getIdMtl());

        List<Garment> productos = dao.filtrarModelos(
            idClrCsv, 
            idTllCsv, 
            idCtgCsv, 
            idMrcCsv, 
            idPrnCsv, 
            idMtlCsv
        );;
        

        if (productos.isEmpty()) {
            throw new NoResultException("No se encontraron modelos para el filtro enviado ");
        }
        
        SuccessResponse<List<Garment>> success = SuccessResponse.<List<Garment>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(productos)
                .build();

        return ResponseEntity.ok(success);
        
    }
	
	@Override
	public ResponseEntity<SuccessResponse<ModeloProductoResponse>> findProductosByModel(Integer id) {

//		Garment modEncontrado = dao.findById(id).orElse(null);
//		
//		if (modEncontrado == null) {
//        	throw new NoResultException("No se encontro el codigo del modelo");
//		}
//		
//        List<Product> productos = repositoryProducto.findByIdMdl(modEncontrado.getId());
//        
//        if(productos.isEmpty()) {
//        	throw new NoResultException("No se encontro ningun producto registrado con ese modelo");
//        }
//        
//        ModeloProductoResponse mpResponse = new ModeloProductoResponse();
//        mpResponse.setId(modEncontrado.getId());
//        mpResponse.setEstado(modEncontrado.getState());
//        mpResponse.setPrecio(modEncontrado.getPrice());
//        mpResponse.setIdCtg(modEncontrado.getIdCtg());
//        mpResponse.setIdMrc(modEncontrado.getIdBrd());
//        mpResponse.setIdMtl(modEncontrado.getIdMtl());
//        mpResponse.setProductos(productos);
//        
//            SuccessResponse<ModeloProductoResponse> success = SuccessResponse.<ModeloProductoResponse>builder()
//                    .timestamp(LocalDateTime.now())
//                    .status(HttpStatus.OK.value())
//                    .success(HttpStatus.OK.getReasonPhrase())
//                    .response(mpResponse)
//                    .build();
//
//            return ResponseEntity.ok(success);
		return null;
    }

    private String listToCsv(List<Integer> list) {
        return (list == null || list.isEmpty()) ? null : list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

}
