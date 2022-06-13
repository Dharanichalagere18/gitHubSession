package com.vivanet.talentzone.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vivanet.talentzone.model.Candidates;

public interface CandidatesRepository extends MongoRepository<Candidates,ObjectId> {
}
