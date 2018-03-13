package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TSalesPosGeo 
 * The corresponding mapping table is t_sales_pos_geo 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesPosGeos", query = "select myTSalesPosGeo from TSalesPosGeo myTSalesPosGeo"),
		@NamedQuery(name = "CountTSalesPosGeoBySalPos", query = "Select Count(c.tSalesPosGeoId.salesPosId) from TSalesPosGeo c where c.tSalesPosGeoId.salesPosId =?1 AND c.tSalesPosGeoId.salesHierId = ?2"),
		@NamedQuery(name = "CountTSalesPosGeos", query = "Select Count(c) from TSalesPosGeo c") })
@Table(name = "t_sales_pos_geo")
public class TSalesPosGeo implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TSalesPosGeoId tSalesPosGeoId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "prn_sales_pos_id", nullable = true, length = 255)
	private Long prnSalesPosId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "prn_sales_hier_id", nullable = true, length = 255)
	private Long prnSalesHierId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "shape_polygon", nullable = false, length = 255)
	private byte[] shapePolygon;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "root_hier_id", nullable = true, length = 255)
	private Integer rootHierId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "pos_name", nullable = true, length = 75)
	private String posName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "pod_title", nullable = true, length = 75)
	private String podTitle;

	/**
	 *
	 * @generated
	 */
	public TSalesPosGeo() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTSalesPosGeoId(final TSalesPosGeoId tSalesPosGeoId) {
		this.tSalesPosGeoId = tSalesPosGeoId;
	}

	/**
	 * 
	 * @generated
	 */
	public TSalesPosGeoId getTSalesPosGeoId() {
		return this.tSalesPosGeoId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrnSalesPosId(final Long prnSalesPosId) {
		this.prnSalesPosId = prnSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getPrnSalesPosId() {
		return this.prnSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrnSalesHierId(final Long prnSalesHierId) {
		this.prnSalesHierId = prnSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getPrnSalesHierId() {
		return this.prnSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setShapePolygon(final byte[] shapePolygon) {
		this.shapePolygon = shapePolygon;
	}

	/**
	 * 
	 * @generated
	 */
	public byte[] getShapePolygon() {
		return this.shapePolygon;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRootHierId(final Integer rootHierId) {
		this.rootHierId = rootHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRootHierId() {
		return this.rootHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPosName(final String posName) {
		this.posName = posName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPosName() {
		return this.posName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPodTitle(final String podTitle) {
		this.podTitle = podTitle;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPodTitle() {
		return this.podTitle;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesPosGeo that) {
		setTSalesPosGeoId(that.getTSalesPosGeoId());
		setPrnSalesPosId(that.getPrnSalesPosId());
		setPrnSalesHierId(that.getPrnSalesHierId());
		setShapePolygon(that.getShapePolygon());
		setRootHierId(that.getRootHierId());
		setPosName(that.getPosName());
		setPodTitle(that.getPodTitle());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tSalesPosGeoId == null) ? 0 : tSalesPosGeoId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tSalesPosGeoId=[").append(tSalesPosGeoId).append("] ");
		buffer.append("prnSalesPosId=[").append(prnSalesPosId).append("] ");
		buffer.append("prnSalesHierId=[").append(prnSalesHierId).append("] ");
		buffer.append("shapePolygon=[").append(shapePolygon).append("] ");
		buffer.append("rootHierId=[").append(rootHierId).append("] ");
		buffer.append("posName=[").append(posName).append("] ");
		buffer.append("podTitle=[").append(podTitle).append("] ");

		return buffer.toString();
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TSalesPosGeo other = (TSalesPosGeo) obj;
		if (tSalesPosGeoId == null) {
			if (other.tSalesPosGeoId != null) {
				return false;
			}
		} else if (!tSalesPosGeoId.equals(other.tSalesPosGeoId)) {
			return false;
		}
		return true;
	}
}
