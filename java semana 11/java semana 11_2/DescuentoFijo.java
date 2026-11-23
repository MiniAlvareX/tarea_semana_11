import java.util.List;

public class DescuentoFijo implements EstrategiaDescuento {

    @Override
    public double calcularDescuento(List<Producto> productos) {
        double total = 0;

        for (Producto p : productos) {
            total += p.getPrecio();
        }

        return total * 0.10;
    }
}
