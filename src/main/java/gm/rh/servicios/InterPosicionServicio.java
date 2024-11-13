package gm.rh.servicios;

import java.util.List;

import gm.rh.modelo.Posicion;
public interface InterPosicionServicio {

        // La interface solo agrega la firma del metodo pero no la implementacion
    public List<Posicion> listarPosiciones();

    public Posicion buscarPosicionPorId(Integer idPosicion);

    // si esta vacio se inserta , si esta lleno se hace un update
    public Posicion guardarPosicion(Posicion posicion);

    public void eliminarPosicion(Posicion posicion);

    public Posicion buscarPosicionLargoPlazo(Boolean largo_plazo);

}
