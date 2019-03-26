import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    private Guest guest;
    @BeforeEach
    public void setUp()
    {
        Scanner input = new Scanner(System.in);
        guest = new Guest();
    }

    @Test
    void userInteface() {
        File file;
        try {
            file = new File("rooms.csv");
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

        guest.ID = "guest";
        guest.name = "name";
        guest.surname = "surname";

        String test  = "1\n3\nno\n";       // book
        String test2 = "2\n3\nno\n";       // cancel book

        ByteArrayInputStream in = new ByteArrayInputStream(test.getBytes());        // book
        System.setIn(in);
        Scanner input = new Scanner(System.in);
        guest.userInteface(input);

        in = new ByteArrayInputStream(test2.getBytes());                            //cancel
        System.setIn(in);
        input = new Scanner(System.in);
        guest.userInteface(input);
    }


    @AfterEach
    public void tearDown()
    {
        guest = null;
    }
}