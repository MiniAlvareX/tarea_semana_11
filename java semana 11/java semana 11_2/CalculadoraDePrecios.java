import java.util.List;

public class CalculadoraDePrecios {

    private EstrategiaDescuento estrategia;

    public CalculadoraDePrecios(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public double calcularTotal(List<Producto> productos) {

        double subtotal = 0;

        for (Producto p : productos) {
            subtotal += p.getPrecio();
        }

        double descuento = estrategia.calcularDescuento(productos);

        return subtotal - descuento;
    }

    public double calcularDescuento(List<Producto> productos) {
        return estrategia.calcularDescuento(productos);
    }
}
