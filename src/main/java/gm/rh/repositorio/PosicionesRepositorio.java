package gm.rh.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// importo el modelo
import gm.rh.modelo.Posicion;

public interface PosicionesRepositorio extends JpaRepository <Posicion, Integer>{

    List<Posicion> encontrarPorIdEntre(Integer startId, Integer endId);

    // List<Posicion> encontrarPorLargoPlazoTrue();

    // Método utilizando convención de Spring Data JPA de chat GPT.
    List<Posicion> findByLargoPlazoTrue();

    
}
