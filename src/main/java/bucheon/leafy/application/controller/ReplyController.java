package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.ReplyService;
import bucheon.leafy.config.AuthUser;


import bucheon.leafy.domain.reply.ReplyDto;
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.enums.RemoveFailedException;
import bucheon.leafy.exception.enums.WriteFailedException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @Operation(summary = "대댓글 수정")
    @PatchMapping("/{id}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify( @AuthenticationPrincipal AuthUser user,
                                            @PathVariable("id") Long id,
                                            @RequestBody ReplyDto replyDto) {
        Long userId = user.getUserId();
        replyDto.setUserId(userId);


            if(replyService.modify(replyDto)!=1) {
                throw new ModifyFailedException();
            }
            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

    }
    @Operation(summary = "대댓글 쓰기")
    @PostMapping("/comments")   // /ch4/comments?bno=1085  POST
    public ResponseEntity<String> write( @AuthenticationPrincipal AuthUser user,
                                         @RequestBody ReplyDto replyDto) {

        Long userId = user.getUserId();
        replyDto.setUserId(userId);


        if (replyService.write(replyDto) != 1){
            throw new WriteFailedException();
        }
            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);

    }

    @Operation(summary = "대댓글 삭제")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read( @AuthenticationPrincipal AuthUser user,
                                        @PathVariable("id") Long id ) {


//        Long userId = user.getUserId();

        if (replyService.getRead(id).size() != 1) {
            throw new RemoveFailedException();
        } else {
            List<ReplyDto> replyDtoList = replyService.getRead(id);
            ReplyDto replyDto = replyDtoList.get(0);
            return ResponseEntity.ok().body(replyDto);
        }
    }
}

