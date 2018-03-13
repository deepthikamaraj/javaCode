package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TPrdAlgmntChngReqDetId Pojo. 
 *
 */
@Embeddable
public class TPrdAlgmntChngReqDetId implements Serializable {
	//default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;

		@NotNull
		@Column(name = "prd_algmnt_id", nullable = false, length = 255)
		private Long prdAlgmntId;
		@NotNull
		@Column(name = "chng_req_id", nullable = false, length = 255)
		private Long chngReqId;

		/**
		 *
		 * @generated
		 */
		public TPrdAlgmntChngReqDetId() {
		}

		/**
		 * 
		 * @generated
		 */
		public void setPrdAlgmntId(final Long prdAlgmntId) {
			this.prdAlgmntId = prdAlgmntId;
		}

		/**
		 * 
		 * @generated
		 */
		public Long getPrdAlgmntId() {
			return this.prdAlgmntId;
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
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (!(other instanceof TPrdAlgmntChngReqDetId)) {
				return false;
			}
			TPrdAlgmntChngReqDetId castOther = (TPrdAlgmntChngReqDetId) other;
			return (this.prdAlgmntId.equals(castOther.prdAlgmntId)) &&

			(this.chngReqId.equals(castOther.chngReqId))

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
			result = prime * result + ((prdAlgmntId == null) ? 0 : prdAlgmntId.hashCode());
			result = prime * result + ((chngReqId == null) ? 0 : chngReqId.hashCode());
			return result;
		}

		/**
		 * Returns a textual representation of a bean.
		 * 
		 * @generated
		 */
		public String toString() {

			final StringBuilder buffer = new StringBuilder();

			buffer.append("prdAlgmntId=[").append(prdAlgmntId).append("] ");
			buffer.append("chngReqId=[").append(chngReqId).append("] ");

			return buffer.toString();
		}
}
