package app.controller.rest;

import app.dto.SoferActiviDTO;
import app.model.SoferActivi;
import app.service.SoferActiviService;
import app.service.SoferActiviService;
import app.single_point_access.ServiceSinglePointAccess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController

@RequestMapping("/sofer")
public class SoferActiviController {

    private SoferActiviService classService = ServiceSinglePointAccess.getSoferActiviService();
    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<SoferActivi>> getAllUsers() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(classService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<SoferActivi> getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<SoferActivi> createUser(@RequestBody SoferActivi entity) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.save(entity));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateStatus/{id}/{name}")
    public ResponseEntity<SoferActivi> updateUserName(@PathVariable Integer id, @PathVariable Boolean name) {
        SoferActivi entity = classService.findById(id);
        entity.setInCursa(name);
        SoferActivi entityUpdated = classService.update(entity);
        return ResponseEntity.status(HttpStatus.OK).body(entityUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<SoferActivi> update(@RequestBody SoferActivi entity) {
        SoferActivi entityFromDb = classService.findById(entity.getId());
        entityFromDb.setInCursa(entity.getInCursa());
        entityFromDb.setSofer(entity.getSofer());
        entityFromDb.setProfit(entity.getProfit());
        entityFromDb.setNrCurse(entity.getNrCurse());
        SoferActivi entityUpdated = classService.update(entityFromDb);
        return ResponseEntity.status(HttpStatus.OK).body(entityUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        SoferActivi entity = classService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(classService.delete(entity));
    }

    @Operation(summary = "Get details (name, address, phone) from all users")
    @GetMapping("/details_all")
    public ResponseEntity<List<SoferActiviDTO>> getAllUserDetails() {

        List<SoferActivi> entities = classService.findAll();
        List<SoferActiviDTO> entityDTOS = new ArrayList<>();

        for (SoferActivi x : entities) {
            SoferActiviDTO eDTO = new SoferActiviDTO();
            eDTO.setSofer(x.getSofer());
            eDTO.setInCursa(x.getInCursa());
            eDTO.setProfit(x.getProfit());
            eDTO.setNrCurse(x.getNrCurse());

            entityDTOS.add(eDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(entityDTOS);
    }
}
