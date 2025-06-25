package cn.jll;

import cn.jll.model.Author;
import cn.jll.model.Book;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo01 {

    public static void main(String[] args) {

        System.out.println("StreamDemo01");
        List<Author> authors = getAuthors();

        /**
         *  学习的内容，如何使用Stream 流对于list进行各种的操作。
         */


        /**
         * 案例1：打印所有年龄小于18的作家的名字，需要去重。
         * 思考：
         */
        System.out.println("Stream流使用匿名内部类的方法");
        authors.stream()
                .distinct()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() < 18;
                    }
                })
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getName());
                    }
                });

        System.out.println("Stream 使用Lambda表达式的方法");
        authors.stream() //先把list转化为stream，这个方法的返回值是一个stream对象。后面就是stream对象的一些方法。
                .distinct() // 先除去重复的作家？ 根据什么标准去重复呢
                .filter(author -> author.getAge() < 18) // 筛选所有年龄小于18的，剩下所有小于18的 autohors， filter的源码中的参数是一个interface的接口，同时只有一个抽象方法，可以使用匿名内部类，然后可以携程lambda表达式的方法。
                                                        // lambda表达式的结果为true就会被留在流中。就实现了过滤
                .forEach(author -> System.out.println(author.getName())); // 遍历打印名字，现在的流对象中就是小于18的

        System.out.println(authors);

        // 如何创建stream

        /**
         * List -->
         */
        List<Author> authorList = getAuthors();
        // authorList.stream()         // 将list 转换成 Stream


        /**
         * 数组 --> stream
         */
        // 匿名内部类写法，如果不会直接写先写匿名内部类方法
        Integer[] arr = {1,2,3,4,5};
        Stream<Integer> stream = Arrays.stream(arr);  // 很重要
        stream.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 3;
            }
        }).forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

        // 然后就写 lambda表达式 + stream流的写法
//        stream.filter(integer -> integer>3)
//                .forEach( integer -> System.out.println(integer));

        /**
         * arr -> stream的第二个方法
         * Stream<Integer> stream = Stream.of (arr)
         */

        /**
         * Map 集合、
         * 先将Map集合转换成一个单列集合
         * map使用entrySet() 返回一个set集合
         */

        Map<String, Integer> map = new HashMap<>();
        map.put("蜡笔小新", 19);
        map.put("黑子", 17);
        map.put("cxk", 23);

        Set<Map.Entry<String, Integer>> entrySet =  map.entrySet();
        // 单列集合可以直接调用一个方法转换成stream
//        entrySet.stream()
//                .filter(new Predicate<Map.Entry<String, Integer>>() {
//                    @Override
//                    public boolean test(Map.Entry<String, Integer> stringIntegerEntry) {
//                        return stringIntegerEntry.getValue() > 18;
//                    }
//                }).forEach(new Consumer<Map.Entry<String, Integer>>() {
//                    @Override
//                    public void accept(Map.Entry<String, Integer> stringIntegerEntry) {
//                        System.out.println(stringIntegerEntry.getKey() + "" + stringIntegerEntry.getValue());
//                    }
//                });

        // lambda 表达式的方法
        entrySet.stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue() > 18)
                .forEach( stringIntegerEntry ->
                        System.out.println(stringIntegerEntry.getKey() + "" + stringIntegerEntry.getValue()));


        /**
         * 打印所有作家的姓名
         */
        List<Author> authorList2 = getAuthors();
//        authorList2.stream()    // 流中的每个元素都是作家
//                .map(new Function<Author, String>() { // map 运行之后从每个元素的作家类型转换到了 String 类型
//                    @Override
//                    public String apply(Author author) {
//                        return author.getName();
//                    }
//                }).forEach(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) {
//                        System.out.println(s);
//                    }
//                });

        authorList2.stream()    // 流中的每个元素都是作家
                .map( author -> author.getName())   //转换之后，元素的类型从作家到 String
                .forEach(s -> System.out.println(s));

        authorList2.stream()
                .distinct()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o1.getAge() - o2.getAge();
                    }
                }).forEach(s -> System.out.println(s.getAge()));

        authorList2.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .limit(1)
                .forEach(s -> System.out.println(s.getName()));


        /**
         * cout 使用
         */
        // 例如，打印这些作家的所列出书籍的数目，注意删除重复的元素。
        List<Author> authorList3 = getAuthors();
        long count = authorList3.stream()
                .flatMap(new Function<Author, Stream<Book>>() {
                    @Override
                    public Stream<Book> apply(Author author) {
                        return author.getBooks().stream();
                    }
                })
                .distinct()
                .count();

        System.out.println("Count is: " + count);


        // 获取一个存放所有作者名字的List集合
        List<String> nameList =  authorList3.stream()
                .distinct()
                .map(new Function<Author, String>() {
                    @Override
                    public String apply(Author author) {
                        return author.getName();
                    }
                })
                .collect(Collectors.toList());
        /**
         * 常用的方法是什么呢？
         * Collectors.toList() ---> 把流转换成List
         */
        System.out.println("nameList: " + nameList);

        // 获取一个set集合，集合中有所有的书名
        Set<String> bookNameSet = authorList3.stream()
                . flatMap(author -> author.getBooks().stream())
                    .map(book -> book.getName())
                                .collect(Collectors.toSet());

        /**
         * 常用的方法是什么呢？
         * Collectors.toList() ---> 把流转换成List
         */
        System.out.println("bookNameList: " + bookNameSet);
        
        // 获取一个Map集合，map的key就是作者名字，value就是List<Book>
        // Map中不允许重复的key，因此在组成map集合之前需要去重
        Map<String, List<Book>> collect = authorList3.stream()
                .distinct()
                .collect(Collectors.toMap(new Function<Author, String>() {
                    @Override
                    public String apply(Author author) {
                        return author.getName();
                    }
                }, new Function<Author, List<Book>>() {
                    @Override
                    public List<Book> apply(Author author) {
                        return author.getBooks();
                    }
                }));

        System.out.println(collect);
    }

    private static List<Author> getAuthors() {
        // 数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

        // 书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的年代理解"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        // 将四个作家，放在一个List中，然后返回这个list
        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }

}
