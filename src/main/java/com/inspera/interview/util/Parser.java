package com.inspera.interview.util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspera.interview.model.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import org.json.simple.JSONObject;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class Parser {

    private ObjectMapper mapper;

    /**
     * Parse the difference between given json objects
     * make result json object with providing information about the updated data
     * @param before
     * @param after
     * @return
     * @throws IOException
     */
    @SneakyThrows
    @SuppressWarnings("all")
    public JSONObject parse(JSONObject before, JSONObject after) {

        MetaData originalMetaFields = mapper.convertValue(before.get(Constants.metaTitle), MetaData.class);

        MetaData updatedMetaFields = mapper.convertValue(after.get(Constants.metaTitle), MetaData.class);

        List<Candidate> originalCandidates = Arrays.asList(mapper.convertValue(before.get(Constants.candidatesTitle), Candidate[].class));

        List<Candidate> updatedCandidates = Arrays.asList(mapper.convertValue(after.get(Constants.candidatesTitle), Candidate[].class));

        List<MetaDataUpdateRecord> metaChanges = this.processMetaChanges(originalMetaFields, updatedMetaFields);

        CandidateUpdateRecord candidatesUpdateInfo = this.processCandidatesChanges(originalCandidates, updatedCandidates);

        return mapper.convertValue(ResultSet.instance(metaChanges, candidatesUpdateInfo), JSONObject.class);
    }

    private List<MetaDataUpdateRecord> processMetaChanges(MetaData beforeUpdate, MetaData afterUpdate) {

        List<MetaDataUpdateRecord> updatedFields = new ArrayList<>();

        if(!beforeUpdate.getTitle().equals(afterUpdate.getTitle())) {

            updatedFields.add(MetaDataUpdateRecord.instance(Constants.title, beforeUpdate.getTitle(), afterUpdate.getTitle()));
        }

        if(!beforeUpdate.getStartTime().equals(afterUpdate.getStartTime())) {

            updatedFields.add(MetaDataUpdateRecord.instance(Constants.startTimeTitle, beforeUpdate.getStartTime(), afterUpdate.getStartTime()));
        }

        if(!beforeUpdate.getEndTime().equals(afterUpdate.getEndTime())) {

            updatedFields.add(MetaDataUpdateRecord.instance(Constants.endTimeTitle, beforeUpdate.getEndTime(), afterUpdate.getEndTime()));
        }

        return updatedFields;
    }

    private CandidateUpdateRecord processCandidatesChanges(List<Candidate> originalCandidates,
                                                           List<Candidate> updatedCandidates) {

        List<Integer> editedCandidatesIds = new ArrayList<>();
        List<Integer> addedCandidatesIds = new ArrayList<>();
        List<Integer> removedCandidatesIds = new ArrayList<>();

        originalCandidates.forEach(candidate -> {

            Optional<Candidate> updatedCandidate = this.getCandidateFromList(candidate.getId(), updatedCandidates);

            updatedCandidate.ifPresent(updatedCandidateItem -> {

                if(!updatedCandidateItem.getCandidateName().equals(candidate.getCandidateName()) || updatedCandidateItem.getExtraTime() != candidate.getExtraTime()) {

                    editedCandidatesIds.add(candidate.getId());
                }
            });
        });

        List<Integer> originalCandidatesIds = originalCandidates.stream()
                .map(Candidate::getId)
                .collect(Collectors.toList());

        List<Integer> updatedCandidatesIds = updatedCandidates.stream()
                .map(Candidate::getId)
                .collect(Collectors.toList());

        originalCandidatesIds.forEach(originalCandidateId -> {

            if(!updatedCandidatesIds.contains(originalCandidateId)) {
                removedCandidatesIds.add(originalCandidateId);
            }
        });

        updatedCandidatesIds.forEach(updatedCandidateId -> {

            if(!originalCandidatesIds.contains(updatedCandidateId)) {
                addedCandidatesIds.add(updatedCandidateId);
            }
        });

        return CandidateUpdateRecord.instance(editedCandidatesIds, addedCandidatesIds, removedCandidatesIds);
    }

    private Optional<Candidate> getCandidateFromList(Integer id, List<Candidate> candidates) {

        return candidates.stream()
                .filter(candidate -> candidate.getId() == id)
                .findFirst();
    }
}
