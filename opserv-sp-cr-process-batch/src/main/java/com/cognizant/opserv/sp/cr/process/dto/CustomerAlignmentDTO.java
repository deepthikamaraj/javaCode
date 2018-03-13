package com.cognizant.opserv.sp.cr.process.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;

/*
 * Base_Source 		Mirror_Source_1		Mirror_Source_2		Other mirrors......		
 * 	||					||					||								
 * 	||	main_CR			||	mirrors			||	mirrors			
 * 	||					||					||			
 * VVVV
 * 
 * Base_Target
 */
public class CustomerAlignmentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -722325443191748090L;

	/**
	 * The Base source  customer alignment
	 */
	private CustomerAlignment sourceBaseCustAlgmt; // Base_source
	
		// TO
	
	/**
	 * The Ba source customer alignment
	 */
	private CustomerAlignment targetBaseCustAlgmt; // Base_Target
	
		// Generates this
	
	private ChangeRequest mainCR; 
	
	
	private List<CustomerAffiliation> allCustomerAffiliations;
	
	// Valid Affliated Customers
	private List<CustomerAlignment> sourceBaseAffCustAlgmts;
	
	//Customer Product Alignments
	private List<CustomerProductAlignment> sourceOldCustProdAlgmts;
    
    //Customer Product Alignments
    private List<CustomerProductAlignment> sourceUpdatedCustProdAlgmts; 

	//Affiliated Customer Product Alignments
	private List<CustomerProductAlignment> affCustProdAlgmts;
	
	// Mirrors to be handled. Valid ones.
	private List<MirrorCustAlgmtDTO> mirrors;
	
	// Mirrors which are errored and needn't to be processed wherever.
	private List<MirrorCustAlgmtDTO> invalidMirrors;
	
	private List<ErrorDTO> errors;
	
	private boolean exceptionOnError = true;
	
	private Long offlineReqId;
	
	private String errorCodeStr;
	
	//Comments
	private String comments;
	
	//Business Reason
	private BusinessReason businessReason;
	
	// All LineItems for Source in this CR
	private List<CustomerAlignmentChangeRequestDetails> crLineItemsForSource;
	
	// All LineItems for Target in this CR
	private List<CustomerAlignmentChangeRequestDetails> crLineItemsForTarget;	

	public CustomerAlignment getSourceBaseCustAlgmt() {
		return sourceBaseCustAlgmt;
	}

	public void setSourceBaseCustAlgmt(CustomerAlignment sourceBaseCustAlgmt) {
		this.sourceBaseCustAlgmt = sourceBaseCustAlgmt;
	}

	public CustomerAlignment getTargetBaseCustAlgmt() {
		return targetBaseCustAlgmt;
	}

	public void setTargetBaseCustAlgmt(CustomerAlignment targetBaseCustAlgmt) {
		this.targetBaseCustAlgmt = targetBaseCustAlgmt;
	}

	public ChangeRequest getMainCR() {
		return mainCR;
	}

	public void setMainCR(ChangeRequest mainCR) {
		this.mainCR = mainCR;
	}

	public List<CustomerAlignment> getSourceBaseAffCustAlgmts() {
		return sourceBaseAffCustAlgmts;
	}

	public void setSourceBaseAffCustAlgmts(
			List<CustomerAlignment> sourceBaseAffCustAlgmts) {
		this.sourceBaseAffCustAlgmts = sourceBaseAffCustAlgmts;
	}

	public List<MirrorCustAlgmtDTO> getMirrors() {
		return mirrors;
	}

	public void setMirrors(List<MirrorCustAlgmtDTO> mirrors) {
		this.mirrors = mirrors;
	}
	
	public void addMirrorDTO(MirrorCustAlgmtDTO mirror) {
		if(mirrors == null) {
			mirrors = new LinkedList<MirrorCustAlgmtDTO>();
		}
		if(!mirrors.contains(mirror)) {
			mirrors.add(mirror);
		}
	}
	
	
	public void removeMirrorDTO(MirrorCustAlgmtDTO mirror) {
		if(mirrors != null && mirrors.contains(mirror)) {
			mirrors.remove(mirror);
		}
	}

	public List<ErrorDTO> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDTO> errors) {
			this.errors = errors;
	}
	
	public void addErrorDTO(ErrorDTO error) {
		if(errors == null) {
			errors = new LinkedList<ErrorDTO>();
		}
		if(!errors.contains(error)) {
			errors.add(error);
		}
	}

	public boolean isExceptionOnError() {
		return exceptionOnError;
	}

	public void setExceptionOnError(boolean exceptionOnError) {
		this.exceptionOnError = exceptionOnError;
	}

	public Long getOfflineReqId() {
		return offlineReqId;
	}

	public void setOfflineReqId(Long offlineReqId) {
		this.offlineReqId = offlineReqId;
	}

	public List<CustomerAffiliation> getAllCustomerAffiliations() {
		return allCustomerAffiliations;
	}

	public void setAllCustomerAffiliations(
			List<CustomerAffiliation> allCustomerAffiliations) {
		this.allCustomerAffiliations = allCustomerAffiliations;
	}

	public List<MirrorCustAlgmtDTO> getInvalidMirrors() {
		return invalidMirrors;
	}

	public void setInvalidMirrors(List<MirrorCustAlgmtDTO> invalidMirrors) {
		this.invalidMirrors = invalidMirrors;
	}

	public List<CustomerProductAlignment> getSourceOldCustProdAlgmts() {
		return sourceOldCustProdAlgmts;
	}

	public void setSourceOldCustProdAlgmts(
			List<CustomerProductAlignment> sourceOldCustProdAlgmts) {
		this.sourceOldCustProdAlgmts = sourceOldCustProdAlgmts;
	}

	public List<CustomerProductAlignment> getSourceUpdatedCustProdAlgmts() {
		return sourceUpdatedCustProdAlgmts;
	}

	public void setSourceUpdatedCustProdAlgmts(
			List<CustomerProductAlignment> sourceUpdatedCustProdAlgmts) {
		this.sourceUpdatedCustProdAlgmts = sourceUpdatedCustProdAlgmts;
	}

	public List<CustomerProductAlignment> getAffCustProdAlgmts() {
		return affCustProdAlgmts;
	}

	public void setAffCustProdAlgmts(
			List<CustomerProductAlignment> affCustProdAlgmts) {
		this.affCustProdAlgmts = affCustProdAlgmts;
	}

	public void addInvalidMirrorDTO(MirrorCustAlgmtDTO mirror) {
		if(invalidMirrors == null) {
			invalidMirrors = new LinkedList<MirrorCustAlgmtDTO>();
		}
		if(!invalidMirrors.contains(mirror)) {
			invalidMirrors.add(mirror);
		}
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BusinessReason getBusinessReason() {
		return businessReason;
	}

	public void setBusinessReason(BusinessReason businessReason) {
		this.businessReason = businessReason;
	}

	public boolean hasErrors(List<ErrorCode> errCodes) {
		if (null != errCodes && !errCodes.isEmpty() && this.getErrors() != null && !this.getErrors().isEmpty()) {
			for (ErrorDTO err : this.getErrors()) {
				if (errCodes.contains(err.getErrorCode())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String getErrorCodeString() {
		errorCodeStr = "";
		for(ErrorDTO err : this.getErrors()) {
			errorCodeStr += err.getErrorCode() + ",";
		}
		return errorCodeStr;
	}

	public List<CustomerAlignmentChangeRequestDetails> getCrLineItemsForSource() {
		return crLineItemsForSource;
	}

	public void setCrLineItemsForSource(
			List<CustomerAlignmentChangeRequestDetails> crLineItemsForSource) {
		this.crLineItemsForSource = crLineItemsForSource;
	}

	public List<CustomerAlignmentChangeRequestDetails> getCrLineItemsForTarget() {
		return crLineItemsForTarget;
	}

	public void setCrLineItemsForTarget(
			List<CustomerAlignmentChangeRequestDetails> crLineItemsForTarget) {
		this.crLineItemsForTarget = crLineItemsForTarget;
	}
	
	
}


