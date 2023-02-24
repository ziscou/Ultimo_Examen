package org.iesvdm.examen.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.examen.domain.Producto;
import org.iesvdm.examen.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping(value = {"","/"},  params = {"!buscar", "!ordenar", "!pagina", "!tamanio"})
    public List<Producto> all() {
        log.info("Accediendo a todas las productos");
        return this.productoService.all();
    }
    @GetMapping(value = {"", "/"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam("buscar") Optional<String> buscarOptional,
                                                   @RequestParam("ordenar") Optional<String> ordenarOptional,
                                                   @RequestParam(value = "pagina", defaultValue = "0")int pag,
                                                   @RequestParam(value = "tamanio", defaultValue = "3") int tam){
        log.info("Accediendo a todas las Categorias");
        Map<String, Object> map = this.productoService.allFilterAndPage(pag,tam,buscarOptional,ordenarOptional);

        return ResponseEntity.ok(map);
    }

    @PostMapping({"","/"})
    public Producto newProducto(@RequestBody Producto producto) {
        return this.productoService.save(producto);
    }

    @GetMapping("/{id}")
    public Producto one(@PathVariable("id") Long id) {
        return this.productoService.one(id);
    }

    @PutMapping("/{id}")
    public Producto replaceProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        return this.productoService.replace(id, producto);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable("id") Long id) {
        this.productoService.delete(id);
    }


}
