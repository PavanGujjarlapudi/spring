package com.pavan.submissions.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponseArray {
    private boolean success;
    private String message;
    private List<SubmissionDTO> data;
}
