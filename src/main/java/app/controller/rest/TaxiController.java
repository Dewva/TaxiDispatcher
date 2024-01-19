package app.controller.rest;

import app.dto.TaxiDTO;
import app.model.Taxi;
import app.service.TaxiService;
import app.service.TaxiService;
import app.single_point_access.ServiceSinglePointAccess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/taxi")
public class TaxiController {

    private TaxiService classService = ServiceSinglePointAccess.getTaxiService();
    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<Taxi>> getAllUsers() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(classService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<Taxi> getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<Taxi> createUser(@RequestBody Taxi entity) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.save(entity));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateStare/{id}/{name}")
    public ResponseEntity<Taxi> updateUserName(@PathVariable Integer id, @PathVariable String name) {
        Taxi entity = classService.findById(id);
        entity.setStare(name);
        Taxi entityUpdated = classService.update(entity);
        return ResponseEntity.status(HttpStatus.OK).body(entityUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Taxi> update(@RequestBody Taxi entity) {
        Taxi entityFromDb = classService.findById(entity.getId());

        entityFromDb.setStare(entity.getStare());
        entityFromDb.setNrInmatriculare(entity.getNrInmatriculare());

        Taxi entityUpdated = classService.update(entityFromDb);
        return ResponseEntity.status(HttpStatus.OK).body(entityUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Taxi entity = classService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(classService.delete(entity));
    }

    @Operation(summary = "Get details (name, address, phone) from all users")
    @GetMapping("/details_all")
    public ResponseEntity<List<TaxiDTO>> getAllUserDetails() {

        List<Taxi> entities = classService.findAll();
        List<TaxiDTO> entityDTOS = new ArrayList<>();

        for (Taxi x : entities) {
            TaxiDTO eDTO = new TaxiDTO();
           eDTO.setNrInmatriculare(x.getNrInmatriculare());
           eDTO.setStare(x.getStare());

            entityDTOS.add(eDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(entityDTOS);
    }
}
