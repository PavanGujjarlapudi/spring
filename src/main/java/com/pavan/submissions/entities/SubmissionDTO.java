package com.pavan.submissions.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDTO {
    private String id;
    private String consultantName;
    private String submissionDate;
    private String leadName;
    private String vendorName;
    private Integer rate;
    private String salesPersonName;
    private String technology;
}

