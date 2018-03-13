package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCustAlgmntChngReqDetId Pojo. 
 *
 */
@Embeddable
public class TCustAlgmntChngReqDetId implements Serializable {
	//default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;

		@NotNull
		@Column(name = "chng_req_id", nullable = false, length = 255)
		private Long chngReqId;
		@NotNull
		@Column(name = "cust_algmnt_id", nullable = false, length = 255)
		private Long custAlgmntId;

		/**
		 *
		 * @generated
		 */
		public TCustAlgmntChngReqDetId() {
		}

		/**
		 * 
		 * @generated
		 */
		public void setChngReqId(final Long chngReqId) {
			this.chngReqId = chngReqId;
		}

		/**
		 * 
		 * @generated
		 */
		public Long getChngReqId() {
			return this.chngReqId;
		}

		/**
		 * 
		 * @generated
		 */
		public void setCustAlgmntId(final Long custAlgmntId) {
			this.custAlgmntId = custAlgmntId;
		}

		/**
		 * 
		 * @generated
		 */
		public Long getCustAlgmntId() {
			return this.custAlgmntId;
		}

		/**
		 * 
		 * @generated
		 */
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (!(other instanceof TCustAlgmntChngReqDetId)) {
				return false;
			}
			TCustAlgmntChngReqDetId castOther = (TCustAlgmntChngReqDetId) other;
			return (this.chngReqId.equals(castOther.chngReqId)) &&

			(this.custAlgmntId.equals(castOther.custAlgmntId))

			;

		}

		/**
		 * @generated
		 * 
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((chngReqId == null) ? 0 : chngReqId.hashCode());
			result = prime * result + ((custAlgmntId == null) ? 0 : custAlgmntId.hashCode());
			return result;
		}

		/**
		 * Returns a textual representation of a bean.
		 * 
		 * @generated
		 */
		public String toString() {

			final StringBuilder buffer = new StringBuilder();

			buffer.append("chngReqId=[").append(chngReqId).append("] ");
			buffer.append("custAlgmntId=[").append(custAlgmntId).append("] ");

			return buffer.toString();
		}
}
