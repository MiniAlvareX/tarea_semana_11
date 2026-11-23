import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Producto> productos = new ArrayList<>();

        CalculadoraDePrecios calculadora =
                new CalculadoraDePrecios(new SinDescuento());

        int opcion;

        do {
            System.out.println("\n SISTEMA DE DESCUENTOS ");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Sin descuento");
            System.out.println("4. Descuento fijo (10%)");
            System.out.println("5. Descuento porcentual (30% en 2 productos iguales)");
            System.out.println("6. Descuento acumulado (50% en el producto mas barato)");
            System.out.println("7. Calcular precio final");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = sc.nextLine();

                    System.out.print("Precio del producto: ");
                    double precio = sc.nextDouble();

                    if (precio <= 0) {
                        System.out.println("El precio debe ser mayor que cero.");
                        break;
                    }

                    productos.add(new Producto(nombre, precio));

                    System.out.println("Producto agregado correctamente.");
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE PRODUCTOS ---");

                    if (productos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        for (Producto p : productos) {
                            System.out.println(
                                p.getNombre() + " - S/ " + p.getPrecio()
                            );
                        }
                    }
                    break;

                case 3:
                    calculadora.setEstrategia(new SinDescuento());
                    System.out.println("Estrategia: Sin descuento.");
                    break;

                case 4:
                    calculadora.setEstrategia(new DescuentoFijo());
                    System.out.println("Estrategia: Descuento del 10%.");
                    break;

                case 5:
                    calculadora.setEstrategia(new DescuentoPorcentual());
                    System.out.println("Estrategia: 30% en dos productos iguales.");
                    break;

                case 6:
                    calculadora.setEstrategia(
                        new DescuentoPorcentualAcumulado()
                    );
                    System.out.println("Estrategia: 50% en el producto mas barato.");
                    break;

                case 7:
                    if (productos.isEmpty()) {
                        System.out.println("Primero debe agregar productos.");
                        break;
                    }

                    double subtotal = 0;

                    for (Producto p : productos) {
                        subtotal += p.getPrecio();
                    }

                    double descuento =
                            calculadora.calcularDescuento(productos);

                    double total =
                            calculadora.calcularTotal(productos);

                    System.out.println("\n RESUMEN DE COMPRA ");
                    System.out.printf("Subtotal: S/ %.2f%n", subtotal);
                    System.out.printf("Descuento: S/ %.2f%n", descuento);
                    System.out.printf("Total a pagar: S/ %.2f%n", total);
                    break;

                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 8);

        sc.close();
    }
}
