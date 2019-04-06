package com.mycompany.airlinenetwork;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Helpers {

    public static <T> List<T> generateArrayFromCSV(Class<T> classT, String path, Map mapping) throws FileNotFoundException, IOException{
       
        HeaderColumnNameTranslateMappingStrategy strategy =
 new HeaderColumnNameTranslateMappingStrategy();
        strategy.setType(classT);
        strategy.setColumnMapping(mapping);
        
        String csvFilename = path;
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
        CsvToBean csv = new CsvToBean();
        
        
        List<T> list = csv.parse(strategy, csvReader);
        
        return list;
    }
}
