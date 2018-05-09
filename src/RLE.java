import com.sun.org.apache.regexp.internal.RE;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RLE {
    private static final Integer MAX_NUMBER_REPEAT = 2;
    public static StringBuilder RESULT = new StringBuilder();

    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //
    public static void compress(InputStream is, OutputStream os) throws Exception {

        int current = 0, last = -1, count = 0;
        // -------------------------------------------------------------- //
        while ( (current = is.read()) != -1 ) {
            // -------------------------------------------------------------- //
            if (    last == -1   ) { last = current; }
            if ( last == current ) { count++;        }
            // -------------------------------------------------------------- //
            if (last != current) {
                // -------------------------------------------------------------- //
                if (count  > 2) { operation(last, count); }
                if (count == 2) { operation(last, count); }
                if (count == 1) { RESULT.append(last);    }
                // -------------------------------------------------------------- //
                count = 0;
                //RESULT.append(current);
                count++;
            }
            // -------------------------------------------------------------- //
            last = current;
        }
        // -------------------------------------------------------------- //
        //RESULT.setLength(0);
        // -------------------------------------------------------------- //
        if (current == -1 && count == 1) { RESULT.append(last); }
        if (current == -1 && count != 1) { operation(last, count); }
        // -------------------------------------------------------------- //

        // -------------------------------------------------------------- //
        os.write(insertData(RESULT));
        closedOutput(os);
        System.out.println(Arrays.toString(insertData(RESULT)));
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