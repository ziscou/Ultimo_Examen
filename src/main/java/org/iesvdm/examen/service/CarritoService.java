package org.iesvdm.examen.service;

import org.iesvdm.examen.domain.Carrito;
import org.iesvdm.examen.exception.CarritoNotFoundException;
import org.iesvdm.examen.repository.CarritoRepository;
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
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    public List<Carrito> all() {
        return this.carritoRepository.findAll();
    }


    public Carrito save(Carrito carrito) {
        return this.carritoRepository.save(carrito);
    }

    public Carrito one(Long id) {
        return this.carritoRepository.findById(id)
                .orElseThrow(() -> new CarritoNotFoundException(id));
    }

    public Carrito replace(Long id, Carrito carrito) {

        return this.carritoRepository.findById(id).map( p -> (id.equals(carrito.getId())  ?
                                                            this.carritoRepository.save(carrito) : null))
                .orElseThrow(() -> new CarritoNotFoundException(id));

    }

    public void delete(Long id) {
        this.carritoRepository.findById(id).map(p -> {this.carritoRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new CarritoNotFoundException(id));
    }

}
