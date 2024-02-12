package com.completablefuture.database;

import com.completablefuture.dto.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EmployeeDatabase {

    public static List<Employee> fetchEmployees(){

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File("/Users/abgwal/Projects/java8/src/main/java/com/resources/data/employees.json"), new TypeReference<List<Employee>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
