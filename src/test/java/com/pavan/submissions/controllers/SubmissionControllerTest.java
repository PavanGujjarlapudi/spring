package com.pavan.submissions.controllers;

import com.pavan.submissions.entities.SubmissionDTO;
import com.pavan.submissions.interfaces.ISubmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SubmissionControllerTest {
    @Mock
    private ISubmissionService submissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get submission by id")
    void getById() {
        String id = "123";
        SubmissionDTO expectedResponse = new SubmissionDTO(id, "Santhosh", "2022-10-31", "Spandhana","tek systems", 42,"Aditya", "Java");
        Mockito.when(submissionService.getSubmissionById(id)).thenReturn(expectedResponse);
        SubmissionDTO actualResponse = submissionService.getSubmissionById(id);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse, actualResponse);
    }

}