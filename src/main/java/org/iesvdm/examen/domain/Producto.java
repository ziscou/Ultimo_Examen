package org.iesvdm.examen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    private String nombre;

    private String descrip;

    @Column(name = "img_url")
    private String imgUrl;

    private String sku;

    @Column(scale = 3, precision = 10)
    private BigDecimal precio;

    private Long cantidad;

    @OneToMany(mappedBy = "producto",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Carrito> carritos;

    @ManyToOne()
    @JoinColumn(name = "id_categoria", foreignKey = @ForeignKey(name = "FK_categoria"))
    private Categoria categoria;
}
