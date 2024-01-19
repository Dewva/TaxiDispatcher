package app.controller.rest;

import app.service.performance.AppPerformanceService;
import app.service.performance.SQLProcedurePerformanceService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    private AppPerformanceService appPerformanceService = ServiceSinglePointAccess.getAppPerformanceService();
    private SQLProcedurePerformanceService sqlProcedurePerformanceService = ServiceSinglePointAccess.getSQLProcedurePerformanceService();

    @PutMapping("/java_logic")
    public ResponseEntity<Long> applyJavaLogic() {
        long timeStart = System.nanoTime();
        appPerformanceService.applyLogicOnUsers(Date.valueOf(LocalDate.now()));
        long timeEnd = System.nanoTime();

        return ResponseEntity.status(HttpStatus.OK).body(timeEnd - timeStart);
    }

    @GetMapping("/sql_logic")
    public ResponseEntity<Long> applySQLLogic() {
        long timeStart = System.nanoTime();
        sqlProcedurePerformanceService.applyLogicOnUsers(Date.valueOf(LocalDate.now()));
        long timeEnd = System.nanoTime();

        return ResponseEntity.status(HttpStatus.OK).body(timeEnd - timeStart);
    }
}
