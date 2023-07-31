package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserBlockRepository;
import bucheon.leafy.application.repository.UserReportRepository;
import bucheon.leafy.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserReportService {

    private final UserRepository userRepository;
    private final UserReportRepository userReportRepository;

}
