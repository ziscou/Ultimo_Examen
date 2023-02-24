package org.iesvdm.examen.service;

import org.iesvdm.examen.domain.Producto;
import org.iesvdm.examen.exception.ProductoNotFoundException;
import org.iesvdm.examen.repository.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> all() {
        return this.productoRepository.findAll();
    }
    public Map<String, Object> allFilterAndPage(int pagina, int tamanio, Optional<String> busOpt, Optional<String> ordOpt) {

        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Producto> pageAll;

        if (ordOpt.isPresent() && ordOpt.get().equalsIgnoreCase("desc")){
            pageable = PageRequest.of(pagina, tamanio, Sort.by("nombre").descending());
        }else if (ordOpt.isPresent() && ordOpt.get().equalsIgnoreCase("asc")){
            pageable = PageRequest.of(pagina, tamanio, Sort.by("nombre").ascending());
        }
        if (busOpt.isPresent()){
            pageAll = this.productoRepository.findAllByNombreContainingIgnoreCase(busOpt.get(), pageable);
        }else {
            pageAll = this.productoRepository.findAll(pageable);
        }




        Map<String, Object> response = new HashMap<>();

        response.put("productos", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getNumberOfElements());
        response.put("totalPage", pageAll.getTotalPages());

        return response;


    }

    public Producto save(Producto producto) {
        return this.productoRepository.save(producto);
    }

    public Producto one(Long id) {
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    public Producto replace(Long id, Producto producto) {

        return this.productoRepository.findById(id).map( p -> (id.equals(producto.getId())  ?
                                                            this.productoRepository.save(producto) : null))
                .orElseThrow(() -> new ProductoNotFoundException(id));

    }

    public void delete(Long id) {
        this.productoRepository.findById(id).map(p -> {this.productoRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

}
