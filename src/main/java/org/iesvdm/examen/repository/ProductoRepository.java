package org.iesvdm.examen.repository;

import org.iesvdm.examen.domain.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    public Page<Producto> findAllByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
