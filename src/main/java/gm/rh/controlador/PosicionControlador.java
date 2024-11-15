package gm.rh.controlador;



import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gm.rh.modelo.Posicion;
import gm.rh.servicios.InterPosicionServicio;

@RestController
@RequestMapping("Po-app")
@CrossOrigin("http://localhost:3000")
public class PosicionControlador {

    private static final Logger logger = LoggerFactory.getLogger(PosicionControlador.class);

    @Autowired
    private InterPosicionServicio posicionServicio;

    @GetMapping("/empleados/largoplazo")
    public ResponseEntity<List<Posicion>> listarPosicionesLargoPlazo(){

        List<Posicion> posiciones = posicionServicio.buscarPosicionesLargoPlazoEntreFechas(LocalDate fechaInicial, localDate fechaFinal);
        
        return ResponseEntity.ok(posiciones);
    }


}
