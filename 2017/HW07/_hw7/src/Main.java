
import java.util.NavigableMap;
import java.util.SortedMap;


public class Main
{
    public static void main(String args[]){
        final Boolean q1 = Q1Test();
        final Boolean q2 = Q2Test();
        if (q1 == q2 == Boolean.TRUE) {
            //System.out.println("Your tests is done. Make sure that you test all methods of class!! " );
            return;
        }
    }
    public static Boolean Q1Test(){

        NavigableMap<String,String> turkey = new BinaryNavMap<String,String>();
        turkey.put("uskudar","istanbul");
        turkey.put("kadıkoy","istanbul");
        turkey.put("cekirge","bursa");
        turkey.put("gebze","kocaeli");
        turkey.put("niksar","tokat");
        turkey.put("kecıoren","ankara");
        turkey.put("aksaray","istanbul");
        turkey.put("foca","izmir");
        turkey.put("manavgat","antalya");
        turkey.put("kahta","adıyaman");
        turkey.put("biga","canakkale");

        //System.out.println("The original set odds is " + turkey);
        System.out.println("-----------------Test Q1-----------------");
        System.out.println("lowerEntry       : " +turkey.lowerEntry("foca").getKey() );
        System.out.println("lowerKey         : " +turkey.lowerKey("foca") );
        System.out.println("floorEntry       : " +turkey.floorEntry("foca").getKey() );
        System.out.println("floorKey         : " +turkey.floorKey("foca") );

        System.out.println("ceilingEntry     : " +turkey.ceilingEntry("foca").getKey() );
        System.out.println("ceilingKey       : " +turkey.ceilingKey("foca") );
        System.out.println("higherEntry      : " +turkey.higherEntry("foca").getKey() );
        System.out.println("higherKey        : " +turkey.higherKey("foca") );

        System.out.println("firstEntry       : " +turkey.firstEntry().getKey());
        System.out.println("firstKey         : " +turkey.firstKey());
        System.out.println("lastEntry        : " +turkey.lastEntry().getKey());
        System.out.println("lastKey          : " +turkey.lastKey());
        System.out.println("pollFirstEntry   : " +turkey.pollFirstEntry().getKey());
        System.out.println("pollLastEntry    : " +turkey.pollLastEntry().getKey());


        System.out.println("descendingMap    : " +turkey.descendingMap());
        System.out.println("navigableKeySet  : " +turkey.navigableKeySet());
        System.out.println("descendingKeySet : " +turkey.descendingKeySet());


        NavigableMap<String,String> m = turkey.subMap("gebze",true,"uskudar",false);
        System.out.println("subMap           : " + m);

        m = turkey.headMap("gebze",true);
        System.out.println("headMap          : " + m);

        m = turkey.tailMap("gebze",true);
        System.out.println("tailMap          : " + m);


        SortedMap<String,String> n = turkey.subMap("gebze","uskudar");
        System.out.println("subMap           : " + n);

        n = turkey.headMap("gebze");
        System.out.println("headMap          : " + n);


        n = turkey.tailMap("gebze");
        System.out.println("tailMap          : " + n);


        System.out.println("-----------------End Test Q1-----------------");

        //you should write more test function to show your solution
        //your test must contain all methods to get full points!!!
        //you also may need to owerwrite some methods to provide BST RULES

        /* *some links to help you

           https://docs.oracle.com/javase/8/docs/api/java/util/NavigableMap.html
           https://docs.oracle.com/javase/8/docs/api/java/util/AbstractMap.html

        * */
        return Boolean.TRUE;

    }
    public static Boolean Q2Test(){
        HashMap<String,String> turkey=new HashTableChaining<String,String>();
        turkey.put("edremit","balikesir");
        turkey.put("edremit","van");
        turkey.put("kemalpasa","bursa");
        turkey.put("kemalpasa","izmir");
        turkey.put("ortakoy","istanbul");//we assume a district
        turkey.put("ortakoy","aksaray");
        turkey.put("ortakoy","corum");
        turkey.put("kecıoren","ankara");
        turkey.put("pinarbasi","kastamonu");
        turkey.put("pinarbasi","kayseri");
        turkey.put("eregli","konya");
        turkey.put("eregli","tekirdag");
        turkey.put("eregli","zonguldak");
        turkey.put("golbasi","adıyaman");
        turkey.put("golbasi","ankara");
        turkey.put("biga","canakkale");

        System.out.println(turkey.get("eregli"));     // get
        turkey.remove("eregli");                // remove
        System.out.println(turkey.get("eregli"));

        System.out.println("size : " +turkey.size());              // size

        /* *test all

            V get(Object key);

            V put(K key, V value);

            V remove(Object key);

            int size();

        * */


        return Boolean.TRUE;
    }


}
