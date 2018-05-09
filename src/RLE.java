import com.sun.org.apache.regexp.internal.RE;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class RLE {
    private static final Integer MAX_NUMBER_REPEAT = 2;
    private static final Integer MAX_NUMBER_BYTE = 255;
    public static StringBuilder RESULT = new StringBuilder();

    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //

    public static void compress(InputStream is, OutputStream os) throws Exception {

        int current = is.read(), last = 0, count = 0;

        if(current != -1){ last = current; count = 1; }
        // -------------------------------------------------------------- //
        while (true) {
            current = is.read();
            // -------------------------------------------------------------- //
            if ( last == current ) { count++; continue; }
            // -------------------------------------------------------------- //
            else {
                // -------------------------------------------------------------- //
                if (count >= 2) { operation(last, count); }
                    else { RESULT.append(last); }
                // -------------------------------------------------------------- //
                count = 1;
                last = current;
            }
            if(current == -1) break;
        }
        os.write(insertData(RESULT));
        closedOutput(os);
        System.out.println(Arrays.toString(insertData(RESULT)));
        RESULT.setLength(0);
    }

    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //
    public static void decompress(InputStream is, OutputStream os) throws Exception {
        int current = 0;

        while ((current = is.read()) != -1) {
            System.out.println(current);
        }
    }
    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //


    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //
    private static OutputStream closedOutput (OutputStream os) throws IOException {
        os.flush();
        os.close();
        return os;
    }

    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //
    private static byte[] insertData(StringBuilder RESULT){
        byte[] data = new byte[RESULT.length()];
        // -------------------------------------------------------------- //
        for (int i = 0; i < RESULT.length(); i++) {
            data[i] = Byte.valueOf(String.valueOf(RESULT.charAt(i)));
        }
        return data;
    }


    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //
    private static StringBuilder operation(int last, int count) {
        for (int i = 0; i < MAX_NUMBER_REPEAT; i++) {
            RESULT.append(last);
        }
        RESULT.append(count - MAX_NUMBER_REPEAT);
        return RESULT;

    }
}