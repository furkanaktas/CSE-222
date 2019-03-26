import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ReceptionistTest {

    private Receptionist recep;
    @BeforeEach
    public void setUp()
    {
        Scanner input = new Scanner(System.in);
        recep = new Receptionist();
    }

    @Test
    void userInteface() {

        String test =   "1\n" +     // book
                        "25\n5\n" +     // room number (1<=number<=15  25 geçersiz tekrar istiyor bu yüzden 5 tekrar)
                        "guest\n" + // guest id
                        "no\n";  // want to continue ??;


        String test2 = "2\n5\nguest\nno\n";    // cancel reservation

        String test3 = "3\n5\nguest\nno\n";    // check in
        String test4 = "4\n5\nguest\nno\n";    // check out

        ByteArrayInputStream in = new ByteArrayInputStream(test.getBytes());
        System.setIn(in);
        Scanner input = new Scanner(System.in);
        recep.userInteface(input);              // book


        in = new ByteArrayInputStream(test2.getBytes());
        System.setIn(in);
        input = new Scanner(System.in);         // cancel
        recep.userInteface(input);



        in = new ByteArrayInputStream(test.getBytes());
        System.setIn(in);
        input = new Scanner(System.in);         // book
        recep.userInteface(input);



        in = new ByteArrayInputStream(test3.getBytes());
        System.setIn(in);
        input = new Scanner(System.in);         // check in
        recep.userInteface(input);

        in = new ByteArrayInputStream(test4.getBytes());
        System.setIn(in);
        input = new Scanner(System.in);         // check out
        recep.userInteface(input);
    }

    @AfterEach
    public void tearDown()
    {
        recep = null;
    }
}