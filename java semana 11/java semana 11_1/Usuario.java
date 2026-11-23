public class Usuario {

    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public void recibirNotificacion(Notificacion notificacion) {
        System.out.println("Usuario: " + nombre);
        System.out.println("Notificacion recibida: " 
                + notificacion.getMensaje());
        System.out.println(" ");
    }

    public String getNombre() {
        return nombre;
    }
}
