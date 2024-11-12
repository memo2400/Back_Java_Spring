package gm.rh.modelo;

import jakarta.persistence.Entity;      // con ello se crea la tabla bd en autmatico
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;                     // para crear los get y setters automarticos.
import lombok.NoArgsConstructor;        // consturctor sin argumentos
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Posiciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // se genera en automatico la llave primaria.
    
    Integer idPosicion ;
    String instrumento;
    String plazo;
    String fecha_registro;
    String fecha_subasta;

    String fecha_final;
    Boolean reinversion;
    Double monto;
    Double isr_retenido;
    Double rendimiento;

    Boolean largo_plazo;

    // Instrumento 	Plazo 	Registro 	Dia de Subasta 	Fecha Final 	Reinversion 	Monto 	ISR Ret 	Rendimiento %
}
