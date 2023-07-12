package com.pavan.submissions.repositories;

import com.pavan.submissions.entities.SubmissionDTO;
import com.pavan.submissions.interfaces.ISubmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class SubmissionRepository implements ISubmissionRepository {

    private static final Logger logger = LoggerFactory.getLogger(SubmissionRepository.class);
    private final List<SubmissionDTO> submissions = new ArrayList<>();

    @Override
    public SubmissionDTO getById(String id) {
        logger.info("Fetching submission by ID: {}", id);
        SubmissionDTO submission = findSubmissionById(id);
        if (submission != null) {
            logger.info("Submission found for ID: {}", id);
            return submission;
        } else {
            logger.info("Submission not found for ID: {}", id);
            return null;
        }
    }

    @Override
    public SubmissionDTO create(SubmissionDTO data) {
        logger.info("Creating new submission: {}", data);
        SubmissionDTO submission = createSubmission(data);
        logger.info("Submission created with ID: {}", submission.getId());
        return submission;
    }

    @Override
    public SubmissionDTO update(SubmissionDTO data) {
        logger.info("Updating submission: {}", data);
        SubmissionDTO submission = updateSubmission(data.getId(), data);
        if (submission != null) {
            logger.info("Submission updated for ID: {}", submission.getId());
            return submission;
        } else {
            logger.info("Submission update failed for ID: {}", data.getId());
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        logger.info("Deleting submission with ID: {}", id);
        return deleteSubmission(id);
    }

    @Override
    public List<SubmissionDTO> getFilteredSubmissions(MultiValueMap<String, String> filters) {
        logger.info("Filtering submissions with filters: {}", filters);

        // If no filters are specified, return all submissions
        if (filters.isEmpty()) {
            logger.info("No filters specified. Returning all submissions.");
            return new ArrayList<>(submissions);
        }

        List<SubmissionDTO> filteredSubmissions = new ArrayList<>();

        for (SubmissionDTO submission : submissions) {

            for (Map.Entry<String, List<String>> entry : filters.entrySet()) {
                String columnName = entry.getKey();
                List<String> filterValues = entry.getValue();

                // Get the value of the submission field based on the column name
                String submissionValue = getSubmissionValue(submission, columnName);

                // Check if the submission value matches any of the filter values
                if (filterValues.contains(submissionValue)) {
                    filteredSubmissions.add(submission);
                    break;
                }
            }
        }

        logger.info("Filtered submissions count: {}", filteredSubmissions.size());
        return filteredSubmissions;
    }

    private String getSubmissionValue(SubmissionDTO submission, String columnName) {
        return switch (columnName) {
            case "id" -> submission.getId();
            case "consultantName" -> submission.getConsultantName();
            case "submissionDate" -> submission.getSubmissionDate();
            case "leadName" -> submission.getLeadName();
            case "vendorName" -> submission.getVendorName();
            case "rate" -> String.valueOf(submission.getRate());
            case "salesPersonName" -> submission.getSalesPersonName();
            case "technology" -> submission.getTechnology();
            default -> null; // Or handle the case when the column name is not recognized
        };
    }


    private SubmissionDTO findSubmissionById(String id) {
        for (SubmissionDTO submission : submissions) {
            if (submission.getId().equals(id)) {
                return submission;
            }
        }
        return null;
    }

    private SubmissionDTO createSubmission(SubmissionDTO data) {
        data.setId(generateId());
        submissions.add(data);
        return data;
    }

    private SubmissionDTO updateSubmission(String id, SubmissionDTO newData) {
        SubmissionDTO submission = findSubmissionById(id);
        if (submission != null) {
            submission.setConsultantName(newData.getConsultantName());

            return submission;
        } else {
            return null;
        }
    }

    private boolean deleteSubmission(String id) {
        SubmissionDTO submission = findSubmissionById(id);
        if (submission != null) {
            submissions.remove(submission);
            logger.info("Submission deleted with ID: {}", id);
            return true;
        } else {
            logger.info("Submission not found for deletion with ID: {}", id);
            return false;
        }
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }


}
