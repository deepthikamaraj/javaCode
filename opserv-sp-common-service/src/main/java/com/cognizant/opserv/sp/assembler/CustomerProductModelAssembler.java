package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustPrdSales;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerProduct;
import com.cognizant.opserv.sp.model.Product;

@Component
public class CustomerProductModelAssembler {
	
	@Autowired
	CustomerModelAssembler customerModelAssembler;
	
	@Autowired
	private ProductModelAssembler productModelAssembler;
	
	/**
	 * Gets the products of customer.
	 * @method convertTCustPrdToProduct
	 * @param  tCustPrdList the customerproduct list
	 * @return the customerproducts of customer
	 */	
	@SuppressWarnings("null")
	public  List<Product> convertTCustPrdToProduct(List<TPrd> tPrdList){

		List<Product> productList = null;
		productList = new ArrayList<Product>();
		for(TPrd tPrd : tPrdList){
			Product product = new Product();
			product = productModelAssembler.convertTProdDtsToProdModel(tPrd);
			productList.add(product);
		}

		return productList;
	}


	/**
	 * Gets the customerproducts of customer.
	 * @method convertTCustPrdSalesToCustomerProduct
	 * @param  tCustPrdList the customerproduct list
	 * @return the customerproducts of customer
	 */		
	@SuppressWarnings("null")
	public  List<CustomerProduct> convertTCustPrdToCustomerProduct(List<TCustPrdSales> tCustPrdList){

		List<CustomerProduct> custPrdList = new ArrayList<CustomerProduct>();
		CustomerProduct customerProduct = null;

		if((null != tCustPrdList && tCustPrdList.size() > 0)){
			for(TCustPrdSales tCustPrd : tCustPrdList){
				if (null != tCustPrd.getTCust() && null != tCustPrd.getTPrd()) {
					customerProduct = new CustomerProduct();
					customerProduct.setCustomer(customerModelAssembler.convertTCustToModelWithChildEntities(tCustPrd.getTCust()));
					customerProduct.setProduct(productModelAssembler.convertTProdDtsToProdModel(tCustPrd.getTPrd()));
					custPrdList.add(customerProduct);
				}

			}

			/*for(TCustPrdSales tCustPrd : tCustPrdList){
    	 if (null != tCustPrd.getTPrd()) {
				prod = ProductModelAssembler
						.convertTProdDtsToProdModel(tCustPrd.getTPrd());
				//productList = new ArrayList<Product>();
				//productList.add(prod);
			}
    	 int id = tCustPrd.getTCust().getCustId();
    	 for(Map.Entry<Customer, Product> custEntry : customerProductMap.entrySet()){
    		 if(custEntry.getKey().getId() == id){
    			 custEntry.setValue(prod);
    		 }
    	 }

     }*/


		}
		return custPrdList;
	}

	/**
	 * Gets the customerproducts of customer.
	 * @method convertTCustPrdAlignToCustomerProduct
	 * @param  tCustPrdAlignList the customerproductAlignment list
	 * @return the customerproducts of customer
	 */	
	public  CustomerProduct convertTCustPrdAlignToCustomerProduct(List<TCustPrdAlgmnt> tCustPrdAlignList){
		//List<CustomerProduct> custProdList = new ArrayList<CustomerProduct>();
		HashMap<Customer,List<Product>> customerProductMap = null;
		List<Product> productList = null;
		CustomerProduct custProd = null;
		Customer customer = null;
		Product prod = null;


		/*if (null != tCustPrdAlignList && tCustPrdAlignList.size() > 0) {
		for (TCustPrdAlgmnt tCustPrdAlgmnt : tCustPrdAlignList) {
			customerProductMap = new HashMap<Customer, List<Product>>();
			if (null != tCustPrdAlgmnt.getTCustAlgmnt()
					&& null != tCustPrdAlgmnt.getTCustAlgmnt().getTCust()) {
				 customer = CustomerModelAssembler
						.convertTcustToModel(tCustPrdAlgmnt
								.getTCustAlgmnt().getTCust());
				 customerProductMap.put(customer, new ArrayList<Product>());	
			}
		}

			for (TCustPrdAlgmnt tCustPrdAlgmnt : tCustPrdAlignList) {
				if (null != tCustPrdAlgmnt.gettPrdAlgmnt()
						&& null != tCustPrdAlgmnt.gettPrdAlgmnt().gettPrd()) {
					 prod = ProductModelAssembler
							.convertTProdDtsToProdModel(tCustPrdAlgmnt
									.gettPrdAlgmnt().gettPrd());
					productList = new ArrayList<Product>();
					productList.add(prod);
				}
				 int id = tCustPrdAlgmnt.getTCustAlgmnt().getTCust().getCustId();
		    	 for(Map.Entry<Customer, List<Product>> custEntry : customerProductMap.entrySet()){
		    		 if(custEntry.getKey().getId() == id){
		    			 custEntry.getValue().addAll(productList);
		    		 }
		    	 }
			}

		for(Map.Entry<Customer, List<Product>> mapEntry : customerProductMap.entrySet()){
			custProd = new CustomerProduct();
			custProd.setCustomer(mapEntry.getKey());
			custProd.setProducts(mapEntry.getValue());
			return custProd;
			//custProdList.add(custProd);
		}

	}*/

		//return custProdList;
		return null;


	}

}
