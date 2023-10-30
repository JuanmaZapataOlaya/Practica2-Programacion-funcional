import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  private static List<Estudiante> estudiantes;

  public static void main(String[] args) throws IOException {

    cargarArchivo();

    contarEstudiantesPorCarrera();

    contarMujeresPorCarrera();

    contarHombresPorCarrera();

    estudianteConPuntajeMasAltoPorCarrera();

    estudianteConPuntajeMasAltoEnGeneral();

    promedioPuntajesPorCarrera();

  }

  private static void cargarArchivo() throws IOException {
    
    Pattern pattern = Pattern.compile(";");

    try (Stream<String> lines = Files.lines(Path.of("puntajes_estudiantes.csv"))) {

      estudiantes = lines
        .skip(1)
        .map(linea -> {
          
          String[] partes = pattern.split(linea);
          
          Estudiante estudiante = new Estudiante();
          estudiante.setId(Integer.parseInt(partes[0]));
          estudiante.setNombre(partes[1]);
          estudiante.setApellido(partes[2]);
          estudiante.setGenero(partes[3]);
          estudiante.setCarrera(partes[4]);
          
          try {
            estudiante.setPuntajeMatematicas(Integer.parseInt(partes[5].trim()));
          } catch (NumberFormatException e) {
            System.out.println("Error parsing puntaje a int para estudiante: " + linea);
            estudiante.setPuntajeMatematicas(0);
          }
          
          return estudiante;
          
        })
        .collect(Collectors.toList());
        
    } catch (IOException e) {
      System.out.println("Error leyendo archivo CSV: " + e.getMessage());
    }

  }

  private static void contarEstudiantesPorCarrera() {
    
    Map<String, Long> conteoPorCarrera = estudiantes.stream()
      .collect(Collectors.groupingBy(Estudiante::getCarrera, Collectors.counting()));

    System.out.println("Estudiantes por carrera:");
    conteoPorCarrera.forEach((carrera, conteo) -> {
      System.out.println(carrera + ": " + conteo);
    });

  }

  private static void contarMujeresPorCarrera() {

    Map<String, Long> contadorMujeresPorCarrera = estudiantes.stream()
      .filter(e -> e.getGenero().equals("F"))
      .collect(Collectors.groupingBy(Estudiante::getCarrera, Collectors.counting()));

    System.out.println("\nMujeres por carrera:");
    contadorMujeresPorCarrera.forEach((carrera, contador) -> {
      System.out.println(carrera + ": " + contador);
    });

  }

  private static void contarHombresPorCarrera() {

    Map<String, Long> contadorHombresPorCarrera = estudiantes.stream()
      .filter(e -> e.getGenero().equals("M"))
      .collect(Collectors.groupingBy(Estudiante::getCarrera, Collectors.counting()));

    System.out.println("\nHombres por carrera:");
    contadorHombresPorCarrera.forEach((carrera, contador) -> {
      System.out.println(carrera + ": " + contador);
    });

  }

  private static void estudianteConPuntajeMasAltoPorCarrera() {

    Map<String, Optional<Estudiante>> mayorPuntajePorCarrera = estudiantes.stream()
      .collect(Collectors.groupingBy(Estudiante::getCarrera, 
        Collectors.maxBy(Comparator.comparingInt(Estudiante::getPuntajeMatematicas))));

    System.out.println("\nEstudiante con puntaje más alto por carrera:");
    mayorPuntajePorCarrera.forEach((carrera, estudiante) -> {
      System.out.println(carrera + ": " + estudiante.get().getNombre() + " " + estudiante.get().getApellido());
    });

  }

  private static void estudianteConPuntajeMasAltoEnGeneral() {

    Estudiante mejorEstudiante = estudiantes.stream()
      .max(Comparator.comparingInt(Estudiante::getPuntajeMatematicas))
      .get();

    System.out.println("\nEstudiante con puntaje más alto en general:");
    System.out.println(mejorEstudiante.getNombre() + " " + mejorEstudiante.getApellido());

  }

  private static void promedioPuntajesPorCarrera() {

    Map<String, Double> promedioPorCarrera = estudiantes.stream()
        .collect(Collectors.groupingBy(Estudiante::getCarrera,
          Collectors.averagingInt(Estudiante::getPuntajeMatematicas)));
    
    System.out.println("\nPromedio de puntajes por carrera:");
    promedioPorCarrera.forEach((carrera, promedio) -> {
      System.out.println(carrera + ": " + promedio);
    });

  }

}