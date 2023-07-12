package com.pavan.submissions.services;

import com.pavan.submissions.interfaces.ISubmissionRepository;
import com.pavan.submissions.interfaces.ISubmissionService;
import com.pavan.submissions.entities.SubmissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
public class SubmissionService implements ISubmissionService {

    @Autowired
    private ISubmissionRepository repository;

    @Override
    public SubmissionDTO addSubmission(SubmissionDTO submission) {
        return repository.create(submission);
    }

    @Override
    public SubmissionDTO getSubmissionById(String id) {
        return repository.getById(id);
    }

    @Override
    public List<SubmissionDTO> getSubmissions(MultiValueMap<String, String> filters) {
        return repository.getFilteredSubmissions(filters);
    }

    @Override
    public SubmissionDTO modifySubmission(SubmissionDTO submission) {
        return repository.update(submission);
    }

    @Override
    public boolean delete(String id) {
       return repository.delete(id);
    }

}
