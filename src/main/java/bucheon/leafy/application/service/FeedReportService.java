package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FeedReportRepository;
import bucheon.leafy.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class FeedReportService {

    private final UserRepository userRepository;
    private final FeedReportRepository feedReportRepository;

}
