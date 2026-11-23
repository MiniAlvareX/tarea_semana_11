import java.util.ArrayList;

public class SistemaNotificaciones {

    private ArrayList<Usuario> usuarios;

    public SistemaNotificaciones() {
        usuarios = new ArrayList<>();
    }

    public void suscribir(Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
            System.out.println(usuario.getNombre() 
                    + " se ha suscrito.");
        }
    }

    public void desuscribir(Usuario usuario) {
        if (usuarios.remove(usuario)) {
            System.out.println(usuario.getNombre() 
                    + " se ha desuscrito.");
        }
    }

    public void enviarNotificacion(Notificacion notificacion) {
        System.out.println("\nENVIANDO NOTIFICACION...");
        
        for (Usuario usuario : usuarios) {
            usuario.recibirNotificacion(notificacion);
        }
    }
}
