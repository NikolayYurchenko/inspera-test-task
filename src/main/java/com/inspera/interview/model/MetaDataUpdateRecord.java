package com.inspera.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Describe structured update information of meta
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataUpdateRecord {

    /**
     * Name of updated field
     */
    private String field;

    /**
     * Value of field before update
     */
    private String before;

    /**
     * Value of field after update
     */
    private String after;

    public static MetaDataUpdateRecord instance(String field, String before, String after) {

        return MetaDataUpdateRecord.builder()
                .field(field)
                .before(before)
                .after(after)
                .build();
    }
}
