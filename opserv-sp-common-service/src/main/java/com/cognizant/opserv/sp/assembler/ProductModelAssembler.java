package com.cognizant.opserv.sp.assembler;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************
 * Class Name: ProductAlignmentModelAssembler for converting the 
 * 				TPrdAlgmnt Entity to ProductAlignment Model Object dts.
 *
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 1/4/2016
 * ***************************************************************************
 */
@Component
public class ProductModelAssembler {
	
	
	
	/**
	 * To value object.
	 *
	 * @param TPrdAlgmnt the tPrdAlgmnt entity dts
	 * @return the ProductAlignment Model Object dts
	 */
	public Product convertTProdDtsToProdModel(TPrd tPrd) {
		
		Product prod = new Product();
		
		if(null!=tPrd){
			if(null!=tPrd.getPrdId()){
			prod.setId(tPrd.getPrdId());
			}
			prod.setName(tPrd.getPrdName());
			prod.setCode(tPrd.getPrdCd());
			prod.setDescription(tPrd.getPrdDesc());
			prod.setStartDate(tPrd.getEffStartDt());
			prod.setEndDate(tPrd.getEffEndDt());
			if(null!=tPrd.getPrdId()){
			prod.setId(tPrd.getPrdId());
			}
			if(CommonConstants.ACTIVE_FLAG.equalsIgnoreCase(tPrd.getActiveFlag().toString())){
				prod.setActive(true);
			}else{
				prod.setActive(false);
			}
			
		}
		return prod;
		
	}
	
	public TPrd convertProductModelToEntity(TPrd tPrd, Product product, UserDetails userDetails){
		
		if(tPrd!=null){
			if(null!=tPrd.getPrdId()){
				tPrd.setPrdId(tPrd.getPrdId());
			}
			tPrd.setPrdCd(product.getCode());
			tPrd.setPrdName(product.getName());
			tPrd.setActiveFlag(product.isActive()==true?'Y':'N');
			tPrd.setEffStartDt(product.getStartDate());
			if(product.getEndDate() != null){
				tPrd.setEffEndDt(product.getEndDate());				
			}
			tPrd.setCreatedBy(product.getCreatedBy());
			tPrd.setCreateDt(product.getCreatedDate());
			tPrd.setTenantId(userDetails.getTenantId());
		}
		
		return tPrd;
	}
	
	public TPrd mapProductModelToEntity(Product product, UserDetails userDetails){
		
		TPrd tPrd = new TPrd();
		if(product!=null){
			tPrd.setPrdCd(product.getCode());
			tPrd.setPrdName(product.getName());
			tPrd.setActiveFlag(product.isActive()==true?'Y':'N');
			tPrd.setEffStartDt(product.getStartDate());
			if(product.getEndDate() != null){
				tPrd.setEffEndDt(product.getEndDate());				
			}
			tPrd.setCreatedBy(product.getCreatedBy());
			tPrd.setCreateDt(product.getCreatedDate());
			tPrd.setTenantId(userDetails.getTenantId());
		}
		
		return tPrd;
	}

	/**
	 * To value object.
	 *
	 * @param TPrdAlgmnt the tPrdAlgmnt entity dts
	 * @return the ProductAlignment Model Object dts
	 */
	public Product convertTProdToProdModel(TPrd tPrd) {
		
		Product prod = new Product();
		
		if(null!=tPrd){
			if(null!=tPrd.getPrdId()){
			prod.setId(tPrd.getPrdId());
			}
			prod.setName(tPrd.getPrdName());
				
		}
		return prod;
	}
}
