package com.cognizant.opserv.sp.assembler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.AttributeType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmnt;
import com.cognizant.opserv.sp.json.ExtendedAttribute;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;

/**
 * ****************************************************************************
 * Class Name: ProductAlignmentModelAssembler for converting the TPrdAlgmnt
 * Entity to ProductAlignment Model Object dts.
 * 
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 1/4/2016
 *  **************************************************************
 */
@Component
public class ProductAlignmentModelAssembler {

	/**
	 * To value object.
	 * 
	 * @param TPrdAlgmnt
	 *            the tPrdAlgmnt entity dts
	 * @return the ProductAlignment Model Object dts
	 */
	public ProductAlignment convertTProdAlgDtsToProdAlg(
			TPrdAlgmnt tPrdAlgmnt) {

		ProductAlignment prodAlgm = new ProductAlignment();

		if (null != tPrdAlgmnt) {
			prodAlgm.setId(tPrdAlgmnt.getPrdAlgmntId());
			
			try {
				prodAlgm.setStartDate(DateUtil.toCurrentUTCDateTime(tPrdAlgmnt.getEffStartDt(),Locale.ENGLISH));
				if(null!= tPrdAlgmnt.getEffEndDt()){
					prodAlgm.setEndDate(DateUtil.toCurrentUTCDateTime(tPrdAlgmnt.getEffEndDt(),Locale.ENGLISH));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			prodAlgm.setWeightage(tPrdAlgmnt.getWtg());
			if (CommonConstants.ACTIVE_FLAG.equalsIgnoreCase(tPrdAlgmnt
					.getActiveFlag().toString())) {
				prodAlgm.setActive(true);
			} else {
				prodAlgm.setActive(false);
			}

			Product prd = new Product();
			if(null!=tPrdAlgmnt.gettPrd() && null!=tPrdAlgmnt.gettPrd().getPrdId() && CommonConstants.ACTIVE_FLAG.equalsIgnoreCase(tPrdAlgmnt.gettPrd().getActiveFlag().toString())){
				prd.setId(tPrdAlgmnt.gettPrd().getPrdId());
				prd.setName(tPrdAlgmnt.gettPrd().getPrdName());
				prd.setCode(tPrdAlgmnt.gettPrd().getPrdCd());
				prd.setId(tPrdAlgmnt.gettPrd().getPrdId());
				prd.setActive(true);
				prd.setStartDate(tPrdAlgmnt.gettPrd().getEffStartDt());
				prd.setEndDate(tPrdAlgmnt.gettPrd().getEffEndDt());
				prodAlgm.setProduct(prd);
			}

			SalesPosition sp = new SalesPosition();
			if(null!=tPrdAlgmnt.gettSalesPos() && CommonConstants.ACTIVE_FLAG.equalsIgnoreCase(tPrdAlgmnt.gettSalesPos().getActiveFlag().toString())){
				sp.setId(tPrdAlgmnt.getSalesPosId());
				sp.setName(tPrdAlgmnt.gettSalesPos().getPosName());
				sp.setCode(tPrdAlgmnt.gettSalesPos().getPosCd());
				sp.setStartDate(tPrdAlgmnt.gettSalesPos().getEffStartDt());
				sp.setEndDate(tPrdAlgmnt.gettSalesPos().getEffEndDt());
				sp.setActive(true);
			}
			Alignment alg = new Alignment();
			if(null!=tPrdAlgmnt.gettSalesPos() && null!=tPrdAlgmnt.gettSalesPos().getTAlgmntSalesTeam() &&  null!=tPrdAlgmnt.gettSalesPos().getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId()){
			
				alg.setId(tPrdAlgmnt.getAlgmntId());
				alg.setName(tPrdAlgmnt.gettSalesPos().getTAlgmntSalesTeam().getTAlgmnt().getAlgmntName());
				alg.setStartDate(tPrdAlgmnt.gettSalesPos().getTAlgmntSalesTeam().getTAlgmnt().getEffStartDt());
				alg.setEndDate(tPrdAlgmnt.gettSalesPos().getTAlgmntSalesTeam().getTAlgmnt().getEffEndDt());
				alg.setActive(true);
			}

			BusinessUnit bu = new BusinessUnit();
			bu.setId(tPrdAlgmnt.getBussUnitId());
			bu.setActive(true);
			SalesTeam st = new SalesTeam();
			st.setId(tPrdAlgmnt.getSalesTeamId());
			st.setBusinessUnit(bu);
			st.setActive(true);
			alg.setSalesTeam(st);

			SalesOrgHierarchy sh = new SalesOrgHierarchy();
			sh.setId(tPrdAlgmnt.getSalesHierId());
			sh.setActive(true);
			sp.setHierarchy(sh);
			sp.setAlignmment(alg);
			
			prodAlgm.setSalesPosition(sp);

		}
		return prodAlgm;

	}

	/**
	 * To value object.
	 * 
	 * @param TPrdAlgmnt
	 *            the tPrdAlgmnt entity dts
	 * @return the ProductAlignment Model Object dts
	 */
	public ProductAlignment convertTProdAlgDtsToProdAlg(
			com.cognizant.opserv.sp.json.ProductAlignment jsonPrdAlgmnt) {

		ProductAlignment prodAlgm = new ProductAlignment();

		if (null != jsonPrdAlgmnt) {

			prodAlgm.setId(jsonPrdAlgmnt.getProdAlignId());
			prodAlgm.setWeightage(jsonPrdAlgmnt.getWeightage());
			prodAlgm.setStartDate(jsonPrdAlgmnt.getEffectiveStartDate());
			prodAlgm.setEndDate(jsonPrdAlgmnt.getEffectiveEndDate());

			List<ExtdAttribue> prodAlgmntExtdAttributeList = new ArrayList<ExtdAttribue>();
			for (ExtendedAttribute extendedAttribute : jsonPrdAlgmnt
					.getProdAlignExtAttrs()) {
				AttributeDefinition attributeDefinition = new AttributeDefinition();
				attributeDefinition.setId(Long.parseLong(extendedAttribute
						.getId().toString()));
				attributeDefinition.setName(extendedAttribute.getName());
				attributeDefinition.setType(AttributeType
						.getAttributeType(Integer.parseInt(extendedAttribute
								.getType())));

				ExtdAttribue extdAttribue = new ExtdAttribue();
				extdAttribue.setDefinition(attributeDefinition);
				extdAttribue.setValue(extendedAttribute.getValue());
				prodAlgmntExtdAttributeList.add(extdAttribue);
			}
			prodAlgm.setExtdAttributes(prodAlgmntExtdAttributeList);

			// if(ACTIVE_FLAG.equalsIgnoreCase(tPrdAlgmnt.getActiveFlag().toString())){
			// prodAlgm.setActive(true);
			// }else{
			// prodAlgm.setActive(false);
			// }

			Product prd = new Product();
			prd.setId(jsonPrdAlgmnt.getProdId());
			prd.setName(jsonPrdAlgmnt.getProdName());
			prd.setCode(jsonPrdAlgmnt.getProdCode());

			List<ExtdAttribue> prodExtdAttributeList = new ArrayList<ExtdAttribue>();
			for (ExtendedAttribute extendedAttribute : jsonPrdAlgmnt
					.getProdExtAttrs()) {
				AttributeDefinition attributeDefinition = new AttributeDefinition();
				attributeDefinition.setId(Long.parseLong(extendedAttribute
						.getId().toString()));
				attributeDefinition.setName(extendedAttribute.getName());
				attributeDefinition.setType(AttributeType
						.getAttributeType(Integer.parseInt(extendedAttribute
								.getType())));

				ExtdAttribue extdAttribue = new ExtdAttribue();
				extdAttribue.setDefinition(attributeDefinition);
				extdAttribue.setValue(extendedAttribute.getValue());
				prodExtdAttributeList.add(extdAttribue);
			}
			prd.setExtdAttributes(prodExtdAttributeList);
			prodAlgm.setProduct(prd);

			SalesPosition sp = new SalesPosition();
			sp.setId(jsonPrdAlgmnt.getSalesposId());
			sp.setName(jsonPrdAlgmnt.getSalesposName());

			// Alignment alg = new Alignment();
			// alg.setId(jsonPrdAlgmnt.getProdAlignId());
			//
			// BusinessUnit bu = new BusinessUnit();
			// bu.setId(tPrdAlgmnt.getBussUnitId());
			//
			//
			// SalesTeam st = new SalesTeam();
			// st.setId(tPrdAlgmnt.getSalesTeamId());
			// st.setBusinessUnit(bu);
			// alg.setSalesTeam(st);
			//
			// SalesOrgHierarchy sh = new SalesOrgHierarchy();
			// sh.setId(tPrdAlgmnt.getSalesHierId());
			// sp.setHierarchy(sh);

			prodAlgm.setSalesPosition(sp);

		}
		return prodAlgm;

	}

	/**
	 * To value object.
	 * 
	 * @param ProductAlignment
	 *            json
	 * @return the ProductAlignment Model Object dts
	 */
	public static List<ProductAlignment> convertjsProductAlignmentToModel(
			List<com.cognizant.opserv.sp.json.ProductAlignment> productAlignmentjs, 
			SalesPosition salesPosition, Alignment alignment, Map<String, AttributeDefinition> mapOfAttrDefn) {
		
		List<ProductAlignment> productAlignmentsLst = new ArrayList<ProductAlignment>();
		
		if (productAlignmentjs != null && !productAlignmentjs.isEmpty()) {
			
			for (com.cognizant.opserv.sp.json.ProductAlignment prdAlignment : productAlignmentjs) {
				if(null != prdAlignment.getProdAlignId()){
					//create product alignmnt object
					ProductAlignment productAlignment = new ProductAlignment();
					productAlignment.setId(prdAlignment.getProdAlignId());
					productAlignment.setSalesPosition(salesPosition);
					
					productAlignment.setWeightage((String) (prdAlignment.getWeightage() == null ? "" : prdAlignment.getWeightage()));
					if(null != prdAlignment.getEffectiveStartDate()){
						productAlignment.setStartDate(prdAlignment.getEffectiveStartDate());
					}
					if(null != prdAlignment.getEffectiveEndDate()){
						productAlignment.setEndDate(prdAlignment.getEffectiveEndDate());
					}
					
					//create product object
					Product product = new Product();
					if(null != prdAlignment.getProdId()){
						product.setId(prdAlignment.getProdId());
					}
					product.setName(prdAlignment.getProdName() == null ? "" : prdAlignment.getProdName());
					product.setDescription(prdAlignment.getProdName() == null ? "" : prdAlignment.getProdName());
					product.setCode(prdAlignment.getProdCode() == null ? "" : prdAlignment.getProdCode());
					
					productAlignment.setProduct(product);
					
					//create alignment extended attributes - max 1-20
					if (null != prdAlignment.getProdAlignExtAttrs() && !prdAlignment.getProdAlignExtAttrs().isEmpty()){
						List<ExtdAttribue> prdExtnAttr = new ArrayList<ExtdAttribue>();
						for (ExtendedAttribute extnAttr : prdAlignment.getProdAlignExtAttrs()){
							if (null != extnAttr){
					        	AttributeDefinition attributeDefinition = mapOfAttrDefn.get(extnAttr.getName());
					        	if(null != attributeDefinition){
					        		ExtdAttribue extdAttribue = new ExtdAttribue();
						        	extdAttribue.setName(extnAttr.getName());
						        	extdAttribue.setValue(extnAttr.getValue());
						        	extdAttribue.setDefinition(attributeDefinition);
						        	prdExtnAttr.add(extdAttribue);
					        	}
				        	}
						}
						productAlignment.setExtdAttributes(prdExtnAttr);
					}
					productAlignmentsLst.add(productAlignment);
				}
			}
		}
		return productAlignmentsLst;
	}
}
