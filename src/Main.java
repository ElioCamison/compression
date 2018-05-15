import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception {


        List<Integer> list = new ArrayList();

        list.add(1);
        list.add(2);
        list.add(0);
        list.add(9);
        list.add(8);
        list.add(0);
        list.add(1);

        System.out.println(Arrays.toString(list.toArray()));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                list.remove(i);
            }
        }

        System.out.println(Arrays.toString(list.toArray()));
    }

}
