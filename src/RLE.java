import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RLE {
    private static final Integer MAX_NUMBER_REPEAT = 2;
    private static final Integer MAX_CAPACITY_BYTE = 255;
    public static StringBuilder RESULT = new StringBuilder();
    public static List<Integer> LIST = new ArrayList();
    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //

    public static void compress(InputStream is, OutputStream os) throws Exception {
        // -------------------------------------------------------------- //
        int current = is.read(), last = 0, count = 0;
        // -------------------------------------------------------------- //
        if(current != -1){ last = current; count = 1; }
        // -------------------------------------------------------------- //
        while (true) {
            current = is.read();
            // ---------------------------------------------------------- //
            if ( last == current ) { count++; continue; }
            // ---------------------------------------------------------- //
            else {
                // ------------------------------------------------------ //
                if (count >= 2)  operation(last, count);
                else LIST.add(last);
                // ------------------------------------------------------ //
                count = 1;
                last = current;
                // ------------------------------------------------------ //
            }
            if(current == -1) break;
        }
        // -------------------------------------------------------------- //
        for (int i = 0; i < LIST.size(); i++) {
            os.write(LIST.get(i));
        }
        // -------------------------------------------------------------- //
        System.out.println(Arrays.toString(LIST.toArray()));
        closedOutput(os);
        LIST.clear();
    }

    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //
    public static void decompress(InputStream is, OutputStream os) throws Exception {

        int current = is.read(), last = 0, count = 0;

        if(current != -1){ last = current; count = 1; }
        // -------------------------------------------------------------- //
        while (true) {
            current = is.read();
            // -------------------------------------------------------------- //
            if ( last == current ) { count++; continue; }
            // -------------------------------------------------------------- //
            else {
                // ---------------------------------------------------------- //
                if (count == 2) { operationDecompress(last, count); }
                else {
                    if (last == 0){ break; }
                    RESULT.append(last);
                }
                // ---------------------------------------------------------- //
                count = 1;
                last = current;
                // ---------------------------------------------------------- //
            }
            if(current == -1) break;
        }
        // -------------------------------------------------------------- //
        for (int i = 0; i < LIST.size(); i++) {
            os.write(LIST.get(i));
        }
        // ----------------------------------------------------------------- //
        System.out.println(Arrays.toString(LIST.toArray()));
        closedOutput(os);
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
    private static List operation(int last, int count) {
        if (count > 255) {
            maxCapacityByte(last,count);
        } else {
            for (int i = 0; i < MAX_NUMBER_REPEAT; i++) {
                LIST.add( last);
            }
            count -=  MAX_NUMBER_REPEAT;
            LIST.add(count);
        }
        return LIST;
    }

    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //
    private static List maxCapacityByte(int last, int count){
        for (int i = 0; i < MAX_NUMBER_REPEAT; i++) {
            LIST.add(last);
        }
        // ---------------------------------------------------------- //
        int aux = count - MAX_NUMBER_REPEAT;
        // ---------------------------------------------------------- //
        byte b = (byte) 255;
        LIST.add( MAX_CAPACITY_BYTE);
        aux -= MAX_CAPACITY_BYTE;
        // ---------------------------------------------------------- //
        for (int i = 0; i < MAX_NUMBER_REPEAT; i++) {
            LIST.add(last);
        }
        // ---------------------------------------------------------- //
        if (aux <= 255) {
            aux -= MAX_NUMBER_REPEAT;
            LIST.add(aux);
        } else {
            LIST.add(MAX_CAPACITY_BYTE);
            count = aux-MAX_CAPACITY_BYTE;
            count -= MAX_NUMBER_REPEAT;
            operation(last,count);
        }
        // ---------------------------------------------------------- //
        return  LIST;
    }

    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //
    private static StringBuilder operationDecompress(int last, int count) {
        //if (count > 255) { maxCapacityByte(last,count);}

        for (int i = 0; i < count; i++) {
            RESULT.append(last);
        }
        return RESULT;
    }
}