/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.owner;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author SPEK1
 */
@Service
public class UploadFileService {

    private String upload_folder = ".//src//main//resources//files//";

    public String saveFile(MultipartFile file, Integer id) throws IOException {
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            Path path = Paths.get(upload_folder + "//"+ id + "//" + file.getOriginalFilename());
            Files.write(path,bytes);
        }
        return upload_folder + "//"+ id + "//" + file.getOriginalFilename();
    }
}
