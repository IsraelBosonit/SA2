package com.example.SA2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class FileUploadController {

    @Autowired
    StorageService storageService;

    @PostMapping("/upload/{tipo}")
    public void upload(@PathVariable String tipo, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
        String [] s=file.getOriginalFilename().split("\\.");
        String extension=s[s.length-1];
        if(extension.equals(tipo)){
            storageService.store(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");

        }else{
            throw new Exception("El fichero debe ser de extension "+tipo);
        }
    }
    @GetMapping("/setpath")
    public void setpath(@RequestParam(name="path", defaultValue = "C:\\Users\\israel.maranon\\Downloads\\SA2\\SA2") String path){
        storageService.setPath(path);
    }
}
