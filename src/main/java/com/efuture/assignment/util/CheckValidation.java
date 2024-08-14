package com.efuture.assignment.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.efuture.assignment.dto.ProductDto;
import com.efuture.assignment.dto.ValidationError;

public class CheckValidation {

	public static List<ValidationError> validateProductDto(ProductDto productDto) {
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
        if (productDto.getDescription() == null || productDto.getDescription().trim().isEmpty()) {
        	ValidationError err = new ValidationError();
        	err.setKey("description");
        	err.setError("Description cannot be empty.");
        	
        	errors.add(err);
        }
        if (productDto.getName() != null && containsDigits(productDto.getName())) {
        	ValidationError err = new ValidationError();
        	err.setKey("name");
        	err.setError("Product name cannot contain digits.");

        	errors.add(err);
        }
        if (productDto.getPrice() == null || productDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
        	ValidationError err = new ValidationError();
        	err.setKey("price");
        	err.setError("Price must be greater than zero.");

        	errors.add(err);
        }
        if (productDto.getCategory() == null) {
        	ValidationError err = new ValidationError();
        	err.setKey("category");
        	err.setError("Product category cannot be empty.");
        	
        	errors.add(err);
        }
        return errors;
    }
	
	private static boolean containsDigits(String input) {
        return Pattern.compile("\\d").matcher(input).find();
    }
}
