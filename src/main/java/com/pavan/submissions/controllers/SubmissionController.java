package com.pavan.submissions.controllers;

import com.pavan.submissions.entities.SubmissionResponse;
import com.pavan.submissions.entities.SubmissionResponseArray;
import com.pavan.submissions.interfaces.ISubmissionService;
import com.pavan.submissions.entities.SubmissionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {

    @Autowired
    private ISubmissionService service;
    private static final Logger logger = LoggerFactory.getLogger(SubmissionController.class);

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionResponse> getById(@PathVariable String id) {
        logger.info("GET request received for ID: {}", id);
        SubmissionDTO submission = service.getSubmissionById(id);
        if (submission != null) {
            logger.info("GET request successful for ID: {}", id);
            SubmissionResponse response = new SubmissionResponse(true,"",submission);
            return ResponseEntity.ok(response);
        } else {
            logger.info("GET request failed for ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<SubmissionResponseArray> getFilteredSubmissions(@RequestParam MultiValueMap<String, String> filters) {
        logger.info("GET request received with filters: {}", filters);
        List<SubmissionDTO> filteredSubmissions = service.getSubmissions(filters);
        SubmissionResponseArray response = new SubmissionResponseArray(true,"",filteredSubmissions);
        if (filteredSubmissions.isEmpty()) {
            logger.info("GET request returned no content with filters: {}", filters);
            return ResponseEntity.ok(response);
        }
        logger.info("GET request successful with filters: {}", filters);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SubmissionResponse> create(@RequestBody SubmissionDTO data) {
        logger.info("POST request received with data: {}", data);
        SubmissionDTO submission = service.addSubmission(data);
        if (submission != null) {
            logger.info("POST request successful with data: {}", data);
            SubmissionResponse response = new SubmissionResponse(true,"Submission Created",submission);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            logger.error("POST request failed with data: {}", data);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping()
    public ResponseEntity<SubmissionResponse> update(@RequestBody SubmissionDTO data) {
        logger.info("PUT request received with data: {}", data);
        SubmissionDTO submission = service.modifySubmission(data);
        if (submission != null) {
            logger.info("PUT request successful with data: {}", data);
            SubmissionResponse response = new SubmissionResponse(true,"Submission updated",submission);
            return ResponseEntity.ok(response);
        } else {
            logger.info("PUT request failed with data: {}", data);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.info("DELETE request received for ID: {}", id);
        boolean deleted = service.delete(id);
        if (deleted) {
            logger.info("DELETE request successful for ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.info("DELETE request failed for ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

}
