package pe.edu.upeu.sysalmacenfx.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "upeu_producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @NotNull(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 120, message = "El nombre debe tener entre 2 y 120 caracteres")
    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Positive(message = "El Precio Unitario debe ser positivo")
    @Column(name = "pu", nullable = false)
    private Double pu;
    @Column(name = "puold", nullable = false)
    private Double puOld;
    @Column(name = "utilidad", nullable = false)
    private Double utilidad;
    @Column(name = "stock", nullable = false)
    private Double stock;
    @Column(name = "stockold", nullable = false)
    private Double stockOld;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria",
            nullable = false, foreignKey = @ForeignKey(name = "FK_CATEGORIA_PRODUCTO"))
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca",
            nullable = false, foreignKey = @ForeignKey(name = "FK_MARCA_PRODUCTO"))
    private Marca marca;
    @ManyToOne
    @JoinColumn(name = "id_unidad", referencedColumnName = "id_unidad",
            nullable = false, foreignKey = @ForeignKey(name = "FK_UNIDADMEDIDA_PRODUCTO"))
    private UnidadMedida unidadMedida;
}