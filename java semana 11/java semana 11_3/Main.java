import java.util.*;

interface Observador {
    void recibirNotificacion(Notificacion notificacion);
}

class Notificacion {
    private String mensaje;

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}

class Usuario implements Observador {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void recibirNotificacion(Notificacion notificacion) {
        System.out.println("Notificacion para " + nombre + ": "
                + notificacion.getMensaje());
    }
}

class Tienda {
    private List<Observador> usuarios = new ArrayList<>();

    public void suscribir(Observador usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario suscrito correctamente.");
    }

    public void desuscribir(Observador usuario) {
        usuarios.remove(usuario);
        System.out.println("Usuario eliminado de las suscripciones.");
    }

    public void notificar(String mensaje) {
        Notificacion notificacion = new Notificacion(mensaje);

        for (Observador usuario : usuarios) {
            usuario.recibirNotificacion(notificacion);
        }
    }
}

interface Descuento {
    double calcular(double precio, int cantidad);
}

class SinDescuento implements Descuento {
    @Override
    public double calcular(double precio, int cantidad) {
        return precio * cantidad;
    }
}

class DescuentoFijo implements Descuento {
    @Override
    public double calcular(double precio, int cantidad) {
        return precio * cantidad * 0.90;
    }
}

class DescuentoPorcentual implements Descuento {
    @Override
    public double calcular(double precio, int cantidad) {
        double subtotal = precio * cantidad;

        if (cantidad >= 2) {
            return subtotal * 0.70;
        }

        return subtotal;
    }
}

class CalculadoraDescuento {
    private Descuento estrategia;

    public void setEstrategia(Descuento estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(double precio, int cantidad) {
        return estrategia.calcular(precio, cantidad);
    }
}

class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}

class Carrito {
    private Map<String, Integer> productos = new LinkedHashMap<>();

    public void agregar(Producto producto) {
        String nombre = producto.getNombre();

        productos.put(nombre,
                productos.getOrDefault(nombre, 0) + 1);

        System.out.println("Producto agregado: " + nombre);
    }

    public void quitar(Producto producto) {
        String nombre = producto.getNombre();

        if (productos.containsKey(nombre)) {
            int cantidad = productos.get(nombre);

            if (cantidad > 1) {
                productos.put(nombre, cantidad - 1);
            } else {
                productos.remove(nombre);
            }

            System.out.println("Producto eliminado: " + nombre);
        } else {
            System.out.println("El producto no esta en el carrito.");
        }
    }

    public void mostrar() {
        System.out.println("\n CARRITO DE COMPRAS ");

        if (productos.isEmpty()) {
            System.out.println("El carrito esta vacio.");
            return;
        }

        for (String nombre : productos.keySet()) {
            System.out.println(nombre + " - Cantidad: "
                    + productos.get(nombre));
        }
    }
}

interface Comando {
    void ejecutar();
    void deshacer();
}

class AgregarProducto implements Comando {
    private Carrito carrito;
    private Producto producto;

    public AgregarProducto(Carrito carrito, Producto producto) {
        this.carrito = carrito;
        this.producto = producto;
    }

    @Override
    public void ejecutar() {
        carrito.agregar(producto);
    }

    @Override
    public void deshacer() {
        carrito.quitar(producto);
    }
}

class QuitarProducto implements Comando {
    private Carrito carrito;
    private Producto producto;

    public QuitarProducto(Carrito carrito, Producto producto) {
        this.carrito = carrito;
        this.producto = producto;
    }

    @Override
    public void ejecutar() {
        carrito.quitar(producto);
    }

