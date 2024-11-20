package gm.rh.controlador;



import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gm.rh.excepcion.RecursoNoEncontradoExcepcion;
import gm.rh.modelo.Empleado;
import gm.rh.modelo.Posicion;
import gm.rh.servicios.InterPosicionServicio;

@RestController
@RequestMapping("po-app")
@CrossOrigin("http://localhost:3000")
public class PosicionControlador {

    private static final Logger logger = LoggerFactory.getLogger(PosicionControlador.class);

    @Autowired
    private InterPosicionServicio posicionServicio;

    @GetMapping("/listaall")
    public List<Posicion> obtenerPosiciones() {
        var empleados = posicionServicio.listarPosiciones();
        // mandamos a imprimir el show
        // enla consola se ve asi, Hibernate: select e1_0.id_empleado,e1_0.departamento,e1_0.nombre,e1_0.sueldo from empleado e1_0
        empleados.forEach((empleado -> logger.info(empleado.toString())));        // expresion lambda para procesar cada entrada

        return empleados;

    }

    // mi error era tenr path varible en lugar de  request param

    @GetMapping("/largoplazo")
    public ResponseEntity<List<Posicion>> listarFinDePosicionesLargoPlazo(@RequestParam("fechaInicial") LocalDate fechaInicial, @RequestParam ("fechaFinal") LocalDate fechaFinal){

        List<Posicion> posiciones = posicionServicio.buscarPosicionesLargoPlazoEntreFechas(fechaInicial, fechaFinal);
        posiciones.forEach((empleado -> logger.info(empleado.toString()))); 

        return ResponseEntity.ok(posiciones);
    }

    @PostMapping("/posiciones")
    public Posicion agregarActualizarPosicion(@RequestBody Posicion posicionToSave){
            
        logger.info("La posicion a agregar/actulizar es: " + posicionToSave);
            
        return posicionServicio.guardarPosicion(posicionToSave);
    }


    // burcar posicion por ID lec 192, va a ir envuelta la respues en Entity
    // el pathvariable significa que viene de nuestra ruta URL la variable
    // BUSCAR POSICION
    @GetMapping("/posicionId/{id}")
    public ResponseEntity<Posicion> obtenerPosicionPorID (@PathVariable Integer id){

        Posicion posicionId = posicionServicio.buscarPosicionPorId(id);                             
        if (posicionId == null){
            //
            throw new RecursoNoEncontradoExcepcion("No se encontró la posicion con ID= " + id);
        
        }
        // se responde el empleado, dentro del respnse entity.
        return ResponseEntity.ok(posicionId);
    }

    //  este es el PUT para Editar, al parecer aun no se usa el put mas adelante
    @PutMapping("/posicionEdit/{id}")
    public ResponseEntity<Posicion> actualizarPosicion(@PathVariable Integer id, 
    @RequestBody Posicion posicionRecibida){

        // buscaremos nuestro objeto Posicion y para asegurarnos que exite, primero lo buscamos por id.
        Posicion posicion = posicionServicio.buscarPosicionPorId(id);

        // esta es una validacino antes de guardado, para no hacer el guardado directo.
        if (posicion == null)
            throw new RecursoNoEncontradoExcepcion("El ID de Posicion recibido no existe: " + id);
        // instrumento, fechaFinal, monto
        posicion.setInstrumento(posicionRecibida.getInstrumento());
        posicion.setFechaFinal(posicionRecibida.getFechaFinal());
        posicion.setMonto(posicionRecibida.getMonto());

        posicionServicio.guardarPosicion(posicion);

        //  aqui el objeto posicion ya esta actualizado, nosotros ahora nos redirigimos al listado
        return ResponseEntity.ok(posicion);

    }


}
