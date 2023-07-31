package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FeedBlockRepository;
import bucheon.leafy.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class FeedBlockService {

    private final UserRepository userRepository;
    private final FeedBlockRepository feedBlockRepository;

}
