import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;
    private File file;
    final InputStream original = System.in;

    @BeforeEach
    public void setUp()
    {
        hotel = new Hotel();
        try {
            file = new File("test.csv");
            FileWriter writer = new FileWriter(file);

            writer.write("ROOM,STATUS,ID,NAME,SURNAME\n");
            writer.flush();
            for (int i = 0; i < 15; ++i) {
                writer.write((i+1)+",none,none,none,none\n");
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @org.junit.jupiter.api.Test
    void readFile() {
        String arr[] = new String [10];
        try {
            arr = hotel.readFile("test.csv", arr);
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void writeFile() {
        String arr[] = new String[3];
        arr[0] = "writed";
        arr[1] = "writed";
        arr[2] = "writed";

        hotel.writeFile("test2.csv",arr);
    }

    @org.junit.jupiter.api.Test
    void register() {
        String idPass = "guest7\nguest7\nname\nsurname\nguest8\nguest8\nname\nsurname\nguest9\nguest9\nname\nsurname\n";
        ByteArrayInputStream in = new ByteArrayInputStream(idPass.getBytes());
        System.setIn(in);
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            hotel.register("guests.csv", input);
        }
    }

    @org.junit.jupiter.api.Test
    void login() {
        String idPass = "guest\nguest\nguest2\nguest2\nguest3\nguest3\nguest4\nguest4\nguest5\nguest5\nguest6\nguest6\n";

        ByteArrayInputStream in = new ByteArrayInputStream(idPass.getBytes());
        System.setIn(in);
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 6; ++i) {           // şuan 6 kişi kayıtlı
            hotel.login("guests.csv", input);
        }


        System.setIn(original);
    }

    @org.junit.jupiter.api.Test
    void sizeOfLines() {
        Assertions.assertEquals(16,hotel.sizeOfLines("test.csv"));  // açılan dosyanın satır sayısı
    }

    @org.junit.jupiter.api.Test
    void size() {
        String [] arr = new String[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = "\0";
        }
        arr[0] = "0";
        arr[1] = "0";
        arr[2] = "0";
        Assertions.assertEquals(3, hotel.size(arr));    // \0  lar sayılmaz.

    }

    @org.junit.jupiter.api.Test
    void toLarger() {
        String [] arr = new String[5];
        arr = hotel.toLarger(arr);
        Assertions.assertEquals(10, arr.length);        // array lentgh *2 oldu.
    }

    @org.junit.jupiter.api.Test
    void getNumber() {

        String test = "1\n4\n7\n18\n15\n";
        ByteArrayInputStream in = new ByteArrayInputStream(test.getBytes());
        System.setIn(in);
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            int num = hotel.getNumber(input);
        }
    }

    @org.junit.jupiter.api.Test
    void askToContinue() {
        String test = "yes\nyes\nno\n";
        ByteArrayInputStream in = new ByteArrayInputStream(test.getBytes());
        System.setIn(in);
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            boolean stat = hotel.askToContinue(input);
        }
    }


    @AfterEach
    public void tearDown()
    {
        hotel = null;
        file.delete();
    }
}