package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchMapper searchMapper;

    public ResponseEntity getSearch(String pumName) {
        return ResponseEntity.status(200).body(searchMapper.findSearchByPumName(pumName));
    }
}
