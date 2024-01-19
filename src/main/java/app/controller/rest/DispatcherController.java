package app.controller.rest;

import app.dto.DispatcherDTO;
import app.model.Dispatcher;
import app.service.DispatcherService;
import app.single_point_access.ServiceSinglePointAccess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/dispatcher")
public class DispatcherController {


    private DispatcherService classService = ServiceSinglePointAccess.getDispatcherService();
    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<Dispatcher>> getAllUsers() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(classService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<Dispatcher> getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<Dispatcher> createUser(@RequestBody Dispatcher entity) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.save(entity));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateName/{id}/{name}")
    public ResponseEntity<Dispatcher> updateUserName(@PathVariable Integer id, @PathVariable String name) {
        Dispatcher entity = classService.findById(id);
        entity.setName(name);
        Dispatcher entityUpdated = classService.update(entity);
        return ResponseEntity.status(HttpStatus.OK).body(entityUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Dispatcher> update(@RequestBody Dispatcher entity) {
        Dispatcher entityFromDb = classService.findById(entity.getId());
        entityFromDb.setName(entity.getName());
        entityFromDb.setPhone(entity.getPhone());
        entityFromDb.setAddress(entity.getAddress());
        entityFromDb.setSalary(entity.getSalary());
        entityFromDb.setInApel(entity.isInApel());
        Dispatcher entityUpdated = classService.update(entityFromDb);
        return ResponseEntity.status(HttpStatus.OK).body(entityUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Dispatcher entity = classService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(classService.delete(entity));
    }

    @Operation(summary = "Get details (name, address, phone) from all users")
    @GetMapping("/details_all")
    public ResponseEntity<List<DispatcherDTO>> getAllUserDetails() {

        List<Dispatcher> entities = classService.findAll();
        List<DispatcherDTO> entityDTOS = new ArrayList<>();

        for (Dispatcher x : entities) {
            DispatcherDTO eDTO = new DispatcherDTO();
            eDTO.setAddress(x.getAddress());
            eDTO.setPhone(x.getPhone());
            eDTO.setName(x.getName());
            eDTO.setSalary(x.getSalary());

            entityDTOS.add(eDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(entityDTOS);
    }
}
