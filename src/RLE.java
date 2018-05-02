import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class RLE {

    public static void compress(InputStream is, OutputStream os) throws Exception {
        int current = 0;
        int last = -1;

        // Contador, lo utilizamos para contar las veces que se repite un número.
        int count = 0;


        while ((current = is.read()) != -1) {
            // Guardamos el valor anterior, el primer valor será -1.
            last = current;
            // Comparamos el último valor con el actual.
            if(last == current) {
                count++;
            } else {
                os.write(last);
                os.write(last);
                os.write(current);

                
            }

        }

    }

    public static void decompress(InputStream is, OutputStream os) throws Exception {

    }
}
