package app.controller.rest;

import app.dto.AngajatDTO;
import app.model.Angajat;
import app.service.AngajatService;
import app.single_point_access.ServiceSinglePointAccess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/angajat")
public class AngajatController {


    private AngajatService classService = ServiceSinglePointAccess.getAngajatService();
    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<Angajat>> getAllUsers() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(classService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<Angajat> getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<Angajat> createUser(@RequestBody Angajat entity) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.save(entity));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateName/{id}/{name}")
    public ResponseEntity<Angajat> updateUserName(@PathVariable Integer id, @PathVariable String name) {
        Angajat entity = classService.findById(id);
        entity.setName(name);
        Angajat entityUpdated = classService.update(entity);
        return ResponseEntity.status(HttpStatus.OK).body(entityUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Angajat> update(@RequestBody Angajat entity) {
        Angajat entityFromDb = classService.findById(entity.getId());
        entityFromDb.setName(entity.getName());
        entityFromDb.setPhone(entity.getPhone());
        entityFromDb.setAddress(entity.getAddress());
        entityFromDb.setTaxiId(entity.getTaxiId());
        entityFromDb.setSalary(entity.getSalary());
        entityFromDb.setTimeOfWork(entity.isTimeOfWork());
        Angajat entityUpdated = classService.update(entityFromDb);
        return ResponseEntity.status(HttpStatus.OK).body(entityUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Angajat entity = classService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(classService.delete(entity));
    }

    @Operation(summary = "Get details (name, address, phone) from all users")
    @GetMapping("/details_all")
    public ResponseEntity<List<AngajatDTO>> getAllUserDetails() {

        List<Angajat> entities = classService.findAll();
        List<AngajatDTO> entityDTOS = new ArrayList<>();

        for (Angajat x : entities) {
            AngajatDTO eDTO = new AngajatDTO();
            eDTO.setAddress(x.getAddress());
            eDTO.setPhone(x.getPhone());
            eDTO.setName(x.getName());
            eDTO.setSalary(x.getSalary());
            eDTO.setTaxi(x.getTaxiId());
            eDTO.setTimeOfWork(x.isTimeOfWork());

            entityDTOS.add(eDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(entityDTOS);
    }
}
