package br.com.domain.agency.resource;

import br.com.domain.agency.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@RequestMapping("/file")
public class FileResource {

    // define a location
    public static final String DIRECTORY = System.getProperty("user.home") + ("/Downloads/");

    private AgentService agentService = new AgentService();

    // define a method to upload files
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            try {
                copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//                agentService.parseXmlFile(DIRECTORY + "/" + filename);
//                agentService.parseJacksonXmlFile(DIRECTORY + "/" + filename);
//                agentService.parserXmlFile(DIRECTORY + filename);
//                agentService.parserSaxFile(DIRECTORY + "/" + filename);
                agentService.unmarshalXml(DIRECTORY + filename);
            } catch (NoSuchFileException e) {
                System.out.println("No Such File Exception: " + e.getMessage());
                ResponseEntity.badRequest().body("Error - No Such File");
            }
            filenames.add(filename);
        }
        return ResponseEntity.ok().body(filenames);
    }

    // define a method to download files (not implement)
}
