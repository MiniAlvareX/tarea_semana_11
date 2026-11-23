import java.util.List;

public class DescuentoPorcentualAcumulado
        implements EstrategiaDescuento {

    @Override
    public double calcularDescuento(List<Producto> productos) {

        if (productos.size() < 3) {
            return 0;
        }

        double menorPrecio = productos.get(0).getPrecio();

        for (Producto p : productos) {
            if (p.getPrecio() < menorPrecio) {
                menorPrecio = p.getPrecio();
            }
        }

        return menorPrecio * 0.50;
    }
}
