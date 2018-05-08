import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RLE {
    private static final Integer MAX_NUMBER_REPEAT = 2;

    public static void compress(InputStream is, OutputStream os) throws Exception {
        // Current, s'empra per assignar el número actual.
        // Last, conté el número anterior.
        // Contador, lo utilizamos para contar las veces que se repite un número.
        int current = 0, last = -1, count = 0, countWhile = 0;
        // Dins result, es va enmagatzemant la sortida de bytes.
        StringBuilder result = new StringBuilder();
        boolean sameNumber = false;
        boolean nextCoincidence = false;

        // -------------------------------------------------------------- //
        while ((current = is.read()) != -1) {

            // Guardamos el valor anterior, el primer valor será -1.
            // Comparamos el último valor con el actual.
            if (countWhile == 0) {
                last = current;
            }

            if (last == current) {
                count++;
                if (count == 1) {
                    for (int i = 0; i < result.length(); i++) {
                        String aux = String.valueOf(last);
                        if (Integer.parseInt(String.valueOf(result.charAt(i))) == last) {
                            result.append(last);
                            result.append(0);
                            count = 0;
                            break;
                        }
                    }
                }
                if (count > 1) {
                    sameNumber = true;
                }
            } else if (last != current) {
                if (countWhile == 1) {
                    result.append(last);
                } else if (count > 1) {
                    for (int i = 0; i < MAX_NUMBER_REPEAT; i++) {
                        result.append(last);
                    }
                    if (count > 2) {
                        count -= MAX_NUMBER_REPEAT;
                    } else {
                        result.append(0);
                    }
                }
                count = 0;
                sameNumber = false;
                result.append(current);
            } else {
                for (int i = 0; i < MAX_NUMBER_REPEAT; i++) {
                    result.append(last);
                }
                result.append(count - last * MAX_NUMBER_REPEAT);
            }
            last = current;
            countWhile++;
        }
        // -------------------------------------------------------------- //
        if (current == -1 && sameNumber) {
            for (int i = 0; i < MAX_NUMBER_REPEAT; i++) {
                result.append(last);
            }
            count -= MAX_NUMBER_REPEAT;
            result.append(count);
        }
        // -------------------------------------------------------------- //
        byte[] data = new byte[result.length()];
        // -------------------------------------------------------------- //
        for (int i = 0; i < result.length(); i++) {
            data[i] = Byte.valueOf(String.valueOf(result.charAt(i)));
        }
        // -------------------------------------------------------------- //
        os.write(data);
        os.flush();
        os.close();
        System.out.println(Arrays.toString(data));
    }

    public static void decompress(InputStream is, OutputStream os) throws Exception {

    }
}
