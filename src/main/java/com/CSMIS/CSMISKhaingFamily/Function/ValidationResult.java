package com.CSMIS.CSMISKhaingFamily.Function;

import java.util.*;

public class ValidationResult {
private List<String> errors;
    
    public ValidationResult() {
        errors = new ArrayList<>();
    }
    
    public void addError(String error) {
        errors.add(error);
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    public List<String> getErrors() {
        return errors;
    }
}
