package ggoa.controller;

import ggoa.dao.CountriesRepository;
import ggoa.dao.StatesRepository;
import ggoa.enums.Designation;
import ggoa.enums.Salutation;
import ggoa.model.Country;
import ggoa.model.State;
import ggoa.util.PdfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

 @Controller
public class ResourcesController {
    @Autowired
    private StatesRepository statesRepository;
    @Autowired
    private CountriesRepository countriesRepository;

    @GetMapping("/states")
    public List<State> getAllStates() {
        return statesRepository.findAll();
    }

    @GetMapping("/designations")
    public List<Designation> getAllDesignations() {
        return Arrays.asList(Designation.values());
    }

    @GetMapping("/salutations")
    public List<Salutation> getAllSalutations() {
        return Arrays.asList(Salutation.values());

    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countriesRepository.findAll();
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getAsPdf() throws Exception {

        InputStream in = PdfUtil .getPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT");
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, HttpHeaders.CONTENT_TYPE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "filename=" + "adv.pdf");
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.EXPIRES, "0");
        headers.setContentLength(in.available());
        ResponseEntity<InputStreamResource> response = new ResponseEntity<>(
                new InputStreamResource(in), headers, HttpStatus.OK);
        in.close();

        return response;
    }
}
