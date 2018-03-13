package com.cognizant.opserv.sp.cr.process.dto;

public enum ErrorCode {

	SERVER_ERROR_1("Sample server error"),
	ERROR_2001("Offline Id is not Intiated"),
	ERROR_2002("Selected Customer is not a root and hence cannot be pushed"),
	ERROR_2003("One of the Base Sales Positions doesn't have Mirror Sales Position"),
	ERROR_2004("Some of the Affilated child customers only are not assigned to the source Sales Position "),
	ERROR_2005("Missing  geography alignment / tenant id"),
	ERROR_2006("Missing customer alignment / tenant id"),
	ERROR_2009("Invalid Mirror case 1:0"),
	ERROR_2010("Affiliated customer issue"),
	ERROR_2011("ZIP selected is already submitted for movement"),
	ERROR_2013("ZIP doesn't belong to mirror sales pos"),
	ERROR_2014("Metrics Voilation Exception"),
	ERROR_2015("Alignment Exception"),
	ERROR_2016("Metrics Execution Exception"),
	ERROR_ZM_2007("Error during initialize CR workflow for zip movement."),
	ERROR_ZM_2012("zipBlob is null"),
	ERROR_ZM_2016("VALIDATION FAILED::: Mandatory field Source Geography Alignment is null"),
	ERROR_ZM_2017("VALIDATION FAILED::: Mandatory field Target Geography Alignment is null"),
	ERROR_ZM_2018("VALIDATION FAILED::: Mandatory field Change Request ID is null"),
	ERROR_ZM_2019("VALIDATION ERROR::: Multiple primary target mirrors present in a particular alignment"),
	ERROR_ZM_2020("VALIDATION ERROR::: Multiple source mirrors containing zip in a particular alignment"),
	ERROR_ZM_2021("VALIDATION ERROR::: Source mirror present for an alignment but primary target mirror not present"),
	ERROR_ZM_2022("Error during processing LineItem for source zip movement"),
	ERROR_ZM_2023("Missing crLineItems / tenant id"),
	ERROR_ZM_2024("Issue occurred while locking Customer Alignment"),
	ERROR_ZM_2025("Exception while updating CR Offline Status"),
	ERROR_ZM_2026("OfflineId or UserDetails is null"),
	ERROR_ZM_2027("Exception occured while fetching Affiliated Customers"),
	ERROR_ZM_2028("Missing parentCR /  tenant id. Hence unable to find Mirror CR"),
	ERROR_ZM_2029("Data access exception while fetching mirror CR"),
	ERROR_ZM_2030("Exception occurred while fetching existing Geo unit ids"),
	ERROR_ZM_2031("Exception occurred while creating target geo id"),
	ERROR_ZM_2032("Exception occurred while inserting target zip alignment."),
	ERROR_ZM_2033("Exception occurred while fetching geography alignments."),
	ERROR_ZM_2034("Exception while updating CR Offline Status."),
	ERROR_ZM_2035("Exception while processing customer."),
	
	
	
	ERROR_CP_2024("The customer is already assigned to this SalesPosition in this time period. Please change the Start & End dates"),
	ERROR_CP_2025("Mirror CR is not created"),
	ERROR_CP_2026("Exception while updating CR Offline Status to Processing"),
	ERROR_CP_2027("Unable to lock the Root customer for the Mirror"),
	ERROR_CP_2028("Unable to lock the Affiliated customer for the Base"),
	ERROR_CP_2029("Unable to lock the Affiliated customer for the Mirror"),
	ERROR_CP_2030("Issue occurred while fetching CallPlan Details"),
	ERROR_CP_2031("Issue occurred while fetching Customer Product Alignment"),
	ERROR_CP_2032("Error finding mirror cr for push"),
	ERROR_CP_2033("Customer Algmnt or TenantId or UserId is null"),
	ERROR_CP_2034("Error while fetching Affiliated customer for Push"),
	ERROR_CP_2035("Error while fetching Mirrors for Push Customers"),
	
	
	ERROR_CPL_2040("The customer is already assigned to this SalesPosition in this time period. Please change the Start & End dates"),
	ERROR_CPL_2041("Mirror CR is not created"),
	ERROR_CPL_2042("Exception while updating CR Offline Status to Processing"),
	ERROR_CPL_2043("Unable to lock the Root customer for the Mirror"),
	ERROR_CPL_2044("Unable to lock the Affiliated customer for the Base"),
	ERROR_CPL_2045("Unable to lock the Affiliated customer for the Mirror"),
	ERROR_CPL_2046("Error while fetching Call Plan for Pull"),
	ERROR_CPL_2047("Error while fetching Customer Product Alignment for Pull"),
	ERROR_CPL_2048("Error finding mirror cr for pull"),
	ERROR_CPL_2049("Error while fetching Affiliated customer for Pull"),
	ERROR_CPL_2050("Error while fetching Mirrors for Pull Customers"),
	
	ERROR_CE_2080("The customer is already assigned to this SalesPosition in this time period. Please change the Start & End dates"),
	ERROR_CE_2081("Error while fetching Call Plan for Edit"),
	ERROR_CE_2082("Mirror CR is not created"),
	ERROR_CE_2083("Exception while updating CR Offline Status to Processing"),
	ERROR_CE_2084("Unable to lock the Root customer for the Mirror"),
	ERROR_CE_2085("Error while fetching Mirrors for Edit Customers"),
	
	ERROR_CR_3000("Error in creating valid CR line items for source and target customer alignments."),
	ERROR_CR_3001("Error in creating valid CR line items for Edit");
	
	ErrorCode(String error) {
		
	}
}
