package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserReportRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.UserResponse;
import bucheon.leafy.domain.userreport.UserReport;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class UserReportService {

    private final UserRepository userRepository;
    private final UserReportRepository userReportRepository;

    public List<UserResponse> getReportedUsers(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<UserReport> userBlocks = userReportRepository.findByUser(user, pageable);

        List<User> reportUsers = userBlocks.stream()
                .map(UserReport::getReportUser)
                .collect(Collectors.toList());

        List<Long> userIds = reportUsers.stream()
                .map(User::getId)
                .collect(Collectors.toList());

        List<User> users = userRepository.findAllById(userIds);

        return users.stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }

    public void reportUser(Long userId, Long reportUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        User reportUser = userRepository.findById(reportUserId)
                .orElseThrow(UserNotFoundException::new);

        Boolean exist = userReportRepository.existsByUserAndReportUser(user, reportUser);

        if (!exist) {
            UserReport userReport = UserReport.of(user, reportUser);
            userReportRepository.save(userReport);
        }
    }

    public void reportCancelUser(Long userId, Long reportUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        User reportUser = userRepository.findById(reportUserId)
                .orElseThrow(UserNotFoundException::new);

        userReportRepository.deleteByUserAndReportUser(user, reportUser);
    }

}
