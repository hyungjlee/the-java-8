package me.whiteship.java8to11;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream2 {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1,"spring boot", true));
        springClasses.add(new OnlineClass(2,"spring data jpa", true));
        springClasses.add(new OnlineClass(3,"spring mvc", false));
        springClasses.add(new OnlineClass(4,"spring core", false));
        springClasses.add(new OnlineClass(5,"rest api dev", false));


        //filter + method reference + static method 사용
        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                // == .filter(oc -> !oc.isClosed())
                .filter(Predicate.not(OnlineClass::isClosed))   // predicate에 ! 사용불가.
                .forEach(System.out::println);  //터미널 오퍼레이터


        List<OnlineClass> javaClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(6,"The Java, Test", true));
        springClasses.add(new OnlineClass(7,"The Java, Code manipulation", true));
        springClasses.add(new OnlineClass(8,"The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("========================================");

        //flatMap 사용
        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        keesunEvents.stream()
                //  == .flatMap(list -> list.stream())
                .flatMap(Collection::stream)                    //오퍼레이터에 들어온 타입은 list 이다!!
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("========================================");

        //iterate 사용
        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 뺴고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        //anyMatch 사용
        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);

        //filter + map 사용
        System.out.println("스프링 수업 중에 제목에 spring 이 들어간 제목만 모아이서 List 로 만들기");
        List<String> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());

        spring.forEach(System.out::println);

    }
}
