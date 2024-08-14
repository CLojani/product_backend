package com.efuture.assignment.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.efuture.assignment.dto.ProductDto;
import com.efuture.assignment.dto.ProductDtoResponse;
import com.efuture.assignment.dto.ResponseDTO;
import com.efuture.assignment.enums.ActiveStatusEnum;
import com.efuture.assignment.enums.LoggerMessageEnum;
import com.efuture.assignment.enums.ResponseEnum;
import com.efuture.assignment.exception.SqlExceptionUtil;
import com.efuture.assignment.model.Product;
import com.efuture.assignment.model.ProductCategory;
import com.efuture.assignment.model.ProductComment;
import com.efuture.assignment.repository.ProductCategoryRepository;
import com.efuture.assignment.repository.ProductCommentRepository;
import com.efuture.assignment.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository  productRepository;
	@Autowired
	ProductCommentRepository productCommentRepository;
	@Autowired
	ProductCategoryRepository productCategoryRepository;

	public ResponseDTO getProductCategory(ServletRequest request, String category) {
		ResponseDTO response = new ResponseDTO();
		try {
							
			ProductCategory cat = productCategoryRepository.findByName(category);
			List<Product> products = productRepository.findByProductCategory(cat);
			
			if(products !=  null) {
	
				List<ProductDto> productsDto = products.stream().map(p -> {
					
					ProductDto dto = new ProductDto();
					
					dto.setName(p.getName());
					dto.setDescription(p.getName());
					dto.setPrice(p.getPrice());
					dto.setCategory(p.getProductCategory());
					dto.setStatus(p.getStatus());
					
					return dto;
					
				}).collect(Collectors.toList());
	
				response.setProducts(productsDto);
				response.setCode(ResponseEnum.SUCCESS.getCode());
				response.setMessage(ResponseEnum.SUCCESS.getMessage());
				
			}else {
				response.setCode(ResponseEnum.NO_DATA.getCode());
				response.setMessage(ResponseEnum.NO_DATA.getMessage());
			}
		} catch (Exception e) {
			
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
		}
		return response;
	}

	public ResponseDTO getProductsByPrice(ServletRequest request) {
		ResponseDTO response = new ResponseDTO();
		try {
			
			List<Product> products = productRepository.getProductsByPrice();			
			if(products !=  null) {

				List<ProductDtoResponse> productsDto = products.stream().map(p -> {					
					ProductDtoResponse dto = new ProductDtoResponse();
					
					dto.setName(p.getName());
					dto.setDescription(p.getName());
					dto.setPrice(p.getPrice());
					dto.setCategory(p.getProductCategory());
					dto.setStatus(p.getStatus());
					
					List<ProductComment> comments = productCommentRepository.findByProduct(p);
					if(comments !=  null) {
						dto.setComments(comments);
					}
										
					return dto;
					
				}).collect(Collectors.toList());

				response.setProductAll(productsDto);
				response.setCode(ResponseEnum.SUCCESS.getCode());
				response.setMessage(ResponseEnum.SUCCESS.getMessage());
				
			}else {
				response.setCode(ResponseEnum.NO_DATA.getCode());
				response.setMessage(ResponseEnum.NO_DATA.getMessage());
			}		
			
		} catch (Exception e) {
			
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
		}
		return response;
	}

	public ResponseDTO create(ServletRequest request, ProductDto productDto) {
		ResponseDTO response = new ResponseDTO();
		try {
			
			if(productDto !=  null) {
				Product product = new Product();
				product.setDescription(productDto.getDescription());
				product.setName(productDto.getName());
				product.setPrice(productDto.getPrice());
				product.setProductCategory(productDto.getCategory());
				product.setStatus(ActiveStatusEnum.ACTIVE.getCode());
				
				productRepository.save(product);				
				response.setCode(ResponseEnum.SUCCESS.getCode());
				response.setMessage(ResponseEnum.SUCCESS.getMessage());
			}
			
		} catch (DataIntegrityViolationException ex) {
           
            Throwable cause = ex.getCause();
            if (cause instanceof SQLException) {
                SQLException sqlEx = (SQLException) cause;
                String errorMessage = SqlExceptionUtil.handleSQLException(sqlEx);
                System.out.println(errorMessage +"_---"+sqlEx);
                response.setCode(ResponseEnum.ERROR.getCode());
                response.setMessage(errorMessage);
            } else {
                response.setCode(ResponseEnum.ERROR.getCode());
                response.setMessage("Data integrity violation: " + ex.getMessage());
            }
	    } catch (Exception ex) {
	        response.setCode(ResponseEnum.ERROR.getCode());
	        response.setMessage(ResponseEnum.ERROR.getMessage() + ex.getMessage());
	    }	
		return response;
	}

	public ResponseDTO update(ServletRequest request, ProductDto productDto) {
		ResponseDTO response = new ResponseDTO();
		try {
			
			Product product = productRepository.findByProductId(productDto.getProductId());			
			if(product !=  null) {
				
				product.setDescription(productDto.getDescription());
				product.setName(productDto.getName());
				product.setPrice(productDto.getPrice());
				product.setProductCategory(productDto.getCategory());
				product.setStatus(ActiveStatusEnum.ACTIVE.getCode());
				
				productRepository.save(product);				
				response.setCode(ResponseEnum.SUCCESS.getCode());
				response.setMessage(ResponseEnum.SUCCESS.getMessage());
				
			}else {
				response.setCode(ResponseEnum.NOT_FOUND.getCode());
				response.setMessage(ResponseEnum.NOT_FOUND.getMessage());
			}
			
		}catch (DataIntegrityViolationException ex) {
	            if (ex.getCause() instanceof SQLException) {
	            	
	                String errorMessage = SqlExceptionUtil.handleSQLException((SQLException) ex.getCause());
	                response.setCode(ResponseEnum.ERROR.getCode());
					response.setMessage(errorMessage);
	            } else {
	            	
	            	response.setCode(ResponseEnum.ERROR.getCode());
					response.setMessage(ex.getMessage());
	            }
		} catch (Exception e) {
			
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
		}
		return response;
	}

	public ResponseDTO delete(ServletRequest request, int productId) {
		ResponseDTO response = new ResponseDTO();
		try {
			
			Product product = productRepository.findByProductId(productId);			
			if(product !=  null) {
				
				product.setStatus(ActiveStatusEnum.DEACTIVATE.getCode());
				
				productRepository.save(product);				
				response.setCode(ResponseEnum.SUCCESS.getCode());
				response.setMessage(ResponseEnum.SUCCESS.getMessage());
				
			}else {
				response.setCode(ResponseEnum.NOT_FOUND.getCode());
				response.setMessage(ResponseEnum.NOT_FOUND.getMessage());
			}
		} catch (Exception e) {
			
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
		}
		return response;
	}
		
}
