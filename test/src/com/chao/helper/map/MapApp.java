package com.chao.helper.map;

import java.util.*;

/**
 * Created by think on 2017/1/13.
 *
 * 关于map操作的一些问题
 */
public class MapApp {

    public static void main(String[] args) {
        /**
         * 1. 将Map转化成List
         * Map接口提供了三种collection：key set,value set 和 key-value set，每一种都可以转成List。如下：
         */

        //map
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,10);
        map.put(2,20);
        map.put(3,30);
        //key list
        ArrayList<Integer> keyList = new ArrayList<>(map.keySet());
        //value list
        ArrayList<Integer> valueList = new ArrayList<>(map.values());
        //key-value list
        ArrayList<Map.Entry<Integer,Integer>> entryList = new ArrayList<>(map.entrySet());


        /**
         * 2. 迭代Map
         * 最高效的遍历map的每个entry的方法如下：
         */
        for (Map.Entry entry : map.entrySet()){
            int key = (int) entry.getKey();
            int value = (int) entry.getValue();
        }
        //也可以使用iterator，特别是JDK 1.5之前。
        Iterator itr = map.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry entry = (Map.Entry) itr.next();
            int key = (int) entry.getKey();
            int value = (int) entry.getValue();
        }


        /**
         * 3. 根据key对map进行排序
         * 可以将Map.Entry放入一个list，然后自己实现Comparator来对list排序。
         */
        ArrayList<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                return e1.getKey().compareTo(e2.getKey());
            }
        });

        //可以使用SortedMap。SortedMap的一个实现类是TreeMap。TreeMap的构造器可以接受一个Comparator参数。如下：
        SortedMap<Integer,Integer> sortedMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer k1, Integer k2) {
                return k1.compareTo(k2);
            }
        });
        sortedMap.putAll(map);
        //注：TreeMap默认对key进行排序。


        /**
         * 4. 根据value对map进行排序
         */

        ArrayList<Map.Entry<Integer,Integer>> listV = new ArrayList<>(map.entrySet());
        Collections.sort(listV, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        //如果map中的value不重复，可以通过反转key-value对为value-key对来用上面的3中的TreeMap方法对其排序。该方法不推荐。


        /**
         * 6. HashMap、TreeMap和HashTable的区别
         * Map接口有三个比较重要的实现类，分别是HashMap、TreeMap和HashTable。
         * TreeMap是有序的，HashMap和HashTable是无序的。
         * Hashtable的方法是同步的，HashMap的方法不是同步的。这是两者最主要的区别。
         * 这就意味着Hashtable是线程安全的，HashMap不是线程安全的。HashMap效率较高，Hashtable效率较低。
         * 如果对同步性或与遗留代码的兼容性没有任何要求，建议使用HashMap。
         * 查看Hashtable的源代码就可以发现，除构造函数外，Hashtable的所有 public 方法声明中都有 synchronized关键字，而HashMap的源码中则没有。
         * Hashtable不允许null值，HashMap允许null值（key和value都允许）
         * 父类不同：Hashtable的父类是Dictionary，HashMap的父类是AbstractMap
         * Hashtable中hash数组默认大小是11，增加的方式是 old*2+1。HashMap中hash数组的默认大小是16，而且一定是2的指数。
         */

        /**
         * 7. 创建一个空的Map
         * 如果希望该map为不可变的，则：
         * map = Collections.emptyMap();
         * 否则：
         * map = new HashMap();
         */



    }


    /**
     * 5. 初始化一个不可变Map
     * 正确的做法：
     */
    public static class Test{
        private static Map<Integer,Integer> map1 = new HashMap<>();
        static {
            map1.put(8,9);
            map1.put(88,99);
            map1 = Collections.unmodifiableMap(map1);
        }
    }

    //错误的做法：
    public static class TestError{
        private static final Map<Integer,Integer> map1 = new HashMap<>();
        static {
            map1.put(8,9);
            map1.put(88,99);
        }
    }
    //加了final只能确保不能 map1 = new，但是可以修改map1中的元素。

}
