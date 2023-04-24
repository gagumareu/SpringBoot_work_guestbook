package org.zerock.guestbook2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.guestbook2.dto.GuestbookDTO;
import org.zerock.guestbook2.dto.PageRequestDTO;
import org.zerock.guestbook2.service.GuestbookService;

@Controller
@RequestMapping({"/guestbook"})
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping({"/"})
    public String index(){

        return "redirect:/guestbook/list";
    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }
    
    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        
        log.info("dto... " + dto);
        
        // 새로 추가된 엔티티의 번호
        Long gno = guestbookService.register(dto);
        
        redirectAttributes.addFlashAttribute("msg", gno);
        
        return "redirect:/guestbook/list";
    }

   @GetMapping({"/list"})
   public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("list............" + pageRequestDTO);

       model.addAttribute("result", guestbookService.getList(pageRequestDTO));
   }

}
