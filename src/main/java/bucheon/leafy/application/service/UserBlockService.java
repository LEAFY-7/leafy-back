package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserBlockRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.UserResponse;
import bucheon.leafy.domain.userblock.UserBlock;
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
public class UserBlockService {

    private final UserRepository userRepository;
    private final UserBlockRepository userBlockRepository;

    public List<UserResponse> getBlockedUsers(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<UserBlock> userBlocks = userBlockRepository.findByUser(user, pageable);

        List<User> blockUsers = userBlocks.stream()
                .map(UserBlock::getBlockUser)
                .collect(Collectors.toList());

        List<Long> userIds = blockUsers.stream()
                .map(User::getId)
                .collect(Collectors.toList());

        List<User> users = userRepository.findAllById(userIds);

        return users.stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }

    public void blockUser(Long userId, Long blockUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        User blockUser = userRepository.findById(blockUserId)
                .orElseThrow(UserNotFoundException::new);

        UserBlock userBlock = UserBlock.of(user, blockUser);
        userBlockRepository.save(userBlock);
    }

    public void noneBlockUser(Long userId, Long blockUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        User blockUser = userRepository.findById(blockUserId)
                .orElseThrow(UserNotFoundException::new);

        userBlockRepository.deleteByUserAndBlockUser(user, blockUser);
    }

}
