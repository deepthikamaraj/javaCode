package com.cognizant.opserv.sp.cr.process.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;

public class GeographyAlignmentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7609203096244343100L;

	private GeographyAlignment sourceGeoAlgmt;

	private GeographyAlignment targetGeoAlgmt;

	private ChangeRequest mainCR;

	private List<MirrorGeoAlgmtDTO> mirrors = new LinkedList<MirrorGeoAlgmtDTO>();

	private List<CustomerAlignmentDTO> custAlgmtDTOs = new LinkedList<CustomerAlignmentDTO>();

	private List<ErrorDTO> errors = new LinkedList<ErrorDTO>();

	private boolean exceptionOnError = true;
	
	private String errorCodeStr;

	private Long offlineReqId;
	
	//Comments
	private String comments;
	
	//Business Reason
	private BusinessReason businessReason;

	public void addMirrorDTO(MirrorGeoAlgmtDTO mirror) {
		if (!mirrors.contains(mirror)) {
			mirrors.add(mirror);
		}
	}

	public void addErrorDTO(ErrorDTO error) {
		if (!errors.contains(error)) {
			errors.add(error);
		}
	}

	public GeographyAlignment getSourceGeoAlgmt() {
		return sourceGeoAlgmt;
	}

	public void setSourceGeoAlgmt(GeographyAlignment sourceGeoAlgmt) {
		this.sourceGeoAlgmt = sourceGeoAlgmt;
	}

	public GeographyAlignment getTargetGeoAlgmt() {
		return targetGeoAlgmt;
	}

	public void setTargetGeoAlgmt(GeographyAlignment targetGeoAlgmt) {
		this.targetGeoAlgmt = targetGeoAlgmt;
	}

	public ChangeRequest getMainCR() {
		return mainCR;
	}

	public void setMainCR(ChangeRequest mainCR) {
		this.mainCR = mainCR;
	}

	public List<MirrorGeoAlgmtDTO> getMirrors() {
		return mirrors;
	}

	public void setMirrors(List<MirrorGeoAlgmtDTO> mirrors) {
		this.mirrors = mirrors;
	}

	public List<CustomerAlignmentDTO> getCustAlgmtDTOs() {
		return custAlgmtDTOs;
	}

	public void setCustAlgmtDTOs(List<CustomerAlignmentDTO> custAlgmtDTOs) {
		this.custAlgmtDTOs = custAlgmtDTOs;
	}

	public List<ErrorDTO> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDTO> errors) {
		this.errors = errors;
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

}
