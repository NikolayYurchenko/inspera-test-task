package com.inspera.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Describe structured update information of candidate
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateUpdateRecord {

    /**
     * List of edited candidates identifiers
     */
    private List<CandidateId> edited;

    /**
     * List of added candidates identifiers
     */
    private List<CandidateId> added;

    /**
     * List of removed candidates identifiers
     */
    private List<CandidateId> removed;

    public static CandidateUpdateRecord instance(List<Integer> edited, List<Integer> added, List<Integer> removed) {

        return CandidateUpdateRecord.builder()
                .edited(edited.stream().map(CandidateId::new).collect(Collectors.toList()))
                .added(added.stream().map(CandidateId::new).collect(Collectors.toList()))
                .removed(removed.stream().map(CandidateId::new).collect(Collectors.toList()))
                .build();
    }
}
