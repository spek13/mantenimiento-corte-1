/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.owner;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
/**
 *
 * @author SPEK1
 */


@Controller
@RequestMapping("/album")
public class ImageController {
    private String upload_folder = ".//src//main//resources//files//";
    
    private final PetRepository pets;
    private final ImageRepository images;
    
    public ImageController(PetRepository pets,ImageRepository images) {
        this.pets = pets;
        this.images = images;
    }
    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @ModelAttribute("image")
    public Image loadPetWithVisit(@PathVariable("petId") int petId, Map<String, Object> model) {
        Pet pet = this.pets.findById(petId);
        model.put("pet", pet);
        Image image = new Image();
        pet.addImage(image);
        return image;
    }
    
    @GetMapping("/{petId}")
    public ModelAndView showOwner(@PathVariable("petId") int petId) {
        ModelAndView mav = new ModelAndView("pets/AlbumList");
        mav.addObject(this.pets.findById(petId));
        return mav;
    }
    
     @PostMapping("/upload/{petId}")
    public String uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("petId") String petIdInt  ,RedirectAttributes redirectAttributes) {
       int petId = Integer.parseInt(petIdInt);
        
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(upload_folder +"//"+Integer.toString(petId)+"//"+ file.getOriginalFilename());
//            Files.write(path, bytes);
//            Pet pet = this.pets.findById(petId);
            
            Image image = new Image();
            image.setPetId(petId);
            image.setUrl("images/"+ file.getOriginalFilename());
//            pet.addImage(image);
            this.images.save(image);

//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        return "redirect:/album/" + Integer.toString(petId);

        } catch (Exception e) {
            e.printStackTrace();
            
            return "redirect:/album/error";
        }

    }
    
}
