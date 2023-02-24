package org.iesvdm.examen.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long id;

    private Long cantidad;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    @Column(name = "fecha_creado")
    private Date fechaCreado = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    @Column(name = "fecha_modificado")
    private Date fechaModificado = new Date();

    @ManyToOne()
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_usuario"))
    private Usuario usuario;

    @ManyToOne()
    @JoinColumn(name = "id_producto", foreignKey = @ForeignKey(name = "FK_producto"))
    private Producto producto;



}
