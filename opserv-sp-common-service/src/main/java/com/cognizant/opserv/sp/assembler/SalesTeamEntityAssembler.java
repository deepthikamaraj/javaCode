package com.cognizant.opserv.sp.assembler;

import com.cognizant.opserv.sp.core.entity.TSalesTeam;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public class SalesTeamEntityAssembler {
	
	public static TSalesTeam convertSalesTeamModeltoSalesTeamEntity(TSalesTeam tteam ,SalesTeam steam,UserDetails userDetails) {
		
		if(steam !=null)
		{
			//tteam.setTSalesTeamId(tSalesTeamId);
			tteam.setSalesTeamName(steam.getName());
			
		}
		
		
		return null;
		
	}
		
}
