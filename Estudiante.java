public class Estudiante {

  private int id;
  private String nombre;
  private String apellido;
  private String genero;
  private String carrera;
  private int puntajeMatematicas;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getCarrera() {
    return carrera;
  }

  public void setCarrera(String carrera) {
    this.carrera = carrera;
  }

  public int getPuntajeMatematicas() {
    return puntajeMatematicas; 
  }

  public void setPuntajeMatematicas(int puntajeMatematicas) {
    this.puntajeMatematicas = puntajeMatematicas;
  }

  @Override
  public String toString() {
    return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", genero=" + genero + ", carrera="
        + carrera + ", puntajeMatematicas=" + puntajeMatematicas + "]";
  }

}