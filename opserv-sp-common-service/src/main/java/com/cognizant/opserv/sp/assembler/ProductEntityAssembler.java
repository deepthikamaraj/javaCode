package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.core.common.ApplicationConstant;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.model.Product;


/**
 * ****************************************************************************
 * Class Name: ProductAlignmentEntityAssembler for converting ProductAlignment Model Object dts to the TPrdAlgmnt Entity.
 *
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 1/4/2016
 * ***************************************************************************
 */
@Component
public class ProductEntityAssembler {
	
	/**
	 * toEntity object.
	 *
	 * @param ProductAlignment the Product alignment Model Object dts
	 * @return the TPrdAlgmnt Entity Object dts
	 */
	
public List<TPrd> convertProdModeltoEntity(List<Product> productList) {
	TPrd tPrd = null;
	List<TPrd> tPrdList = new ArrayList<TPrd>();
	for(Product product : productList){
			tPrd = new TPrd();
		
			tPrd.setPrdId(product.getId());
			tPrd.setPrdName(product.getName());
			tPrd.setPrdCd(product.getCode());
			tPrd.setPrdDesc(product.getDescription());
			tPrd.setEffStartDt(product.getStartDate());
			tPrd.setEffEndDt(product.getEndDate());
			
			if(product.isActive()){
				tPrd.setActiveFlag(ApplicationConstant.FLAG_YES);
			}else{
				tPrd.setActiveFlag(ApplicationConstant.FLAG_NO);
			}
			tPrdList.add(tPrd);
	}
		
		return tPrdList;
		
	}
	
}
