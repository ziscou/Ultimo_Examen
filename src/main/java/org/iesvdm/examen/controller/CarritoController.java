package org.iesvdm.examen.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.examen.domain.Carrito;
import org.iesvdm.examen.service.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/carritos")
public class CarritoController {
    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping(value = {"","/"})
    public List<Carrito> all() {
        log.info("Accediendo a todas las carritos");
        return this.carritoService.all();
    }

    @PostMapping({"","/"})
    public Carrito newCarrito(@RequestBody Carrito carrito) {
        return this.carritoService.save(carrito);
    }

    @GetMapping("/{id}")
    public Carrito one(@PathVariable("id") Long id) {
        return this.carritoService.one(id);
    }

    @PutMapping("/{id}")
    public Carrito replaceCarrito(@PathVariable("id") Long id, @RequestBody Carrito carrito) {
        return this.carritoService.replace(id, carrito);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCarrito(@PathVariable("id") Long id) {
        this.carritoService.delete(id);
    }


}
