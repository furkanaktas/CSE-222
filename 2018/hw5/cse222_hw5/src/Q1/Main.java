package Q1;

public class Main {

    public static void main(String[] args) {
        DoubleHash<String,String> map =new DoubleHash<>();

        for (int i = 0; i < 708; i++) {
            if (i == 57)
                map.put(String.valueOf(i), "1");
            else if (i == 97)
                map.put(String.valueOf(i), "2");
            else if (i == 131)
                map.put(String.valueOf(i), "3");
            else if (i == 297)
                map.put(String.valueOf(i), "4");
            else if (i == 497)
                map.put(String.valueOf(i), "5");
            else if (i == 697)
                map.put(String.valueOf(i), "6");
            else
                map.put(String.valueOf(i), "regular");
        }

        System.out.println(map.get("57") + "\n" +map.get("97") +"\n"+map.get("131") +"\n"+
                           map.get("297")+"\n" +map.get("497") +"\n"+map.get("697"));



        if (map.containsValue("4"))
            map.remove("57");

        System.out.println(map.get("57"));

        System.out.println("size: "+ map.size());


        //----------------------------------------------------------------------------------

        System.out.println( "------------------ Second Map --------------------");
        DoubleHash<String, Integer> map2 = new DoubleHash<>();

        for (int i = 0; i < 100; i++) {
            map2.put(String.valueOf(i), i);
        }

        System.out.println(map2.get("11"));
        System.out.println(map2.get("20"));
        System.out.println(map2.get("61"));

        System.out.println("size : "+ map2.size());

        map2.remove("11");
        map2.remove("20");
        map2.remove("61");

        if (map2.containsValue("11"))
            System.out.println("ilginc");
        else if (map2.containsValue("20"))
            System.out.println("ilginc");
        else if (map2.containsValue("61"))
            System.out.println("ilginc");
        else
            System.out.println("11, 20, 61 silindi !!");

        System.out.println("size : "+ map2.size());

    }
}
