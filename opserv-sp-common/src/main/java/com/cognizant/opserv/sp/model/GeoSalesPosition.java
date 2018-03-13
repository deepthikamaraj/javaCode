package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import java.util.List;
/**
 * ****************************************************************************.
 *
 * @class GeoSalesPosition
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class GeoSalesPosition extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6347351439492221175L;

	private SalesPosition salesPosition;

	private String podTitle;

	private String shapePolygon;

	private List<PostalCode> postalCodes;

	/**
	 * @return the podTitle
	 */
	public String getPodTitle() {
		return podTitle;
	}

	/**
	 * @param podTitle
	 *            the podTitle to set
	 */
	public void setPodTitle(String podTitle) {
		this.podTitle = podTitle;
	}

	/**
	 * @return the shapePolygon
	 */
	public String getShapePolygon() {
		return shapePolygon;
	}

	/**
	 * @param shapePolygon
	 *            the shapePolygon to set
	 */
	public void setShapePolygon(String shapePolygon) {
		this.shapePolygon = shapePolygon;
	}

	/**
	 * @return the postalCodes
	 */
	public List<PostalCode> getPostalCodes() {
		return postalCodes;
	}

	/**
	 * @param postalCodes
	 *            the postalCodes to set
	 */
	public void setPostalCodes(List<PostalCode> postalCodes) {
		this.postalCodes = postalCodes;
	}

	/**
	 * @return the salesPosition
	 */
	public SalesPosition getSalesPosition() {
		return salesPosition;
	}

	/**
	 * @param salesPosition
	 *            the salesPosition to set
	 */
	public void setSalesPosition(SalesPosition salesPosition) {
		this.salesPosition = salesPosition;
	}

}
