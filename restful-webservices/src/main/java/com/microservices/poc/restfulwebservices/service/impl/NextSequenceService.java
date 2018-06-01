package com.microservices.poc.restfulwebservices.service.impl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.microservices.poc.restfulwebservices.entity.CustomSequences;
import com.microservices.poc.restfulwebservices.service.entity.CustomSequencesPost;

@Service
public class NextSequenceService {
	@Autowired
	private MongoOperations mongo;

	public int getNextSequence(String seqName) {
		CustomSequences counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("seq", 1),
				options().returnNew(true).upsert(true), CustomSequences.class);
		return counter.getSeq();
	}
	
	public int getNextSequencePost(String seqName) {
		CustomSequencesPost counter = mongo.findAndModify(query(where("_id").is(seqName)), new Update().inc("seq", 1),
				options().returnNew(true).upsert(true), CustomSequencesPost.class);
		return counter.getSeq();
	}

}
