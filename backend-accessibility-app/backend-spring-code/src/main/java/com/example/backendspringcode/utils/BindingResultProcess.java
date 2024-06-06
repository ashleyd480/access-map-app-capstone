package com.example.backendspringcode.utils;

import com.example.backendspringcode.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;


public class BindingResultProcess {

   public static ResponseEntity<?> getBindingResponseEntity(BindingResult result) {
        Map<String, String> errorMap = new HashMap<>();
        StringBuilder errors = new StringBuilder();

        result.getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
            errors.append(error.getDefaultMessage()).append(", ");
        });
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setError(errors.toString());
        responseDTO.setHasError(true);
        return ResponseEntity.badRequest().body(responseDTO);
    }
}

// BindingResult is an interface provided by the Spring Framework that holds the results of a binding and validation process for a form or web request. I put this method in Utils to keep it dry since I call it a few times.
// we put the key value pairs into a hashmap and then call the getter so that is how my error is generated

//To briefly explain the code logic (and kudos to my instructor for help on this):
// 1. We initialize a hashmap called `errorMap` to store the fields and their corresponding error messages.
//2. StringBuilder is used to build a comma separated string of all error messages. It iterates over all the `FieldErrors` in the binding result, and for each one: a key-value pair is created with the field (i.e. username) and error message(s) - i.e.  “Username is mandatory.”
//  3. These key-value pairs are added into that `errorMap`, with `errorMap.put`
// 4. It also appends the default error message to the errors StringBuilder, followed by a comma and a space, with `errors.append`
//5. Once done iterating over all those errors, a new instance of `ResponseDTO` is created.
//6. The setter is used to set the `error` attribute of the `ResponseDTO` instance to the string version of the `errors` which we created with StringBuilder.
//7. It sets the hasError property of the ResponseDTO to true, indicating that there were validation errors.
//8. Finally, it returns a ResponseEntity with an HTTP status code of 400 Bad Request and the `ResponseDTO` instance as the response body.

