package gm.rh.controlador;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import gm.rh.modelo.Modificacion;
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
    // public ResponseEntity <Map<String, Boolean>> agregarActualizarPosicion(@RequestBody Posicion posicionToSave){
            
        logger.info("La posicion a agregar/actulizar es: " + posicionToSave);
            
        // posicionServicio.guardarPosicion(posicionToSave);
        // // Json postman {"emilinado", "true"}
        // Map<String, Boolean> respuesta = new HashMap<>();
        // respuesta.put("La posicion agregada/actualizada fue: " + posicionToSave, Boolean.TRUE);        
         
        // return ResponseEntity.ok(respuesta);

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
        // instrumento, fechaFinal, monto, plazo
        posicion.setInstrumento(posicionRecibida.getInstrumento());
        posicion.setFechaFinal(posicionRecibida.getFechaFinal());
        posicion.setMonto(posicionRecibida.getMonto());
        posicion.setPlazo(posicionRecibida.getPlazo());
        // posicion.setModificaciones(posicionRecibida.getModificaciones());

        posicionServicio.guardarPosicion(posicion);

        //  aqui el objeto posicion ya esta actualizado, nosotros ahora nos redirigimos al listado
        return ResponseEntity.ok(posicion);

    }

    // Borrar una Posicion
    @DeleteMapping("/posiciones/{id_to_delete}")
    //  la respuesta sera texto y valor, recibirmos el id a eliminar
    public ResponseEntity <Map<String, Boolean>> eliminarPosicion (@PathVariable Integer id_to_delete)
    {
        // recuperarmos el objeto empleado de BD antes de eleiminarlo. veremo primero si existe.
        Posicion posicion_to_delete = posicionServicio.buscarPosicionPorId(id_to_delete);

        if (posicion_to_delete == null)
            throw new RecursoNoEncontradoExcepcion("El ID recibido no existe: " + id_to_delete);
        
        posicionServicio.eliminarPosicion(posicion_to_delete);
        
        // Json {"emilinado", "true"}
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("La posicion eliminada fue: " + posicion_to_delete, Boolean.TRUE);        
        
        return ResponseEntity.ok(respuesta);

    }

    // agregar datos a "Modificaciones"
    @PutMapping("/posicionEdit/{id}/modificacion")
    public ResponseEntity<Posicion> agregarModificacion(@PathVariable Integer id, 
    @RequestBody Modificacion nuevaModificacion) {

    // Buscar la Posicion por su ID
    Posicion posicion = posicionServicio.buscarPosicionPorId(id);
    if (posicion == null) {
        throw new RecursoNoEncontradoExcepcion("El ID de Posicion recibido no existe: " + id);
    }

    // Agregar la nueva Modificacion a la lista
    if (posicion.getModificaciones() == null) {
        posicion.setModificaciones(new ArrayList<>());
    }
    posicion.getModificaciones().add(nuevaModificacion);

    // Guardar la Posicion actualizada
    Posicion posicionActualizada = posicionServicio.guardarPosicion(posicion);

    return ResponseEntity.ok(posicionActualizada);
}



}
