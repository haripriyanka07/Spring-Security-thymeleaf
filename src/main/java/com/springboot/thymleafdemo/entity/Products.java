package com.springboot.thymleafdemo.entity;

import java.util.List;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Products {
	
	private String name;
	
//	private String price;
	
	public Products() {}

}
//
//{
//	id:,
//	fis:,
//	prod:[
//	      {
//	    	  name:,
//	    	  price:
//	      },
//	      {
//	    	  
//	      }
//	     ]
//			
//}
