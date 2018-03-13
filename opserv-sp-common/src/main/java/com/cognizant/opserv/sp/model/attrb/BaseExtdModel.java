package com.cognizant.opserv.sp.model.attrb;

import java.util.List;

import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class BaseExtdModel
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class BaseExtdModel extends BaseModel {

	private List<ExtdAttribue> extdAttributes;

	/**
	 * @return the extdAttributes
	 */
	public List<ExtdAttribue> getExtdAttributes() {
		return extdAttributes;
	}

	/**
	 * @param extdAttributes
	 *            the extdAttributes to set
	 */
	public void setExtdAttributes(List<ExtdAttribue> extdAttributes) {
		this.extdAttributes = extdAttributes;
	}

}
