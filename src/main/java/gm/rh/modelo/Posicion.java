package gm.rh.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;      // con ello se crea la tabla bd en autmatico
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;                     // para crear los get y setters automarticos.
import lombok.NoArgsConstructor;        // consturctor sin argumentos
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Posicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // se genera en automatico la llave primaria.
    
    Integer idPosicion;
    String instrumento;
    String plazo;
    LocalDate fechaRegistro;
    LocalDate fechaSubasta;

    LocalDate fechaFinal;
    Boolean reinversion;
    Double monto;
    Double isrRetenido;
    Double rendimiento;

    // Afuerza hay que usar este tipo de nombre sin "_" para que no falle la JPA Convention
    Boolean largoPlazo;
    LocalDateTime fechaCapturaFront;

    // // Funcional para hacer una nueva tabla cada que se inserta una nueva posicon
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinColumn(name = "posicion_id") // Esto genera una relación unidireccional
    // private List<Modificacion> modificaciones;

    // Pruebas
    // @Lob
    // @Column(columnDefinition = "TEXT") // Guarda el JSON como texto largo
    // private String modificaciones; // JSON de modificaciones

     
}
