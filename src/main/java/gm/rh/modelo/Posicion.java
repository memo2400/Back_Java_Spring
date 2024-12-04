package gm.rh.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;      // con ello se crea la tabla bd en autmatico
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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


    // LocalDateTime fechaServer;
    // @PrePersist
    // public void prePersist() {
    //     this.fechaServer = LocalDateTime.now(); // Asignar la fecha y hora actual
    // }

     
    // Instrumento 	Plazo 	Registro 	Dia de Subasta 	Fecha Final 	Reinversion 	Monto 	ISR Ret 	Rendimiento %
}
