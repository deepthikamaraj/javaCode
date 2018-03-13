/**
 * 
 */
package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.opserv.sp.common.AttributeType;
import com.cognizant.opserv.sp.core.entity.TBussAttr;
import com.cognizant.opserv.sp.model.FieldMetaData;
import com.cognizant.opserv.sp.model.ViewData;
import com.cognizant.opserv.sp.model.ViewHeader;
import com.cognizant.opserv.sp.model.ViewRowData;

/**
 * ***************************************************************************.
 *
 * @class SalesPositionViewAssembler - Mapper class for SalesPositionAssignments
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 12/05/2016
 * 
 * ***************************************************************************
 */
public class SalesPositionViewAssembler {

	/**
	 * @param bussAttrList
	 * @return ViewHeader
	 */
	public static ViewHeader convertTBussAttrToModel(List<TBussAttr> bussAttrList) {
		
		ViewHeader header = new ViewHeader();
		List<FieldMetaData> fieldMetaDataList = new ArrayList<FieldMetaData>();
		
		for (TBussAttr bussAttr : bussAttrList) {
			FieldMetaData metaData = new FieldMetaData();
			if (null != bussAttr.getAttrName()) {
				metaData.setName(bussAttr.getAttrName());
			}
			if (null != bussAttr.getAttrAliasName()) {
				metaData.setDisplayName(bussAttr.getAttrAliasName());
			}
			
			if (null != bussAttr.getAttrDataType()) {
				if(AttributeType.NUMBER.equals(bussAttr.getAttrDataType())){
					metaData.setType(AttributeType.NUMBER);
				}
				if(AttributeType.TEXT.equals(bussAttr.getAttrDataType())){
					metaData.setType(AttributeType.TEXT);
				}
				if(AttributeType.DATETIME.equals(bussAttr.getAttrDataType())){
					metaData.setType(AttributeType.DATETIME);
				}
				if(AttributeType.DATE.equals(bussAttr.getAttrDataType())){
					metaData.setType(AttributeType.DATE);
				}
				if(AttributeType.LIST.equals(bussAttr.getAttrDataType())){
					metaData.setType(AttributeType.LIST);
				}
				if(AttributeType.LONGTEXT.equals(bussAttr.getAttrDataType())){
					metaData.setType(AttributeType.LONGTEXT);
				}
				if(AttributeType.RADIO.equals(bussAttr.getAttrDataType())){
					metaData.setType(AttributeType.RADIO);
				}
			}
			if (null != bussAttr.getOrder()) {
				metaData.setOrder(bussAttr.getOrder());
			}
			if (null != bussAttr.getIsSearchable()) {
				if(bussAttr.getIsSearchable().equals('Y')){
					metaData.setSearchable(true);
				}
				if(bussAttr.getIsSearchable().equals('N')){
					metaData.setSearchable(false);
				}
			}
			if (null != bussAttr.getIsSortable()) {
				if(bussAttr.getIsSortable().equals('Y')){
					metaData.setSortable(true);
				}
				if(bussAttr.getIsSortable().equals('N')){
					metaData.setSortable(false);
				}
			}
			if (null != bussAttr.getViewType()) {
				metaData.setName(bussAttr.getViewType());
			}
			
			fieldMetaDataList.add(metaData);
		}

		header.setFields(fieldMetaDataList);
		return header;
	}
	

	/**
	 * @param objArrayList
	 * @param selectParameterList
	 * @param viewHeader
	 * @return List<ViewData>
	 */
	public static ViewData convertObjectArrayListToViewDataList(List<Object[]> objArrayList, List<String> selectParameterList, List<String> displayParameterList, ViewHeader viewHeader) {
		ViewData data = new ViewData();
		
		List<ViewRowData> dataList = new ArrayList<ViewRowData>();
		
		for (Object[] object : objArrayList) {
			ViewRowData rowData = new ViewRowData();
			int dataRowCount = 0;
			for (String displayParameter : displayParameterList) {
				if(null != object[dataRowCount] && null != displayParameter){
					rowData.addFieldData(displayParameter, object[dataRowCount].toString());
				}
				dataRowCount++;
			}
			dataList.add(rowData);	
		}
		
		data.setDataList(dataList);
		data.setHeaderData(viewHeader);
		
		return data;
	}

}
