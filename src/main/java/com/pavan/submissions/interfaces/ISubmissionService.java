package com.pavan.submissions.interfaces;

import com.pavan.submissions.entities.SubmissionDTO;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface ISubmissionService {
    SubmissionDTO addSubmission(SubmissionDTO submission);
    SubmissionDTO getSubmissionById(String id);
    List<SubmissionDTO> getSubmissions(MultiValueMap<String, String> filters);
    SubmissionDTO modifySubmission(SubmissionDTO submission);
    boolean delete(String id);
}
