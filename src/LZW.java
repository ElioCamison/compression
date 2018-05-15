import java.io.InputStream;
import java.io.OutputStream;

public class LZW {


    private int position = 0;
    private int value    = 0;
    private int[] table;


    LZW(int position, int value){
        this.position = position;
        this.value    = value;
    }



    public static void compress(InputStream is, OutputStream os) throws Exception {

        int current = 0;

        while ( (current = is.read()) != -1) {

        }


    }

    public static void decompress(InputStream is, OutputStream os) throws Exception {

    }
}
