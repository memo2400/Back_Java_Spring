package gm.rh.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gm.rh.modelo.Posicion;
import gm.rh.repositorio.PosicionesRepositorio;

@Service
public class PosicionServicio implements InterPosicionServicio {

    // Con esto comunicaremos a Repositorio de Servicio a la Base de datos
    @Autowired
    private PosicionesRepositorio posicionRepositorio;

    @Override
    public List<Posicion> listarPosiciones() {
        // TODO Auto-generated method stub
        return posicionRepositorio.findAll();
    }

    @Override
    public Posicion buscarPosicionPorId(Integer idPosicion) {
        // TODO Auto-generated method stub
        
        Posicion posicion = posicionRepositorio.findById(idPosicion).orElse(null);
        return posicion;
    }

    @Override
    public Posicion guardarPosicion(Posicion posicion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarPosicion'");
    }

    @Override
    public void eliminarPosicion(Posicion posicion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPosicion'");
    }


    @Override
    public List<Posicion> obtenerPosicionesLargoPlazo() {
        return posicionRepositorio.findByLargoPlazoTrue();

    }

}
