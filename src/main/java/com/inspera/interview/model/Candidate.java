package com.inspera.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describe candidate data structure
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {

    /**
     * Id of candidate
     */
    private int id;

    /**
     * Name of candidate
     */
    private String candidateName;

    /**
     * Extra time
     */
    private int extraTime;
}
