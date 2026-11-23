public class Main {

    public static void main(String[] args) {

        SistemaNotificaciones sistema = new SistemaNotificaciones();

        Usuario usuario1 = new Usuario("Jhon");
        Usuario usuario2 = new Usuario("Hector");
        Usuario usuario3 = new Usuario("Justin");

        sistema.suscribir(usuario1);
        sistema.suscribir(usuario2);
        sistema.suscribir(usuario3);

        Notificacion promo = new Notificacion(
                "Descuento del 30% en todos los productos",
                "PROMOCION"
        );

        sistema.enviarNotificacion(promo);

        System.out.println("\n CAMBIOS DE SUSCRIPCION ");
        sistema.desuscribir(usuario3);

        Notificacion actualizacion = new Notificacion(
                "Nuevo catalogo de productos disponible",
                "ACTUALIZACION"
        );

        sistema.enviarNotificacion(actualizacion);

        Usuario usuario4 = new Usuario("Jose");
        sistema.suscribir(usuario4);

        Notificacion nuevaPromo = new Notificacion(
                "Oferta especial por tiempo limitado",
                "PROMOCION"
        );

        sistema.enviarNotificacion(nuevaPromo);
    }
}