import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception {
        byte b = (byte) 255;
        byte c = (byte) 0xFF;

        if (c == b)
            System.out.println((b & 0xFF) + " == " + (c & 0xFF));
    }

}
