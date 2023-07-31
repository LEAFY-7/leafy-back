package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserBlockRepository;
import bucheon.leafy.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserBlockService {

    private final UserRepository userRepository;
    private final UserBlockRepository userBlockRepository;

}
