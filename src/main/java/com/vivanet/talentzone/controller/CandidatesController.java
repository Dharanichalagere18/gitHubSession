package com.vivanet.talentzone.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivanet.talentzone.model.Candidates;
import com.vivanet.talentzone.repository.CandidatesRepository;
import java.io.IOException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import com.vivanet.talentzone.services.FileService;

@RestController
public class CandidatesController {

    @Autowired
    private CandidatesRepository repository;

    @Autowired
    private FileService fileService;

    @PutMapping("/candidates/shortlist/{id}")
    public ResponseEntity shortlistCandidate(@PathVariable String id) {
    Optional<Candidates> candidate = repository.findById(id);

    if(candidate.isPresent())
    {
        candidate.get().setShortlisted(true);
        repository.save(candidate.get());
    }
    return new ResponseEntity<>(HttpStatus.OK);
}
    @GetMapping("/candidates/{id}")
    public ResponseEntity<Object> getCandidates() {
        if(Candidates.isPresent()) {
            return ResponseEntity.ok().body(Candidates.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @RequestMapping(path  = "/candidates", method = RequestMethod.POST ,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> insertcandidates(@RequestPart String candidates,@RequestParam("resume")MultipartFile resume)throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Candidates candidate = mapper.readValue(candidates, Candidates.class);
        String fileID = fileService.addFile(resume);
        candidate.setResumename(fileID);
        repository.insert(candidate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

 
    // @GetMapping("/candidates/{id}")
    // public ResponseEntity<List<Candidates>> getcandidates() {
    //     return new ResponseEntity<>(repository.findAll(), (HttpStatus.OK));
    // }

    public CandidatesRepository getRepository() {
        return repository;
    }

    public void setRepository(CandidatesRepository repository) {
        this.repository = repository;
    }

    // @Autowired
    // private FileService fileService;
    // @PostMapping("/upload")
    // public ResponseEntity<?> upload(@RequestParam("resume")MultipartFile resume)
    // throws IOException {
    // return new ResponseEntity<>(fileService.addFile(resume), HttpStatus.OK);
    // }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        Candidates loadFile = fileService.downloadFile(id);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(loadFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getResumename() + "\"")
                .body(new ByteArrayResource(loadFile.getResume()));
    }
}
