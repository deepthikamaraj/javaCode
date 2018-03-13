package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class TWkflwStatusId implements Serializable{

       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
       @Column(name = "status_id", nullable = false)
       private Integer statusId;

       @NotNull
       @Column(name = "locale_id", nullable = false)
       private String localeId;

       public Integer getStatusId() {
              return statusId;
       }

       public void setStatusId(Integer statusId) {
              this.statusId = statusId;
       }

       public String getLocaleId() {
              return localeId;
       }

       public void setLocaleId(String localeId) {
              this.localeId = localeId;
       }

}

