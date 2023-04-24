package org.zerock.guestbook2.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook2.Entity.Guestbook;
import org.zerock.guestbook2.Entity.QGuestbook;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    GuestbookRepository guestbookRepository;

    @Test
    public void test(){
        System.out.println(guestbookRepository.getClass().getName());
    }

    @Test
    public void insertTest(){

        IntStream.rangeClosed(1, 300).forEach(i -> {
            Guestbook book = Guestbook.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer("writer" + i).build();
            guestbookRepository.save(book);
        });

    }

    @Test
    public void getTest(){
        Long no = 1L;

        Optional<Guestbook> result = guestbookRepository.findById(no);

        System.out.println("-------------------------");

        if(result.isPresent()){
            Guestbook book = result.get();
            System.out.println(book);
        }
    }

    @Transactional
    @Test
    public void getTest2(){
        Long no = 3L;

       Guestbook book = guestbookRepository.getOne(no);

        System.out.println("-----------------------------");

        System.out.println(book);
    }

    @Test
    public void updateTest(){

        Optional<Guestbook> result = guestbookRepository.findById(3L);

        if(result.isPresent()){
            Guestbook book = result.get();
            book.changeTitle("change title");
            book.changeContent("changed content");

            guestbookRepository.save(book);
        }


    }


    @Test
    public void deleteTest(){
        Long no1 = 1L;
        Long no2 = 2L;
        guestbookRepository.deleteById(no1);
        guestbookRepository.deleteById(no2);
    }

    @Test
    public void testQuery1(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qGuestbook.title.contains(keyword);

        builder.and(expression);

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(i -> {
            System.out.println(i);
        });

    }

    @Test
    public void testQuery2(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);

        BooleanExpression exContent = qGuestbook.content.contains(keyword);

        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);

        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(i ->{
            System.out.println(i);
        });
    }



}
