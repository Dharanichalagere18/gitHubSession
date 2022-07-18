package com.vivanet.talentzone.services;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.vivanet.talentzone.model.Candidates;
import org.apache.commons.io.IOUtils;

@Service
public class FileService {

    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFsOperations operations;

    public String addFile(MultipartFile upload) throws IOException {

        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", upload.getSize());
        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(),
                metadata);
        return fileID.toString();
    }

    public Candidates downloadFile(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));

        Candidates candidates = new Candidates();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            candidates.setResumename(gridFSFile.getFilename());

            candidates.setFileType(gridFSFile.getMetadata().get("_contentType").toString());

            candidates.setFileSize(gridFSFile.getMetadata().get("fileSize").toString());

            candidates.setResume(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
        }
        return candidates;
    }
}
