package com.pavan.submissions.interfaces;
import com.pavan.submissions.entities.SubmissionDTO;
import com.pavan.submissions.entities.SubmissionResponse;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface ISubmissionRepository {
   SubmissionDTO getById(String id);
   SubmissionDTO create(SubmissionDTO data);
   SubmissionDTO update(SubmissionDTO data);
   boolean delete(String id);
   List<SubmissionDTO> getFilteredSubmissions(MultiValueMap<String, String> filters);
}
