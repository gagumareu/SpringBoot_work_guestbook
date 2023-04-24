package org.zerock.guestbook2.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook2.Entity.Guestbook;

import java.util.Optional;

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
        Guestbook book = Guestbook.builder()
                .title("title2")
                .content("content2")
                .writer("writer2").build();
        guestbookRepository.save(book);
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
        Long no = 1L;

       Guestbook book = guestbookRepository.getOne(no);

        System.out.println("-----------------------------");

        System.out.println(book);
    }

    @Test
    public void updateTest(){
        Guestbook book = Guestbook.builder().gno(1L)
                .title("update1")
                .content("update1")
                .writer("update1").build();
        guestbookRepository.save(book);
    }


    @Test
    public void deleteTest(){
        Long no1 = 1L;
        Long no2 = 2L;
        guestbookRepository.deleteById(no1);
        guestbookRepository.deleteById(no2);
    }

}
