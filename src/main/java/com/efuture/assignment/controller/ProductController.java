package com.efuture.assignment.controller;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efuture.assignment.dto.ProductDto;
import com.efuture.assignment.dto.ResponseDTO;
import com.efuture.assignment.enums.LoggerMessageEnum;
import com.efuture.assignment.enums.ResponseEnum;
import com.efuture.assignment.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins="*")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	private String controllerName = "PRODUCT CONTROLLER - ";
	
	@Autowired
	ProductService productService;

	@GetMapping("/{category}")
	private ResponseDTO getProductsCategory(ServletRequest request,@PathVariable String category) {
		
		ResponseDTO response = new ResponseDTO();
		try {
			response = 	productService.getProductCategory(request,category);
			logger.info(controllerName + LoggerMessageEnum.GET.getValue() + LoggerMessageEnum.METHOD_EXECUTED.getValue());
		} catch (Exception e) {
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
			logger.info(controllerName + LoggerMessageEnum.GET.getValue() + LoggerMessageEnum.ERROR_OCCURED.getValue()+ e.getLocalizedMessage());
		}
		return response;
	}
	
	@GetMapping
	private ResponseDTO getProductsByPrice(ServletRequest request) {
		
		ResponseDTO response = new ResponseDTO();
		try {
			response = 	productService.getProductsByPrice(request);
			logger.info(controllerName + LoggerMessageEnum.GET.getValue() + LoggerMessageEnum.METHOD_EXECUTED.getValue());
		} catch (Exception e) {
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
			logger.info(controllerName + LoggerMessageEnum.GET.getValue() + LoggerMessageEnum.ERROR_OCCURED.getValue()+ e.getLocalizedMessage());
		}
		return response;
	}
	
	@PostMapping
	private ResponseDTO create(ServletRequest request,@RequestBody ProductDto productDto) {
		
		ResponseDTO response = new ResponseDTO();
		try {
			response = 	productService.create(request,productDto);
			logger.info(controllerName + LoggerMessageEnum.CREATE.getValue() + LoggerMessageEnum.METHOD_EXECUTED.getValue());
		} catch (Exception e) {
			response.setCode(response.getCode());
			response.setMessage(response.getMessage());
			logger.info(controllerName + LoggerMessageEnum.CREATE.getValue() + LoggerMessageEnum.ERROR_OCCURED.getValue()+ e.getLocalizedMessage());
		}
		return response;
	}
	
	@PutMapping
	private ResponseDTO update(ServletRequest request,@RequestBody ProductDto productDto) {
		
		ResponseDTO response = new ResponseDTO();
		try {
			response = 	productService.update(request,productDto);
			logger.info(controllerName + LoggerMessageEnum.UPDATE.getValue() + LoggerMessageEnum.METHOD_EXECUTED.getValue());
		} catch (Exception e) {
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
			logger.info(controllerName + LoggerMessageEnum.UPDATE.getValue() + LoggerMessageEnum.ERROR_OCCURED.getValue()+ e.getLocalizedMessage());
		}
		return response;
	}
	
	@DeleteMapping("/{productId}")
	private ResponseDTO delete(ServletRequest request,@PathVariable int productId ) {
		
		ResponseDTO response = new ResponseDTO();
		try {
			response = 	productService.delete(request,productId);
			logger.info(controllerName + LoggerMessageEnum.DELETE.getValue() + LoggerMessageEnum.METHOD_EXECUTED.getValue());
		} catch (Exception e) {
			response.setCode(response.getCode());
			response.setMessage(response.getMessage());
			logger.info(controllerName + LoggerMessageEnum.DELETE.getValue() + LoggerMessageEnum.ERROR_OCCURED.getValue()+ e.getLocalizedMessage());
		}
		return response;
	}
}
