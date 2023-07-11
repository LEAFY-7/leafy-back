package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.CommentMapper;
import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.comment.CommentDto;
<<<<<<< HEAD
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57

@Service
public class CommentService {

    @Autowired
<<<<<<< HEAD
    CommentMapper commentMapper;
    @Autowired
    QnaMapper qnaMapper;
    @Autowired
     EmailService emailService;
    @Autowired
    JavaMailSender mailSender; // Add this line

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    ExecutorService emailExecutor = Executors.newFixedThreadPool(10); // or however many threads you want
    public Long getCommentsByQnaId(Long id) {
        return commentMapper.selectByQnaId(id);
    }

    public int getCount() {
        return commentMapper.count();
    }
    public int remove(Long id, Long userId) { return commentMapper.delete(id, userId); }

    public int write(CommentDto commentDto) { return commentMapper.insert(commentDto); }
=======
    QnaMapper qnaMapper;

    @Autowired
    CommentMapper commentMapper;

    public int getCount(Long id) {
        return commentMapper.count();
    }

    public int remove(Long id, Long userId) {
        int rowCnt = commentMapper.delete(id, userId);
        return rowCnt;
    }

    public int write(CommentDto commentDto) {
        return commentMapper.insert(commentDto);
    }
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57

    public List<CommentDto> getList(){
        return commentMapper.selectAll();
    }

<<<<<<< HEAD
    public List<CommentDto> getRead(Long id){
        return commentMapper.select(id);
    }
    public int modify(CommentDto commentDto) {
        return commentMapper.update(commentDto);
    }
    public String getContent(Long qnaCommentId) {
        return commentMapper.getContent(qnaCommentId);
    }

    public String getEmail(Long id) { return commentMapper.getEmail(id); }
    public void sendEmailNotification(CommentDto commentDto) {
        emailExecutor.submit(() -> {
            Long id = commentDto.getId();
            String answerContent = getContent(id);
            String recipientEmail = getEmail(id);
            String subject = "답변이 완료되었습니다.";
            String content = "질문에 대한 답변입니다.:\n" + answerContent;
            emailService.sendEmailNotification(recipientEmail, subject, content);
        });
    }
//테스트를 위해 작성된 코드//
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmailNotificationtest(MimeMessagePreparator messagePreparator) {
        mailSender.send(messagePreparator);
    }

}


=======
    public CommentDto read(Long id){
        return commentMapper.select(id);
    }

    public int modify(CommentDto commentDto) {
        return commentMapper.update(commentDto);
    }
}
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
