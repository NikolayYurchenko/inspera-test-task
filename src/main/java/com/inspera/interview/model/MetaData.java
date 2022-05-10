package com.inspera.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describe meta data structure
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaData {

    /**
     * Title
     */
    private String title;

    /**
     * Start time
     */
    private String startTime;

    /**
     * End time
     */
    private String endTime;
}
