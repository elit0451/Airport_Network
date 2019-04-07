package com.mycompany.airlinenetwork;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class Helpers {

    public static <T> List<T> generateArrayFromCSV(Class classT, String path, String[] mapping) throws FileNotFoundException, IOException{
        ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        ms.setType(classT);
        ms.setColumnMapping(mapping);
  
        CSVReader csvReader = new CSVReader(new FileReader(path),';','\"',1);
        
        CsvToBean cb = new CsvToBeanBuilder(csvReader)
                .withType(classT)
                .withMappingStrategy(ms)
                .build();
        
        List<T> list = cb.parse();
        
        return list;
    }
}
