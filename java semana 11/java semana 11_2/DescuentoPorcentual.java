import java.util.List;

public class DescuentoPorcentual implements EstrategiaDescuento {

    @Override
    public double calcularDescuento(List<Producto> productos) {
        double descuento = 0;

        for (int i = 0; i < productos.size(); i++) {
            for (int j = i + 1; j < productos.size(); j++) {

                if (productos.get(i).getNombre().equalsIgnoreCase(
                    productos.get(j).getNombre())) {

                    descuento += (productos.get(i).getPrecio()
                                + productos.get(j).getPrecio()) * 0.30;

                    return descuento;
                }
            }
        }

        return 0;
    }
}
