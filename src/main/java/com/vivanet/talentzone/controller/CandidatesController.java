package com.vivanet.talentzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vivanet.talentzone.model.Candidates;
import com.vivanet.talentzone.repository.CandidatesRepository;


@RestController
public class CandidatesController {

	@Autowired
    private CandidatesRepository repository;
    
    @PostMapping("/candidates")
    public ResponseEntity insertcandidates(@RequestBody Candidates candidates)
    {
    	repository.insert(candidates);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/candidates")
     public ResponseEntity<List<Candidates>>getcandidates(){
    	return new ResponseEntity<>(repository.findAll(),(HttpStatus.OK));
    }
	public CandidatesRepository getRepository() {
	    return repository;
    }
	public void setRepository(CandidatesRepository repository) {
	    this.repository = repository;
    }
}
   
