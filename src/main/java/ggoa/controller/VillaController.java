package ggoa.controller;

import ggoa.exception.ResourceNotFoundException;
import ggoa.model.Villa;
import ggoa.dao.VillaRepository;
import ggoa.util.NumberFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class VillaController {

    @Autowired
    private VillaRepository villaRepository;

    @GetMapping("/villas")
    public List<Villa> getAlVillas() {

        return villaRepository.findAll();
    }
    @GetMapping("/villas/{id}")
    public Optional<Villa> getVillasById(@PathVariable(value = "id") String id) {
        return villaRepository.findById(id);

    }
    @PostMapping("/villas")
    public Villa createVillas(@Valid @RequestBody Villa villa) {
        return villaRepository.save(villa);
    }

    @PutMapping("/villas/{id}")
    public Villa updateEmployee(@PathVariable(value = "id") String id,
                                @Valid @RequestBody Villa updatedVilla) {

        Villa villa = villaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("villas", "id", id));

        updatedVilla.setId(villa.getId());


        return villaRepository.save(updatedVilla);

    }

    @DeleteMapping("/villas/{id}")
    public ResponseEntity<?> deleteEmployees(@PathVariable(value = "id") String id) {
        Villa villa = villaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("villa", "id", id));

        villaRepository.delete(villa);

        return ResponseEntity.ok().build();
    }
}
