import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RLE_modiBenja {


    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        compress(new ByteArrayInputStream(new byte[]{1,1,1,1,1,1}), bos);
        System.out.println(Arrays.toString(bos.toByteArray()));
    }

    public static void compress(InputStream is, OutputStream os) throws Exception {
        List<Byte> bytes = getBytes(is);
        int current, last, count = 0;
        // -------------------------------------------------------------- //
        for (int i = 0; i < bytes.size(); i++) {
            last = bytes.get(i);
            for (int j = i; j < bytes.size(); j++) {
                current = bytes.get(j);
                if (current != last) break;
                count++;
                i = j;
            }
            count = operation(last, count, os);

        }
        // ----------------------------------------------------------------- //
        os.flush();
        os.close();
    }

    private static List<Byte> getBytes(InputStream is) throws IOException {
        List<Byte> bytes = new ArrayList<>();
        int n = 0;
        while (is.available() != 0) {
            n = is.read();
            bytes.add((byte) n);
        }
        return bytes;
    }

    // -------------------------------------------------------------- //
    // -------------------------------------------------------------- //
    private static int operation(int last, int count, OutputStream os) throws IOException {
        if (count >= 2) {
            os.write(last);
            os.write(last);
            os.write(count == 2 ? (byte) 0 : (byte) count - 2);
        } else {
            os.write(last);
        }
        return 0;
    }

}

