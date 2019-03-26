import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by furkan on 17.03.2017.
 */
public class MainQ1 {

    public static void main(String args[]){

        q1Test();        // part 1
    }

    /**
     * part1 için test işlemleri
     */
    public static void q1Test(){


        try {
            File file = new File("test.csv");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            File file2 = new File("testResult_1.csv");
            FileWriter writer = new FileWriter(file2);

            String line;
            line = reader.readLine();    // satır okundu

            while(line != null) {

                String tokens[] = line.split(",");
                try {

                    Integer.parseInt(tokens[0]);
                    StackA<Integer>     a1 = new StackA();
                    StackB<Integer>     b1 = new StackB();
                    StackC<Integer>     c1 = new StackC();
                    StackD<Integer>     d1 = new StackD();
                    for (int i = 0; i < tokens.length; ++i) {
                        a1.push(Integer.parseInt(tokens[i]));
                        b1.push(Integer.parseInt(tokens[i]));
                        c1.push(Integer.parseInt(tokens[i]));
                        d1.push(Integer.parseInt(tokens[i]));
                    }
                    write(writer, a1, "stackA (int):");
                    write(writer, b1, "stackB (int):");
                    write(writer, c1, "stackC (int):");
                    write(writer, d1, "stackD (int):");

                }catch (NumberFormatException e){
                    try{

                        Double.parseDouble(tokens[0]);
                        StackA<Double>     a2 = new StackA();
                        StackB<Double>     b2 = new StackB();
                        StackC<Double>     c2 = new StackC();
                        StackD<Double>     d2 = new StackD();
                        for (int i = 0; i < tokens.length; ++i) {
                            a2.push(Double.parseDouble(tokens[i]));
                            b2.push(Double.parseDouble(tokens[i]));
                            c2.push(Double.parseDouble(tokens[i]));
                            d2.push(Double.parseDouble(tokens[i]));
                        }
                        write(writer, a2, "stackA (double):");
                        write(writer, b2, "stackB (double):");
                        write(writer, c2, "stackC (double):");
                        write(writer, d2, "stackD (double):");

                    }catch (NumberFormatException ex){
                            if (tokens[0].length() == 1) {

                                StackA<Character>     a3 = new StackA();
                                StackB<Character>     b3 = new StackB();
                                StackC<Character>     c3 = new StackC();
                                StackD<Character>     d3 = new StackD();
                                for (int i = 0; i < tokens.length; ++i) {
                                    a3.push(tokens[i].charAt(0));
                                    b3.push(tokens[i].charAt(0));
                                    c3.push(tokens[i].charAt(0));
                                    d3.push(tokens[i].charAt(0));
                                }
                                write(writer, a3, "stackA (char):");
                                write(writer, b3, "stackB (char):");
                                write(writer, c3, "stackC (char):");
                                write(writer, d3, "stackD (char):");

                            } else if(tokens[0].length() > 1) {

                                StackA<String >     a4 = new StackA();
                                StackB<String >     b4 = new StackB();
                                StackC<String >     c4 = new StackC();
                                StackD<String >     d4 = new StackD();
                                for (int i = 0; i < tokens.length; ++i) {
                                    a4.push(tokens[i]);
                                    b4.push(tokens[i]);
                                    c4.push(tokens[i]);
                                    d4.push(tokens[i]);
                                }
                                write(writer, a4, "stackA (String):");
                                write(writer, b4, "stackB (String):");
                                write(writer, c4, "stackC (String):");
                                write(writer, d4, "stackD (String):");

                            }
                    }
                }
                line = reader.readLine();
            }
            reader.close();
            writer.close();

        } catch (FileNotFoundException ex) {System.out.println("test.cvs is not opened !");}
        catch (IOException ex) {System.out.println("IO error !");}
    }

    /**
     *  Yollanan yazmak için açılan dosyaya , stack içindeki verileri size ve performans bilgisiyle beraber yazar.
     *
     * @param writer dosyaya yazmak için açılan writer(FileWriter)
     * @param stack  dosyaya yazılacak stack
     * @param type   veri tipine uygun gelen string
     * @throws IOException  dosya sorunları için
     */
    public static void write(FileWriter writer, StackInterface stack, String type) throws IOException {

        long startTime = System.nanoTime();

        int size = stack.size();
        for (int i = 0; i < size; ++i) {


            if (i == 0) {
                writer.write("size:" + size + " , ");   // get()
                writer.flush();
            }
            if(i != size-1)
            {
                writer.write(stack.pop() + ",");
                writer.flush();
            }
            else
            {
                writer.write(stack.pop()+"\n");
                writer.flush();
            }
        }

        long endTime = System.nanoTime();
        long estimatedTime = endTime - startTime; // Geçen süreyi nanosaniye
        double seconds = (double)estimatedTime/1000000000.0; // saniyeye çevirmek için milyar'a bölüyoruz.
        System.out.println(type+seconds);
    }


}

