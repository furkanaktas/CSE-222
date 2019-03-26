import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private File file;
    final InputStream original = System.in;
    ByteArrayInputStream in;
    Hotel hotel=null;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void main() {

        String []args=null;
        hotel = new Hotel();
//------------------------------------------------------------------------------------------------------------
        try {
            file = new File("rooms.csv");
            FileWriter writer = new FileWriter(file);

            writer.write("ROOM,STATUS,ID,NAME,SURNAME\n");
            writer.flush();
            for (int i = 0; i < 15; ++i) {
                writer.write((i+1)+",none,none,none,none\n");           // book için status none olmalı
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i <15 ; i++) {
            String test0 = "1\nadmin\nadmin\n1\n"+(i+1)+"\nguest\nno\nno\n";    // book

            in = new ByteArrayInputStream(test0.getBytes());
            System.setIn(in);
            Main.main(args);
        }
        String arr[] = new String[16];
        for (int i = 0; i < 16; i++) {
            arr[i] = "\0";
        }
        try {
            arr = hotel.readFile("rooms.csv",arr);          // rooms.csv üzerinden işlem yapılır
            hotel.writeFile("noneToBooked.csv",arr);        // o yüzden o veriler yeni dosyaya koyuldu
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//--------------------------------------------------------------------------------------------------------------------
        try {
            file = new File("rooms.csv");
            FileWriter writer = new FileWriter(file);

            writer.write("ROOM,STATUS,ID,NAME,SURNAME\n");
            writer.flush();
            for (int i = 0; i < 15; ++i) {
                writer.write((i+1)+",booked,guest,name,surname\n");     // cancel için status booked olmalı
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i <15 ; i++) {
            String test0 = "1\nadmin\nadmin\n2\n"+(i+1)+"\nguest\nno\nno\n";    // cancel reservation

            in = new ByteArrayInputStream(test0.getBytes());
            System.setIn(in);
            Main.main(args);
        }
        for (int i = 0; i < 16; i++) {
            arr[i] = "\0";
        }
        try {
            arr = hotel.readFile("rooms.csv",arr);
            hotel.writeFile("bookedToCancel.csv",arr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//-------------------------------------------------------------------------------------------------------------------
        try {
            file = new File("rooms.csv");
            FileWriter writer = new FileWriter(file);

            writer.write("ROOM,STATUS,ID,NAME,SURNAME\n");
            writer.flush();
            for (int i = 0; i < 15; ++i) {
                writer.write((i+1)+",booked,guest,name,surname\n"); // check in için status booked olmalı
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <15 ; i++) {
            String test0 = "1\nadmin\nadmin\n3\n"+(i+1)+"\nguest\nno\nno\n";    // check in

            in = new ByteArrayInputStream(test0.getBytes());
            System.setIn(in);
            Main.main(args);
        }
        for (int i = 0; i < 16; i++) {
            arr[i] = "\0";
        }
        try {
            arr = hotel.readFile("rooms.csv",arr);
            hotel.writeFile("bookedToCheckIn.csv",arr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//--------------------------------------------------------------------------------------------------------------------
        try {
            file = new File("rooms.csv");
            FileWriter writer = new FileWriter(file);

            writer.write("ROOM,STATUS,ID,NAME,SURNAME\n");
            writer.flush();
            for (int i = 0; i < 15; ++i) {
                writer.write((i+1)+",checkIn,guest,name,surname\n"); // check out için status check in olmalı
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <15 ; i++) {
            String test0 = "1\nadmin\nadmin\n4\n"+(i+1)+"\nguest\nno\nno\n";    // check out

            in = new ByteArrayInputStream(test0.getBytes());
            System.setIn(in);
            Main.main(args);
        }
        for (int i = 0; i < 16; i++) {
            arr[i] = "\0";
        }
        try {
            arr = hotel.readFile("rooms.csv",arr);
            hotel.writeFile("checkInToCheckOut.csv",arr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//--------------------------------------------------------------------------------------------------------------------

// ** **** * * ** *

//-------------------------------------------------------------------------------------------------------------------
        try {
            file = new File("rooms.csv");
            FileWriter writer = new FileWriter(file);

            writer.write("ROOM,STATUS,ID,NAME,SURNAME\n");
            writer.flush();
            for (int i = 0; i < 15; ++i) {
                writer.write((i+1)+",none,none,none,none\n"); // book için status none olmalı
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <15 ; i++) {
            String test0 = "2\nguest\nguest\n1\n"+(i+1)+"\nno\nno\n";    // book

            in = new ByteArrayInputStream(test0.getBytes());
            System.setIn(in);
            Main.main(args);
        }
        for (int i = 0; i < 16; i++) {
            arr[i] = "\0";
        }
        try {
            arr = hotel.readFile("rooms.csv",arr);
            hotel.writeFile("noneToBook_Guest.csv",arr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//--------------------------------------------------------------------------------------------------------------------

        try {
            file = new File("rooms.csv");
            FileWriter writer = new FileWriter(file);

            writer.write("ROOM,STATUS,ID,NAME,SURNAME\n");
            writer.flush();
            for (int i = 0; i < 15; ++i) {
                writer.write((i+1)+",booked,guest,name,surname\n"); // cancel için status booked olmalı
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <15 ; i++) {
            String test0 = "2\nguest\nguest\n2\n"+(i+1)+"\nno\nno\n";    // cancel

            in = new ByteArrayInputStream(test0.getBytes());
            System.setIn(in);
            Main.main(args);
        }
        for (int i = 0; i < 16; i++) {
            arr[i] = "\0";
        }
        try {
            arr = hotel.readFile("rooms.csv",arr);
            hotel.writeFile("bookedToCancel_Guest.csv",arr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//--------------------------------------------------------------------------------------------------------------------

// ** **** * * ** *

//-------------------------------------------------------------------------------------------------------------------



        for (int i = 0; i <5 ; i++) {
            String test0 = "3\n1\nguest"+(i+1)+"\nguest\nname\nsurname\nno\nno\n";    // guest kaydı, 2. kez çalıştıgında kayıtlı olan kişiler
                                                                                        // tekrar kayıt edilmeye çalışacağı için hata verir
            in = new ByteArrayInputStream(test0.getBytes());                            // ilk sefer için test çalışır
            System.setIn(in);
            Main.main(args);
        }

        for (int i = 0; i <5 ; i++) {
            String test0 = "3\n2\nadmin\nadmin\nadmin"+(i+1)+"\nadmin\nname\nsurname\nno\nno\n";   // admin kaydı, 2. kez çalıştıgında kayıtlı olan kişiler
                                                                                                     // tekrar kayıt edilmeye çalışacağı için hata verir
            in = new ByteArrayInputStream(test0.getBytes());                                         // ilk sefer için test çalışır
            System.setIn(in);
            Main.main(args);
        }

        System.setIn(original);
    }
}