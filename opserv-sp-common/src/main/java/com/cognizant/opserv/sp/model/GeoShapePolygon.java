package com.cognizant.opserv.sp.model;

import java.io.Serializable;

/**
 * ****************************************************************************.
 *
 * @class GeoShapePolygon
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class GeoShapePolygon extends SalesPosition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4066292336168859350L;
	
	private String shapePolygon;
	
	private String podTitle;

	/**
	 * Gets the shape polygon.
	 *
	 * @return the shape polygon
	 */
	public String getShapePolygon() {
		return shapePolygon;
	}

	/**
	 * Sets the shape polygon.
	 *
	 * @param shapePolygon the new shape polygon
	 */
	public void setShapePolygon(String shapePolygon) {
		this.shapePolygon = shapePolygon;
	}

	/**
	 * Gets the pod title.
	 *
	 * @return the pod title
	 */
	public String getPodTitle() {
		return podTitle;
	}

	/**
	 * Sets the pod title.
	 *
	 * @param podTitle the new pod title
	 */
	public void setPodTitle(String podTitle) {
		this.podTitle = podTitle;
	}

}
