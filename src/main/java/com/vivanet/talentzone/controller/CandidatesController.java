package com.vivanet.talentzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String savecandidates(@RequestBody Candidates candidates)
    {
    	repository.save(candidates);
    	return "Added candidates with id:" + candidates.getId();
    }
    
    @GetMapping("/candidates")
    public List<Candidates>getcandidates(){
    	return repository.findAll();
    }
	public CandidatesRepository getRepository() {
	    return repository;
    }
	public void setRepository(CandidatesRepository repository) {
	    this.repository = repository;
    }
}
   
