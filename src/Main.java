import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception {
        RLE rle = new RLE();

        // fis = File Input Stream
        FileInputStream fis = new FileInputStream("C:\\Users\\Elio\\Desktop\\practica.txt");
        try {
            System.out.println(compress(fis));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String compress(InputStream is) throws Exception {
        int current = 0;
        int last = -1;
        StringBuilder sb = new StringBuilder();

        // Contador, lo utilizamos para contar las veces que se repite un número.
        int count = 0;

        while ((current = is.read()) != -1) {
            // Guardamos el valor anterior, el primer valor será -1.
            last = current;
            // Comparamos el último valor con el actual.
            if(last == current) {
                count++;
            } else {
                sb.append(last);
                sb.append(last);
                sb.append(current);
                System.out.println(sb.toString());
                /*os.write(last);
                os.write(last);
                os.write(current);*/
            }
        }
        return sb.toString();
    }
}
