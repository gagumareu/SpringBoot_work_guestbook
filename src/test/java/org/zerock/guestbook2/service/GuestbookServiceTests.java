package org.zerock.guestbook2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook2.Entity.Guestbook;
import org.zerock.guestbook2.dto.GuestbookDTO;
import org.zerock.guestbook2.dto.PageRequestDTO;
import org.zerock.guestbook2.dto.PageResultDTO;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    GuestbookService guestbookService;

    @Test
    public void testRegister(){

        GuestbookDTO dto = GuestbookDTO.builder()
                .title("Sample title")
                .content("Sample Content")
                .writer("Sample Writer")
                .build();

        System.out.println(guestbookService.register(dto));
    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = guestbookService.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());
        System.out.println("------------------------------------------");

        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }

        System.out.println("===========================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }

    @Test
    public void testSearch(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc")
                .keyword("한글")
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = guestbookService.getList(pageRequestDTO);

        System.out.println("Prev:" + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("-------------------------------------");
        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }

        System.out.println("===========================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }


}
