package gm.rh.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import gm.rh.modelo.Posicion;
public interface InterPosicionServicio {

        // La interface solo agrega la firma del metodo pero no la implementacion
    public List<Posicion> listarPosiciones();

    public Posicion buscarPosicionPorId(Integer idPosicion);

    // si esta vacio se inserta , si esta lleno se hace un update
    public Posicion guardarPosicion(Posicion posicion);

    public void eliminarPosicion(Posicion posicion);

    public List<Posicion> buscarPosicionesLargoPlazoEntreFechas(LocalDate fechaInicial, LocalDate fechaFinal);

    public List<Posicion> buscarPosicionesEntreFechaFinal(LocalDate fechaInicial, LocalDate fechaFinal);

    public String convertirListaAJson(List<Map<String, Object>> modificaciones) throws JsonProcessingException;

    public List<Map<String, Object>> convertirJsonALista(String json) throws JsonProcessingException;

}
