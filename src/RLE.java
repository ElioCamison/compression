import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RLE {

    private static final Integer MAX_NUMBER_SHOW = 2;

    public static void compress(InputStream is, OutputStream os) throws Exception {
        int current = 0;
        int last = -1;
        StringBuilder sb = new StringBuilder();

        // Contador, lo utilizamos para contar las veces que se repite un número.
        int count = 0;

        // while
        // -------------------------------------------------------------- //
        while ((current = is.read()) != -1) {
            // Guardamos el valor anterior, el primer valor será -1.
            last = current;
            // Comparamos el último valor con el actual.
            if (last == current) {
                count++;
            } else {
                for (int i = 0; i < MAX_NUMBER_SHOW; i++) {
                    sb.append(last);
                }
                sb.append(count - last * 2);
            }

        }
        // -------------------------------------------------------------- //
        if (current == -1) {
            for (int i = 0; i < MAX_NUMBER_SHOW; i++) {
                sb.append(last);
            }
            count -= MAX_NUMBER_SHOW;
            sb.append(count);
        }
        // -------------------------------------------------------------- //
        byte[] data = new byte[sb.length()];
        // -------------------------------------------------------------- //
        for (int i = 0; i < sb.length(); i++) {
            data[i] = Byte.valueOf(String.valueOf(sb.charAt(i)));
        }
        // -------------------------------------------------------------- //

        os.write(data);
        System.out.println(Arrays.toString(data));
    }

    public static void decompress(InputStream is, OutputStream os) throws Exception {

    }
}
