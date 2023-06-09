package bucheon.leafy.domain.qna.controller;

import bucheon.leafy.domain.qna.domain.PageHandler;
import bucheon.leafy.domain.qna.domain.QnaDto;
import bucheon.leafy.domain.qna.domain.SearchCondition;
import bucheon.leafy.domain.qna.service.QnaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/")
public class QnaController {

    @Autowired
    QnaService qnaService;

    @PostMapping("/")
    public String modify(QnaDto qnaDto, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        String user_id = (String)session.getAttribute("id");
        qnaDto.setUser_id(user_id);

        try {
            if (qnaService.modify(qnaDto)!= 1)
                throw new Exception("Modify failed.");

            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect://"+sc.getQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(qnaDto);
            m.addAttribute("msg", "MOD_ERR");
            return "";
        }
    }

    @GetMapping("/")
    public String user_id(Model m) {
        m.addAttribute("mode", "new");
        return "";
    }

    @PostMapping("/")
    public String user_id(QnaDto qnaDto, RedirectAttributes rattr, Model m, HttpSession session) {
        String user_id = (String)session.getAttribute("id");
        qnaDto.setUser_id(user_id);

        try {
            if (qnaService.user_id(qnaDto) != 1)
                throw new Exception("User_id failed.");

            rattr.addFlashAttribute("msg", "WRT_OK");
            return"";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(qnaDto);
            m.addAttribute("mode", "new");
            m.addAttribute("msg", "WRT_ERR");
            return"";
        }
    }

    @GetMapping("/")
    public String read(Integer bno, SearchCondition sc, RedirectAttributes rattr, Model m) {
        try {
            QnaDto qnaDto = qnaService.read(bno);
            m.addAttribute(qnaDto);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "READ_ERR");
            return"";
        }

        return "";
    }

    @PostMapping("/")
    public String remove(Integer bno, SearchCondition sc, RedirectAttributes rattr, HttpSession session) {
        String user_id = (String)session.getAttribute("id");
        String msg = "DEL_OK";

        try {
            if(qnaService.remove(bno, user_id)!=1)
                throw new Exception("Delete failed.");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "DEL_ERR";
        }

        rattr.addFlashAttribute("msg", msg);
        return"";//
    }

    @GetMapping("/")
    public String list(Model m, SearchCondition sc, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect://"+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        try {
            int totalCnt = qnaService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<QnaDto> list = qnaService.getSearchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        return""; //"" 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서(false는 session이 없어도 새로 생성하지 않는다. 반환값 null)
        HttpSession session = request.getSession(false);
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session!=null && session.getAttribute("id")!=null;
    }
}

