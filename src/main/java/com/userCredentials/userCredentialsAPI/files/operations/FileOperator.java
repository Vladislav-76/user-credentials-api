package com.userCredentials.userCredentialsAPI.files.operations;

import com.userCredentials.userCredentialsAPI.models.User;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class FileOperator {
    private final static String PATH = "src/main/resources";
    private final static String FILE_NAME = "query_results";
    private final static String[] FORMAT = new String[]{".txt", ".csv"};
    public void queryUserWriteToFile(User user) {

    }
}
