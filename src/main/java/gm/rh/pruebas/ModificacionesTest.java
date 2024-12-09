package gm.rh.pruebas;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import gm.rh.modelo.Posicion;

public class ModificacionesTest {

//     Posicion posicion = new Posicion();
//     posicion.setInstrumento("bono");
//     posicion.setPlazo("5 años");

// // Simulación de modificaciones
//     List<Map<String, Object>> modificaciones = List.of(
//     Map.of("creadoPor", "usuario_hermetico", "fechaModificacion", "2024-12-06T21:57:33"),
//     Map.of("creadoPor", "usuario_2", "fechaModificacion", "2024-10-18T21:50:33")
//     );

// // Convertir lista de modificaciones a JSON y guardarla
// try {
//     String jsonModificaciones = posicionService.convertirListaAJson(modificaciones);
//     posicion.setModificaciones(jsonModificaciones);
// } catch (JsonProcessingException e) {
//     e.printStackTrace();
// }

// // Guardar en base de datos
// posicionRepository.save(posicion);

// // Recuperar y convertir JSON a lista
// Posicion posicionGuardada = posicionRepository.findById(1).orElseThrow();
// try {
//     List<Map<String, Object>> modificacionesGuardadas =
//         posicionService.convertirJsonALista(posicionGuardada.getModificaciones());
//     modificacionesGuardadas.forEach(System.out::println);
// } catch (JsonProcessingException e) {
//     e.printStackTrace();
// }
}
