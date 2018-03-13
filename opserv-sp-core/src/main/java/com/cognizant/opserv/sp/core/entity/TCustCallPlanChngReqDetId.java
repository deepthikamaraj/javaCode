package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCustCallPlanChngReqDetId Pojo. 
 *
 */
@Embeddable
public class TCustCallPlanChngReqDetId implements Serializable {
	//default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;

		@NotNull
		@Column(name = "chng_req_id", nullable = false, length = 255)
		private Long chngReqId;
		@NotNull
		@Column(name = "cust_call_plan_id", nullable = false, length = 255)
		private Integer custCallPlanId;

		/**
		 *
		 * @generated
		 */
		public TCustCallPlanChngReqDetId() {
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
		public void setCustCallPlanId(final Integer custCallPlanId) {
			this.custCallPlanId = custCallPlanId;
		}

		/**
		 * 
		 * @generated
		 */
		public Integer getCustCallPlanId() {
			return this.custCallPlanId;
		}

		/**
		 * 
		 * @generated
		 */
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (!(other instanceof TCustCallPlanChngReqDetId)) {
				return false;
			}
			TCustCallPlanChngReqDetId castOther = (TCustCallPlanChngReqDetId) other;
			return (this.chngReqId.equals(castOther.chngReqId)) &&

			(this.custCallPlanId.equals(castOther.custCallPlanId))

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
			result = prime * result + ((custCallPlanId == null) ? 0 : custCallPlanId.hashCode());
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
			buffer.append("custCallPlanId=[").append(custCallPlanId).append("] ");

			return buffer.toString();
		}
}
