package ggoa.controller;

import ggoa.exception.ResourceNotFoundException;
import ggoa.model.Transaction;
import ggoa.model.Villa;
import ggoa.dao.VillaRepository;
import ggoa.util.EmailUtil;
import ggoa.util.NumberFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class VillaController {

    @Autowired
    private VillaRepository villaRepository;

    @Autowired
    private EmailUtil emailUtil;

    @GetMapping("/villas")
    public List<Villa> getAlVillas() {

        List<Villa> villas = villaRepository.findAll();
        setBalance(villas);

        return villas;
    }

    private static void setBalance(List<Villa> villas) {

        villas.forEach(villa -> {
            if (!CollectionUtils.isEmpty(villa.getTransactions())) {
                villa.setAccountBalance(villa.getTransactions()
                        .stream().mapToLong(Transaction::getBalance).sum());
            } else {
                villa.setAccountBalance(0L);
            }
        });
    }

    @GetMapping("/villas/{id}")
    public Optional<Villa> getVillasById(@PathVariable(value = "id") String id) {
        Optional<Villa> optionalVilla = villaRepository.findById(id);
        Villa villa = null;
        if (optionalVilla.isPresent()) {
            villa = optionalVilla.get();
            villa.setAccountBalance(villa.getTransactions()
                    .stream().mapToLong(Transaction::getBalance).sum());
        }
        return optionalVilla;
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

    @PostMapping("/txn")
    public ResponseEntity<?> addTransaction(@Valid @RequestBody Transaction txn) {

        Optional<Villa> optionalVilla = villaRepository.findById(txn.getId());
        if (optionalVilla.isPresent()) {
            Villa villa = optionalVilla.get();
            if (CollectionUtils.isEmpty(villa.getTransactions())) {
                villa.setTransactions(new ArrayList<>());
            }
            txn.setId(null);
            txn.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))); //.toString());
            villa.getTransactions().add(txn);
            villaRepository.save(villa);
            try {
                emailUtil.sendEmail(villa,txn);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }


        return ResponseEntity.ok().build();

    }
}
