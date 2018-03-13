package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmnt;
import com.cognizant.opserv.sp.json.ExtendedAttribute;
import com.cognizant.opserv.sp.json.ProductDetails;
import com.cognizant.opserv.sp.json.SPTargetedCustomerProduct;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;

/**
 * ****************************************************************************
 * Class Name: CustomerProductAlignmentModelAssembler for converting the 
 * 				json object SPCustomerProduct to list of CustomerProductAlignment model object 
 *
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 1/4/2016
 * ***************************************************************************
 */
@Component
public class CustomerProductAlignmentModelAssembler {
	
	@Autowired
	CustomerModelAssembler customerModelAssembler;
	
	@Autowired
	private ProductModelAssembler productModelAssembler;
	
	public List<CustomerProductAlignment> convertJsonObjToModelObj(SPTargetedCustomerProduct spCustomerProduct,
			SalesPosition salesPosition, Alignment alignment, Map<String, AttributeDefinition> mapOfAttrDefn){
		
		salesPosition.setAlignmment(alignment);
		List<CustomerProductAlignment> customerProductAlignmentList = new ArrayList<CustomerProductAlignment>();
		
		Map<Long,List<ProductDetails>> custIdPrdsMap = spCustomerProduct.getCustIdPrds();
		if(null != custIdPrdsMap){
			for(Entry<Long,List<ProductDetails>> entry : custIdPrdsMap.entrySet()){
				
				Customer customer = null;
				if (null != entry.getKey()){
					customer = new Customer();
					customer.setId(entry.getKey());
				}
				
				CustomerProductAlignment customerProductAlignment = new CustomerProductAlignment();
				customerProductAlignment.setSalesPosition(salesPosition);
				customerProductAlignment.setCustomer(customer);
				
				List<ProductDetails> prodList = entry.getValue();
				
				if(null != prodList && !prodList.isEmpty()){
					for(ProductDetails productDetails : prodList){
						
						Product product = null; 
						if (null != productDetails.getProdId()){
							product = new Product();
							product.setId(productDetails.getProdId());
														
							product.setName(productDetails.getProdName() == null ? "" : productDetails.getProdName());
							product.setCode(productDetails.getProdCode() == null ? "" : productDetails.getProdCode());
							product.setStartDate((Date)(productDetails.getEffectiveStartDate() == null ? "" : productDetails.getEffectiveStartDate()));
							product.setEndDate((Date)(productDetails.getEffectiveEndDate() == null ? "" : productDetails.getEffectiveEndDate()));
						}
						
						customerProductAlignment.setProduct(product);
						customerProductAlignment.setCompAvailable(productDetails.isCompApplicable());
						
						//start - create list of algnmnt extended (1-20) attr
				        if (null != productDetails.getProdExtAttrs() && !productDetails.getProdExtAttrs().isEmpty()){
				        	List<ExtdAttribue> custPrdExtnAttr = new ArrayList<ExtdAttribue>();
					        for (ExtendedAttribute extnAttr : productDetails.getProdExtAttrs()){
					        	if (null != extnAttr){
						        	AttributeDefinition attributeDefinition = mapOfAttrDefn.get(extnAttr.getName());
						        	if(null != attributeDefinition){
						        		ExtdAttribue extdAttribue = new ExtdAttribue();
							        	extdAttribue.setName(extnAttr.getName());
							        	extdAttribue.setValue(extnAttr.getValue());
							        	extdAttribue.setDefinition(attributeDefinition);
							        	custPrdExtnAttr.add(extdAttribue);
						        	}
					        	}
					        }
					        customerProductAlignment.setExtdAttributes(custPrdExtnAttr);
				        }
				        //end - list of extended (1-20) attr
					}
				}
				customerProductAlignmentList.add(customerProductAlignment);
			}
		}
		return customerProductAlignmentList;
	}
	
	
	public  List<CustomerProductAlignment> convertTCustPrdAlgToCustProdAlgModel(
			List<TCustPrdAlgmnt> custPrdAlgList, boolean flag) {

		List<CustomerProductAlignment> custProdAligList = new ArrayList<CustomerProductAlignment>();
		if (null != custPrdAlgList && custPrdAlgList.size() > 0) {
			
			CustomerProductAlignment custProdAlg = null;
			for (TCustPrdAlgmnt tCustPrdAlgmnt : custPrdAlgList) {
				custProdAlg = new CustomerProductAlignment();
				if(null!=tCustPrdAlgmnt.getCompFlag() && tCustPrdAlgmnt.getCompFlag().equals(CommonConstants.ACTIVE_Y)){
					custProdAlg.setCompAvailable(true);
				}
				if(flag){
					if (null != tCustPrdAlgmnt.getTCustAlgmnt()
							&& null != tCustPrdAlgmnt.getTCustAlgmnt().getTCust()) {
						Customer customer = customerModelAssembler.convertTCustToModelWithChildEntities(tCustPrdAlgmnt.getTCustAlgmnt().getTCust());
						custProdAlg.setCustomer(customer);
					}
					if (null != tCustPrdAlgmnt.gettPrdAlgmnt()
							&& null != tCustPrdAlgmnt.gettPrdAlgmnt().gettPrd()) {
						Product product = productModelAssembler
								.convertTProdDtsToProdModel(tCustPrdAlgmnt
										.gettPrdAlgmnt().gettPrd());
						custProdAlg.setProduct(product);
					}
					
				}else{
					
					if (null != tCustPrdAlgmnt.getTCustAlgmnt()
							&& null != tCustPrdAlgmnt.getTCustAlgmnt().getTCust()) {
						Customer customer = customerModelAssembler.convertTCustToModelWithBasicDetails(tCustPrdAlgmnt.getTCustAlgmnt().getTCust());
						custProdAlg.setCustomer(customer);
					}
					if (null != tCustPrdAlgmnt.gettPrdAlgmnt()
							&& null != tCustPrdAlgmnt.gettPrdAlgmnt().gettPrd()) {
						Product product = productModelAssembler
								.convertTProdToProdModel(tCustPrdAlgmnt
										.gettPrdAlgmnt().gettPrd());
						custProdAlg.setProduct(product);
					}
					
				}
				
				custProdAlg.setAllocationPercentage(tCustPrdAlgmnt.getAllocPct());
				custProdAlg.setStartDate(tCustPrdAlgmnt.getEffStartDt());
				custProdAlg.setEndDate(tCustPrdAlgmnt.getEffEndDt());
				custProdAlg.setPrdFlag(tCustPrdAlgmnt.getPrdFlag());
				custProdAlg.setCustomerAlgmntId(tCustPrdAlgmnt.getTCustPrdAlgmntId().getCustAlgmntId());
				custProdAlg.setProductAlgmntId(tCustPrdAlgmnt.getTCustPrdAlgmntId().getPrdAlgmntId());
				custProdAligList.add(custProdAlg);

			}
		}

		return custProdAligList;

	}

}
