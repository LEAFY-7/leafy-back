package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.NoticeService;
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.qna.PageHandler;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.qna.SearchCondition;
import bucheon.leafy.application.service.QnaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/noticeAndQna")
public class QnaController {

    private NoticeService noticeService;
    private QnaService qnaService;
    @Operation(summary = "Qna/Notice게시판 수정하기")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @PostMapping("/modify/{id}")
    public String modify(QnaDto qnaDto,NoticeDto noticeDto, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        Integer user_id = (Integer)session.getAttribute("id");

        if(qnaDto.equals(qnaDto)){

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

        } else {

            noticeDto.setUser_id(user_id);

            try {
                if (noticeService.modify(noticeDto)!= 1)
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
    }
    @Operation(summary = "Qna/Notice게시판 수정하기")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @GetMapping("/write")
    public String userId(Model m) {
        m.addAttribute("mode", "new");
        return "";
    }
    @Operation(summary = "Qna/Notice게시판 수정하기")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @PostMapping("/write")
    public String userId(QnaDto qnaDto, NoticeDto noticeDto, RedirectAttributes rattr, Model m, HttpSession session) {
        Integer user_id = (Integer)session.getAttribute("id");

        if(qnaDto.equals(qnaDto)){

            qnaDto.setUser_id(user_id);

            try {
                if (qnaService.modify(qnaDto)!= 1)
                    throw new Exception("userId failed.");

                rattr.addFlashAttribute("msg", "WRT_OK");
                return"/";
            } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute(qnaDto);
                m.addAttribute("mode", "new");
                m.addAttribute("msg", "WRT_ERR");
                return"/";
            }

        } else {

            noticeDto.setUser_id(user_id);

            try {

                if (noticeService.modify(noticeDto)!= 1)
                    throw new Exception("userId failed.");

                rattr.addFlashAttribute("msg", "WRT_OK");
                return"/";
            } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute(noticeDto);
                m.addAttribute("mode", "new");
                m.addAttribute("msg", "WRT_ERR");
                return"";
            }

        }
    }
    @Operation(summary = "Qna/Notice게시판 수정하기")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @GetMapping("/read/{id}")
    public String read(QnaDto qnaDto, NoticeDto noticeDto, SearchCondition sc, RedirectAttributes rattr, Model m) {

        try {

            qnaDto = qnaService.read(qnaDto.getId());
            noticeDto = noticeService.read(noticeDto.getId());
            m.addAttribute(qnaDto);
            m.addAttribute(noticeDto);

        } catch (Exception e) {

            e.printStackTrace();
            rattr.addFlashAttribute("msg", "READ_ERR");
            return"";
        }

        return "";
    }
    @Operation(summary = "Qna/Notice게시판 수정하기")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @PostMapping("/remove/(id)")
    public String remove(QnaDto qnaDto, NoticeDto noticeDto, SearchCondition sc, RedirectAttributes rattr, HttpSession session) {
        Integer user_id = (Integer)session.getAttribute("id");
        String msg = "DEL_OK";

        try {

            if((qnaService.remove(qnaDto.getId() , user_id)!=1) || (noticeService.remove(noticeDto.getId(), user_id)!=1));

                throw new Exception("Delete failed.");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "DEL_ERR";
        }

        rattr.addFlashAttribute("msg", msg);
        return"";//
    }
    @Operation(summary = "Qna/Notice게시판 수정하기")
    @GetMapping("/list")
    public String list(Model m, SearchCondition sc, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect://"+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        try {
            int totalCnt = qnaService.getSearchResultCnt(sc);//
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<QnaDto> list = qnaService.getSearchSelectPage(sc);//
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
    @Operation(summary = "Qna/Notice게시판 수정하기")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서(false는 session이 없어도 새로 생성하지 않는다. 반환값 null)
        HttpSession session = request.getSession(false);
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session!=null && session.getAttribute("id")!=null;
    }
}

