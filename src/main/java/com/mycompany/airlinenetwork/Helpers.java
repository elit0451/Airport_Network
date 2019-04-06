package com.mycompany.airlinenetwork;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Helpers {

    public static <T> List<T> generateArrayFromCSV(Class classT, String path, Map mapping) throws FileNotFoundException, IOException{
       
        /*HeaderColumnNameTranslateMappingStrategy strategy =
 new HeaderColumnNameTranslateMappingStrategy();
        strategy.setType(classT);
        strategy.setColumnMapping(mapping);
        
        String csvFilename = path;
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
        CsvToBeanBuilder<classT> csv = new CsvToBeanBuilder<classT>();*/
        
        
        ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        ms.setType(classT);
  
        CSVReader csvReader = new CSVReader(new FileReader(path));
        
        CsvToBean cb = new CsvToBeanBuilder(csvReader)
                .withType(classT)
                .withMappingStrategy(ms)
                .withSeparator(';')
                .build();
        
        
        List<T> list = cb.parse();
        
        return list;
    }
}
