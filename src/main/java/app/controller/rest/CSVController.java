package app.controller.rest;

import app.model.Profit;
import app.model.Taxi;
import app.service.ProfitService;
import app.service.TaxiService;
import app.single_point_access.ServiceSinglePointAccess;
import app.util.FileUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// All imported/exported files are taken form resources directory ONLY
@RestController
@RequestMapping("/csv")
public class CSVController {

    private TaxiService taxiService = ServiceSinglePointAccess.getTaxiService();
    private ProfitService profitService = ServiceSinglePointAccess.getProfitService();

    // TO DO
    // For project take in consideration that a csv file could have different order of columns
    // Do it for at least 2 entities - import and export
    // Take in consideration data validation or if some data already exists
    // Extract duplicate logic and improve it based on template below
    //
    // For demo - import at least 25 entities and export all entities
    //
    @PostMapping("/import_taxi")
    public ResponseEntity<Boolean> importTaxiFromCSV(@RequestBody String filename) {
        try {
            File file = FileUtil.getAndCreateFileFromResourcesDirectory(filename);

            // Read data in a buffer
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<Taxi> taxis = new ArrayList<>();
            Map<String, Integer> columnOrder = new HashMap<>();
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (firstLine) {
                    firstLine = false;

                    for(int i = 0; i < values.length; i ++){
                        values[i] = values[i].toLowerCase();
                        columnOrder.put(values[i],i);
                    }
                    continue;
                }

                Taxi taxi = new Taxi();

                //taxi.setId(Integer.parseInt(values[columnOrder.get("id")]));
                taxi.setNrInmatriculare(values[columnOrder.get("nrinmatriculare")]);
                taxi.setStare(values[columnOrder.get("stare")]);

                taxis.add(taxi);
            }

            for (Taxi taxiIt : taxis) {
                if(taxiService.findByName(taxiIt.getNrInmatriculare()) == null)
                    taxiService.save(taxiIt);
            }

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

            // TO DO - treat exception case
        } catch (IOException e) {

            // TO DO - treat exception case
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {

            // TO DO - treat exception case
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/import_profit")
    public ResponseEntity<Boolean> importProfitFromCSV(@RequestBody String filename) {
        try {
            File file = FileUtil.getAndCreateFileFromResourcesDirectory(filename);

            // Read data in a buffer
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<Profit> profits = new ArrayList<>();
            Map<String, Integer> columnOrder = new HashMap<>();
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (firstLine) {
                    firstLine = false;

                    for(int i = 0; i < values.length; i ++){
                        values[i] = values[i].toLowerCase();
                        columnOrder.put(values[i],i);
                    }
                    continue;
                }

                Profit profit = new Profit();

                profit.setIncasare(Integer.valueOf(values[columnOrder.get("incasare")]));
                profit.setData(Date.valueOf(values[columnOrder.get("data")]));
                profit.setProfit(Integer.valueOf(values[columnOrder.get("profit")]));
                profit.setCheltuieli(Integer.valueOf(values[columnOrder.get("cheltuieli")]));
                profits.add(profit);

            }

            for (Profit profitIt : profits) {
                if(profitService.findByDate(profitIt.getData()) == null)
                    profitService.save(profitIt);
            }

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

            // TO DO - treat exception case
        } catch (IOException e) {

            // TO DO - treat exception case
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {

            // TO DO - treat exception case
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/export_taxi")
    public ResponseEntity<Boolean> exportTaxiToCSV(@RequestBody String filename) {

        try {
            File file = FileUtil.getAndCreateFileFromResourcesDirectory(filename);
            FileWriter fileWriter = new FileWriter(file);
            String header = "id,nrInmatriculare,stare\n";
            fileWriter.write(header);


            List<Taxi> taxis = taxiService.findAll();
            for (Taxi taxiIt : taxis) {
                String taxiDetails = taxiIt.getId().toString() + "," + taxiIt.getNrInmatriculare() + "," + taxiIt.getStare() + "\n";
                fileWriter.write(taxiDetails);
            }

            fileWriter.close();

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (IOException ex) {

            // TO DO - treat exception case

            throw new RuntimeException(ex);
        } catch (URISyntaxException e) {

            // TO DO - treat exception case

            throw new RuntimeException(e);
        }
    }
    @PostMapping("/export_profit")
    public ResponseEntity<Boolean> exportProfitToCSV(@RequestBody String filename) {

        try {
            File file = FileUtil.getAndCreateFileFromResourcesDirectory(filename);
            FileWriter fileWriter = new FileWriter(file);
            String header = "id,data,incasare,cheltuieli,profit\n";
            fileWriter.write(header);


            List<Profit> profits = profitService.findAll();
            for (Profit profitIt : profits) {
                String profitDetails = profitIt.getId() + "," + profitIt.getData() + "," + profitIt.getIncasare() + "," + profitIt.getCheltuieli() + "," + profitIt.getProfit() + "\n";
                fileWriter.write(profitDetails);
            }

            fileWriter.close();

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (IOException ex) {

            // TO DO - treat exception case

            throw new RuntimeException(ex);
        } catch (URISyntaxException e) {

            // TO DO - treat exception case

            throw new RuntimeException(e);
        }
    }
}
