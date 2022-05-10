package com.inspera.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describe the result data structure of parsed difference between incoming JSON objects
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultSet {

    /**
     * Meta data
     */
    private List<MetaDataUpdateRecord> meta;

    /**
     * Candidates info
     */
    private CandidateUpdateRecord candidates;

    public static ResultSet instance(List<MetaDataUpdateRecord> meta, CandidateUpdateRecord candidates) {

        return ResultSet.builder()
                .meta(meta)
                .candidates(candidates)
                .build();
    }
}
