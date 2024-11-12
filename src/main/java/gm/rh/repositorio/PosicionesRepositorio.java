package gm.rh.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
// importo el modelo
import gm.rh.modelo.Posicion;

public interface PosicionesRepositorio extends JpaRepository <Posicion, Integer>{


}
