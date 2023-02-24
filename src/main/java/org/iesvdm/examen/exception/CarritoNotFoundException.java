package org.iesvdm.examen.exception;

public class CarritoNotFoundException extends RuntimeException{
    public CarritoNotFoundException(Long id) {
        super("Not found Carrito with id: " + id);
    }
}