    @Override
    public void deshacer() {
        carrito.agregar(producto);
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Tienda tienda = new Tienda();
        Carrito carrito = new Carrito();

        CalculadoraDescuento calculadora = new CalculadoraDescuento();

        Usuario usuario1 = new Usuario("Jhon");
        Usuario usuario2 = new Usuario("Hector");

        Producto producto1 = new Producto("Laptop", 2500);
        Producto producto2 = new Producto("Mouse", 50);
        Producto producto3 = new Producto("Teclado", 100);

        Stack<Comando> historial = new Stack<>();

        int opcion;

        do {
            System.out.println("\n SISTEMA DE VENTAS ");
            System.out.println("1. Suscribir usuario");
            System.out.println("2. Desuscribir usuario");
            System.out.println("3. Enviar promocion");
            System.out.println("4. Calcular descuento");
            System.out.println("5. Agregar producto al carrito");
            System.out.println("6. Quitar producto del carrito");
            System.out.println("7. Deshacer ultima operacion");
            System.out.println("8. Mostrar carrito");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("1. Carlos");
                    System.out.println("2. Maria");
                    System.out.print("Seleccione usuario: ");

                    int suscribir = sc.nextInt();

                    if (suscribir == 1) {
                        tienda.suscribir(usuario1);
                    } else if (suscribir == 2) {
                        tienda.suscribir(usuario2);
                    } else {
                        System.out.println("Usuario no valido.");
                    }
                    break;

                case 2:
                    System.out.println("1. Carlos");
                    System.out.println("2. Maria");
                    System.out.print("Seleccione usuario: ");

                    int eliminar = sc.nextInt();

                    if (eliminar == 1) {
                        tienda.desuscribir(usuario1);
                    } else if (eliminar == 2) {
                        tienda.desuscribir(usuario2);
                    } else {
                        System.out.println("Usuario no valido.");
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.print("Ingrese la promocion: ");
                    String mensaje = sc.nextLine();

                    tienda.notificar(mensaje);
                    break;

                case 4:
                    System.out.println("\n ESTRATEGIAS DE DESCUENTO ");
                    System.out.println("1. Sin descuento");
                    System.out.println("2. Descuento fijo del 10%");
                    System.out.println("3. Descuento del 30% por 2 o mas productos iguales");
                    System.out.print("Seleccione estrategia: ");

                    int tipo = sc.nextInt();

                    System.out.print("Ingrese precio del producto: ");
                    double precio = sc.nextDouble();

                    System.out.print("Ingrese cantidad: ");
                    int cantidad = sc.nextInt();

                    if (precio < 0 || cantidad <= 0) {
                        System.out.println("Datos no validos.");
                        break;
                    }

                    switch (tipo) {
                        case 1:
                            calculadora.setEstrategia(new SinDescuento());
                            break;
                        case 2:
                            calculadora.setEstrategia(new DescuentoFijo());
                            break;
                        case 3:
                            calculadora.setEstrategia(new DescuentoPorcentual());
                            break;
                        default:
                            System.out.println("Estrategia no valida.");
                            continue;
                    }

                    double total = calculadora.calcular(precio, cantidad);

                    System.out.printf("Precio original: S/ %.2f%n",
                            precio * cantidad);
                    System.out.printf("Precio final: S/ %.2f%n", total);
                    System.out.printf("Descuento aplicado: S/ %.2f%n",
                            (precio * cantidad) - total);
                    break;

                case 5:
                    System.out.println("\n--- PRODUCTOS ---");
                    System.out.println("1. Laptop - S/ 2500");
                    System.out.println("2. Mouse - S/ 50");
                    System.out.println("3. Teclado - S/ 100");
                    System.out.print("Seleccione producto: ");

                    int agregar = sc.nextInt();

                    Producto seleccionado = null;

                    if (agregar == 1) seleccionado = producto1;
                    if (agregar == 2) seleccionado = producto2;
                    if (agregar == 3) seleccionado = producto3;

                    if (seleccionado != null) {
                        Comando comando =
                                new AgregarProducto(carrito, seleccionado);

                        comando.ejecutar();
                        historial.push(comando);
                    } else {
                        System.out.println("Producto no valido.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- PRODUCTOS ---");
                    System.out.println("1. Laptop");
                    System.out.println("2. Mouse");
                    System.out.println("3. Teclado");
                    System.out.print("Seleccione producto: ");

                    int quitar = sc.nextInt();

                    Producto eliminado = null;

                    if (quitar == 1) eliminado = producto1;
                    if (quitar == 2) eliminado = producto2;
                    if (quitar == 3) eliminado = producto3;

                    if (eliminado != null) {
                        Comando comando =
                                new QuitarProducto(carrito, eliminado);

                        comando.ejecutar();

                        historial.push(comando);
                    } else {
                        System.out.println("Producto no valido.");
                    }
                    break;

                case 7:
                    if (!historial.isEmpty()) {
                        Comando ultimo = historial.pop();
                        ultimo.deshacer();

                        System.out.println("Operacion deshecha.");
                    } else {
                        System.out.println("No hay operaciones para deshacer.");
                    }
                    break;

                case 8:
                    carrito.mostrar();
                    break;

                case 9:
                    System.out.println("Gracias por utilizar el sistema.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 9);

        sc.close();
    }
}
