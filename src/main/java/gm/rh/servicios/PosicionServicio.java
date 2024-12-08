package gm.rh.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        // throw new UnsupportedOperationException("Unimplemented method 'guardarPosicion'");
        return posicionRepositorio.save(posicion);
    }

    @Override
    public void eliminarPosicion(Posicion posicion_to_delete) {
        
        posicionRepositorio.delete(posicion_to_delete);
    }

    @Override
    public List<Posicion> buscarPosicionesLargoPlazoEntreFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        // throw new UnsupportedOperationException("Unimplemented method 'buscarPosicionesLargoPlazoEntreFechas'");
        List<Posicion> posicion = posicionRepositorio.findByLargoPlazoTrueAndFechaFinalBetween(fechaInicial, fechaFinal);
        return posicion;
    }

    @Override
    public List<Posicion> buscarPosicionesEntreFechaFinal(LocalDate fechaInicial, LocalDate fechaFinal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPosicionesEntreFechaFinal'");
    }

    // manejo de modificaciones Json
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertirListaAJson(List<Map<String, Object>> modificaciones) throws JsonProcessingException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'convertirListaAJson'");
        return objectMapper.writeValueAsString(modificaciones);
    }

    @Override
    public List<Map<String, Object>> convertirJsonALista(String json) throws JsonProcessingException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'convertirJsonALista'");
        return objectMapper.readValue(json, List.class);
    }

    // public String convertirListaAJson(List<Map<String, Object>> modificaciones) throws JsonProcessingException {
    //     return objectMapper.writeValueAsString(modificaciones);
    // }

    // public List<Map<String, Object>> convertirJsonALista(String json) throws JsonProcessingException {
    //     return objectMapper.readValue(json, List.class);
    // }


}
