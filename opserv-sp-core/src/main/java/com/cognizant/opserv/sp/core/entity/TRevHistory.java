package com.cognizant.opserv.sp.core.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;


@Entity
@RevisionEntity
@Table(name="t_rev_history")
public class TRevHistory implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@RevisionNumber
	@Column(name = "rev_id", nullable = false, length = 255)
	private Long revNumber;

	
	@RevisionTimestamp
    @Column(name ="timestamp")
    private Date revtimestamp;

	
}
