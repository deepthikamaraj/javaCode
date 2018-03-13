package com.cognizant.opserv.sp.assembler;

import com.cognizant.opserv.sp.core.entity.TEmpAlgmnt;
import com.cognizant.opserv.sp.model.EmployeeAlignment;

/**
 * ****************************************************************************
 * Class Name: ProductAlignmentEntityAssembler for converting ProductAlignment Model Object dts to the TPrdAlgmnt Entity.
 *
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 1/4/2016
 * ***************************************************************************
 */
public class EmployeeAlignmentEntityAssembler {
	
	/**
	 * toEntity object.
	 *
	 * @param ProductAlignment the Product alignment Model Object dts
	 * @return the TPrdAlgmnt Entity Object dts
	 */
	public static TEmpAlgmnt convertEmpAlgmntModeltoEntity(EmployeeAlignment employeeAlignment) {
		
		TEmpAlgmnt tEmpAlgm = new TEmpAlgmnt();
		
		return tEmpAlgm;
		
	}
	
}
