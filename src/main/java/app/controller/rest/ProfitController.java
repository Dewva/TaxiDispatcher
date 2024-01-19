package app.controller.rest;

import app.model.Profit;
import app.service.ProfitService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController

@RequestMapping("/profit")
public class ProfitController {


    private ProfitService classService = ServiceSinglePointAccess.getProfitService();
    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<Profit>> getAllUsers() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(classService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<Profit> getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<Profit> createUser(@RequestBody Profit entity) {
        return ResponseEntity.status(HttpStatus.OK).body(classService.save(entity));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateDate/{id}")
    public ResponseEntity<Profit> updateUserName(@PathVariable Integer id) {
        Profit entity = classService.findById(id);
        entity.setData(new Date(LocalDate.now().toEpochDay()));
        Profit entityUpdated = classService.update(entity);
        return ResponseEntity.status(HttpStatus.OK).body(entityUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Profit> update(@RequestBody Profit entity) {
        Profit entityFromDb = classService.findById(entity.getId());
        entityFromDb.setData(entity.getData());
        entityFromDb.setProfit(entity.getProfit());
        entityFromDb.setCheltuieli(entity.getCheltuieli());
        entityFromDb.setIncasare(entity.getIncasare());
        Profit entityUpdated = classService.update(entityFromDb);
        return ResponseEntity.status(HttpStatus.OK).body(entityUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Profit entity = classService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(classService.delete(entity));
    }

}
