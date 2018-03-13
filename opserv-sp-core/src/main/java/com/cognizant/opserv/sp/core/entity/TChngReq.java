package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * JPA class representing TChngReq The corresponding mapping table is t_chng_req
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqs", query = "select myTChngReq from TChngReq myTChngReq"),
		@NamedQuery(name = "CountTChngReqs", query = "Select Count(c) from TChngReq c"),
		@NamedQuery(name = "FindTChngReqByTChngReqConfig", query = "select myTChngReq from TChngReq myTChngReq where myTChngReq.tChngReqConfig = ?1 "),
		@NamedQuery(name = "CountTChngReqsByTChngReqConfig", query = "select Count(myTChngReq) from TChngReq myTChngReq where myTChngReq.tChngReqConfig = ?1 "),
		@NamedQuery(name = "CountTChngReqsBySPandStatus", query = "select Count(myTChngReq) from TChngReq myTChngReq where (myTChngReq.reqSalesPosId in (?1) or myTChngReq.raiseSalesPosId in (?1)) and myTChngReq.statusId = ?2 and myTChngReq.tenantId = ?3 and myTChngReq.activeFlag='Y' "),
//		@NamedQuery(name = "GetSalePositionReqStatusBySP", query = "select spchngreq.statusId from TChngReq tchngReq INNER JOIN tchngReq.tChngReqDetailss chngreqdet INNER JOIN chngreqdet.tChngReqTrigger reqtrig INNER JOIN chngreqdet.tSalesPosChngReqDets spchngreq where tchngReq.reqSalesPosId = ?1 and spchngreq.tenantId = ?2 "),
		@NamedQuery(name = "getChangeRequestByStatus", query = "select myTChngReq from TChngReq myTChngReq where myTChngReq.reqSalesPosId = ?1 and myTChngReq.reqSalesHierId = ?2 and myTChngReq.statusId in (?3, 10) and myTChngReq.triggerId = ?4 and myTChngReq.tenantId = ?5 and myTChngReq.activeFlag='Y' and myTChngReq.raiseSalesPosId = ?6 and myTChngReq.raiseSalesHierId = ?7"),
		@NamedQuery(name = "getChangeRequestByStatusForSubmit", query = "select myTChngReq from TChngReq myTChngReq where myTChngReq.reqSalesPosId = ?1 and myTChngReq.reqSalesHierId = ?2 and myTChngReq.statusId in (?3) and myTChngReq.triggerId = ?4 and myTChngReq.tenantId = ?5 and myTChngReq.activeFlag='Y' and myTChngReq.raiseSalesPosId = ?6 and myTChngReq.raiseSalesHierId = ?7"),
		@NamedQuery(name = "getChangeRequestIdByStatus", query = "select myTChngReq.chngReqId from TChngReq myTChngReq where myTChngReq.reqSalesPosId = ?1 and myTChngReq.reqSalesHierId = ?2 and myTChngReq.statusId in (?3, 10) and myTChngReq.triggerId = ?4 and myTChngReq.tenantId = ?5 and myTChngReq.activeFlag='Y' and myTChngReq.raiseSalesPosId = ?6 and myTChngReq.raiseSalesHierId = ?7"),
		// @NamedQuery(name = "getChangeRequestByStatus", query =
		// "select myTChngReq from TChngReq myTChngReq join fetch myTChngReq.tChngReqConfig myTChngReqConfig join fetch myTChngReqConfig.tAlgmntSalesTeam myTAlgmntSalesTeam where myTChngReq.reqSalesPosId = ?1 and myTChngReq.reqSalesHierId = ?2 and myTChngReq.statusId= ?3 and myTChngReq.triggerId = ?4 and myTChngReq.tenantId = ?5 "),

//		@NamedQuery(name = "GetTCustAlgn", query = " SELECT myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId, myTChngReqDetail.tChngReqDetailId.chngReqDetailId,myTCustAlgmnt.tCust.custId,myTCust.custName,myTChngReq.submitDtTm,myTChngReq.bussReason,myTChngReq.comments,myTSalesPos.posName,myTCustAlgmnt.custAlgmntId,myTChngReq.triggerId,myTCustType.typeName,myTPrtType.prtTypeDesc,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt from TChngReqDetail  myTChngReqDetail , TCustAlgmntChngReqDet myTCustAlgmntChngReqDet, TCustAlgmnt  myTCustAlgmnt,TCust  myTCust,TCustType myTCustType,TPrtType myTPrtType,TChngReq myTChngReq,TSalesPos myTSalesPos  where myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=?1 and  "
//				+ " myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId and  myTChngReqDetail.tChngReqDetailId.chngReqDetailId=myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId "
//				+ " and myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.custAlgmntId = myTCustAlgmnt.custAlgmntId and myTCust.custId = myTCustAlgmnt.tCust.custId  and myTChngReq.chngReqId=myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId and myTSalesPos.tSalesPosId.salesPosId=myTChngReq.reqSalesPosId and myTSalesPos.tAlgmntSalesHier.salesHierId=myTChngReq.reqSalesHierId and myTCustType.tCustTypeId.custTypeId = myTCust.custTypeId "
//				+ " and myTPrtType.tPrtTypeId.prtTypeId =myTCust.prtTypeId and myTChngReqDetail.tenantId=?2  and myTCustType.tCustTypeId.localeId=?3 and myTChngReqDetail.tenantId=myTChngReq.tenantId "
//				+ " and myTChngReqDetail.tenantId=myTCustAlgmntChngReqDet.tenantId and myTChngReqDetail.tenantId=myTCustAlgmnt.tenantId and myTChngReqDetail.tenantId=myTCust.tenantId and myTChngReqDetail.tenantId=myTSalesPos.tenantId "),
//		@NamedQuery(name = "GetTCustAffltn", query = "SELECT DISTINCT(myTChngReqDetail.tChngReqDetailId.chngReqDetailId),myTChngReq.chngReqId,myTCustAffChngReqDet.tCustAff.tCustByAffCustId.custId, myTCustAff.custAffId,myTCust.custName,myTChngReq.submitDtTm,myTChngReq.bussReason,myTChngReq.comments,myTSalesPos.posName,myTChngReq.triggerId,myTChngReq.chngReqNum,myTCust.custFirstName,myTCust.custMiddleName,myTCust.custLastName FROM TChngReqAppr myTChngReqAppr,TChngReq myTChngReq,TChngReqDetail myTChngReqDetail,TCustAff myTCustAff,TCustAffChngReqDet myTCustAffChngReqDet,TCust myTCust,TSalesPos myTSalesPos "
//				+ " where myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=myTCustAffChngReqDet.tCustAffChngReqDetId.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId and myTChngReqDetail.tChngReqDetailId.chngReqDetailId=myTCustAffChngReqDet.tCustAffChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId and  myTCustAff.custAffId=myTCustAffChngReqDet.tCustAff.custAffId and myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=?1 and myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=myTChngReq.chngReqId "
//				+ "and myTSalesPos.tSalesPosId.salesPosId=myTChngReq.reqSalesPosId and myTSalesPos.tSalesPosId.salesHierId=myTChngReq.reqSalesHierId and myTCust.custId = myTCustAff.tCustByAffCustId.custId  and myTChngReqDetail.tenantId=?2  and myTChngReqDetail.tenantId=myTChngReq.tenantId and myTChngReqDetail.tenantId=myTCustAff.tenantId and myTChngReqDetail.tenantId=myTCustAffChngReqDet.tenantId and myTChngReqDetail.tenantId=myTCust.tenantId and myTChngReqDetail.tenantId=myTSalesPos.tenantId  and myTChngReqAppr.tChngReqDetail.tChngReqDetailId.chngReqDetailId=myTChngReqDetail.tChngReqDetailId.chngReqDetailId and myTChngReqAppr.targetApprFlag = 'Y' and myTChngReqAppr.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId"),
//		@NamedQuery(name = "GetTSalesPostn", query = "select DISTINCT(myTChngReqDetail.tChngReqDetailId.chngReqDetailId),myTChngReq.chngReqId,myTSalesPosChngReqDet.salesPosId,myTSalesPosChngReqDet.salesHierId,myTSalesPos.posName,myTChngReq.submitDtTm,myTChngReq.bussReason,myTChngReq.comments,myTSalesPosChngReqDet.spChngReqId,myTSalesPos.effStartDt,myTSalesPos.effEndDt,myTSalesPos.posCd,myTChngReq.triggerId,myTChngReq.chngReqNum "
//				+ " from TChngReqAppr myTChngReqAppr, TChngReq myTChngReq,TChngReqDetail  myTChngReqDetail,TSalesPos myTSalesPos,TSalesPosChngReqDet myTSalesPosChngReqDet "
//				+ " where myTChngReq.chngReqId = myTSalesPosChngReqDet.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId and myTChngReqDetail.tChngReqDetailId.chngReqDetailId = myTSalesPosChngReqDet.tChngReqDetail.tChngReqDetailId.chngReqDetailId and myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId = myTSalesPosChngReqDet.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId and "
//				+ "  myTSalesPos.tSalesPosId.salesPosId = myTSalesPosChngReqDet.salesPosId and myTSalesPos.tSalesPosId.salesHierId = myTSalesPosChngReqDet.salesHierId and myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=?1  "
//				+ " and myTChngReq.tenantId=?2 and myTChngReqDetail.tenantId = myTChngReq.tenantId and myTSalesPos.tenantId = myTChngReqDetail.tenantId and myTSalesPosChngReqDet.tenantId = myTChngReqDetail.tenantId and myTChngReqAppr.tChngReqDetail.tChngReqDetailId.chngReqDetailId=myTChngReqDetail.tChngReqDetailId.chngReqDetailId and myTChngReqAppr.targetApprFlag = 'Y' and myTChngReqAppr.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId"),
//		@NamedQuery(name = "GetTPrdDetail", query = " SELECT DISTINCT(myTChngReqDetail.tChngReqDetailId.chngReqDetailId),myTChngReq.chngReqId,myTPrd.prdId,myTPrd.prdName,myTSalesPos.posName,myTPrdAlgmnt.prdAlgmntId,myTChngReq.submitDtTm,myTChngReq.bussReason,myTChngReq.comments,myTPrdAlgmnt.effStartDt,myTPrdAlgmnt.effEndDt,myTChngReq.triggerId,myTPrd.prdCd,myTChngReq.chngReqNum FROM TChngReqAppr myTChngReqAppr, TChngReq myTChngReq,TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet,TChngReqDetail myTChngReqDetail,TPrdAlgmnt myTPrdAlgmnt,TSalesPos myTSalesPos, TPrd myTPrd"
//				+ " WHERE  myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId =myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId AND myTChngReqDetail.tChngReqDetailId.chngReqDetailId =myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId AND myTPrdAlgmntChngReqDet.tPrdAlgmnt.prdAlgmntId = myTPrdAlgmnt.prdAlgmntId AND myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId = ?1 and myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=myTChngReq.chngReqId "
//				+ "and myTSalesPos.tSalesPosId.salesPosId=myTChngReq.reqSalesPosId and myTSalesPos.tSalesPosId.salesHierId=myTChngReq.reqSalesHierId and myTPrd.prdId=myTPrdAlgmnt.tPrd.prdId and myTChngReqDetail.tenantId=?2 and myTChngReqDetail.tenantId=myTChngReq.tenantId and myTChngReqDetail.tenantId=myTPrdAlgmntChngReqDet.tenantId and myTChngReqDetail.tenantId=myTPrdAlgmnt.tenantId and myTChngReqDetail.tenantId=myTSalesPos.tenantId and myTChngReqDetail.tenantId=myTPrd.tenantId and myTChngReqAppr.tChngReqDetail.tChngReqDetailId.chngReqDetailId=myTChngReqDetail.tChngReqDetailId.chngReqDetailId and myTChngReqAppr.targetApprFlag = 'Y' and myTChngReqAppr.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId "),

		@NamedQuery(name = "fetchAllMyChngRequest", query = "select tcr.chngReqId, tcr.createDt, tcr.updateDt, tcr.submittedBy, tf.featureName, tft.typeDesc, tws.statusName, tws.tWkflwStatusId.statusId,tcr.triggerId,tpers.firstName,tpers.middleName,tpers.lastName,tsp.posCd,tcr.submitDtTm,tsp.effEndDt,tws.statusDesc,tf.featureDesc,tcr.chngReqNum from TWkflwStatus tws,TChngReq tcr,TChngReqFeature tf, TChngReqTrigger tcrt,TFeatureType tft,TPers tpers,TSalesPos tsp,TChngReqNoteType myTChngReqNoteType,TNoteType  myTNoteType  where tsp.tAlgmntSalesHier.salesHierId=tcr.lastSalesHierId and tsp.salesPosId=tcr.lastSalesPosId and tpers.userId=tcr.updatedBy and tcr.reqSalesPosId =?1 and tcr.statusId = tws.tWkflwStatusId.statusId and tcr.triggerId = tcrt.triggerId and tcrt.typeId = tft.typeId and tcrt.featureId = tf.featureId AND tws.tenantId = ?2 and tws.tenantId = tcr.tenantId AND tcrt.tenantId = tft.tenantId and tft.tenantId = tsp.tenantId and tsp.tenantId = tpers.tenantId and tws.tWkflwStatusId.localeId = ?3 and myTChngReqNoteType.tChngReqNoteTypeId.chngReqConfigId = tcr.chngReqConfigId and " 
				+ " myTChngReqNoteType.tChngReqNoteTypeId.noteTypeId =  myTNoteType.tNoteTypeId.noteTypeId and myTNoteType.noteTypeName in ('DashBoard','Both')  and tcr.activeFlag='Y' and tcr.activeFlag = myTChngReqNoteType.activeFlag ORDER BY tcr.updateDt DESC"),


		@NamedQuery(name = "fetchChngRequestBasedOnRole", query = "select tcr.chngReqId, tsp.posCd, tws.statusName, tcr.updateDt, tf.featureName, tft.typeDesc,tws.tWkflwStatusId.statusId, tcr.triggerId,tpers.firstName,tpers.middleName,tpers.lastName,tws.statusDesc,tf.featureDesc,tcr.chngReqNum from TWkflwStatus tws,TChngReq tcr,TChngReqFeature tf, TChngReqTrigger tcrt,TFeatureType tft,TSalesPos tsp,TPers tpers where tsp.tAlgmntSalesHier.salesHierId=tcr.lastSalesHierId and tsp.salesPosId=tcr.lastSalesPosId and tpers.userId=tcr.updatedBy and tcr.reqSalesPosId =?1 and tcr.reqSalesHierId =?2 and tcr.statusId in ?3 and tcr.statusId = tws.tWkflwStatusId.statusId and tcr.triggerId = tcrt.triggerId and tcrt.typeId = tft.typeId and tcrt.featureId = tf.featureId AND tws.tenantId = ?5 and tws.tenantId = tcr.tenantId AND tcrt.tenantId = tft.tenantId and tft.tenantId = tsp.tenantId and tsp.tenantId = tpers.tenantId and tws.tWkflwStatusId.localeId = ?4 ORDER BY tcr.updateDt DESC "),

		// @NamedQuery(name = "FindApprCRBySalesPosID", query =
		// "select distinct tcr.chngReqId,tcr.createDt,tcr.updatedBy,tcr.updateDt,tf.featureName,tft.typeDesc,tws.statusName,tpers.firstName,tpers.middleName,tpers.lastName,tsp.posCd,tcr.submitDtTm,tf.featureDesc,(select COUNT(distinct myTChngReqAppr.tChngReqDetail.tChngReqDetailId.chngReqDetailId) from TChngReqAppr myTChngReqAppr where myTChngReqAppr.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId = tcr.chngReqId  and myTChngReqAppr.statusId in ?3  AND myTChngReqAppr.apprSalesPosId= ?1 and myTChngReqAppr.apprSalesHierId= ?2  and myTChngReqAppr.tenantId = ?4 and  myTChngReqAppr.activeFlag='Y'  GROUP BY myTChngReqAppr.targetApprFlag ORDER BY myTChngReqAppr.updateDt DESC )as transCount from TChngReqAppr tcra,TChngReq tcr ,TChngReqTrigger tcrt,TFeature tf,TFeatureType tft,TWkflwStatus tws,TPers tpers,TSalesPos tsp where tcra.apprSalesPosId=?1 and tcra.apprSalesHierId=?2 and tcra.statusId in ?3 and tcra.tChngReqApprId.tChngReq.chngReqId=tcr.chngReqId and tcr.triggerId=tcrt.triggerId and tcrt.tFeature.featureId=tf.featureId and tcrt.typeId=tft.typeId and tcra.statusId=tws.tWkflwStatusId.statusId and tsp.tSalesPosId.salesHierId=tcr.lastSalesHierId and tsp.tSalesPosId.salesPosId=tcr.lastSalesPosId and tpers.tUsr.usrId=tcr.updatedBy and tcr.tenantId=?4 and tws.tenantId=tcr.tenantId and  tws.tWkflwStatusId.localeId=?5 ORDER BY tcr.updateDt DESC"),
//		@NamedQuery(name = "GetTCustTransDetails", query = " SELECT DISTINCT(myTChngReqDetail.tChngReqDetailId.chngReqDetailId),myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId,myTCustAlgmnt.tCust.custCd,myTCust.custName,myTChngReq.submitDtTm,myTChngReq.bussReason,myTChngReq.comments,myTSalesPos.posName,myTCustAlgmnt.custAlgmntId,myTChngReq.triggerId,myTCustAlgmnt.targetFlag,myTChngReq.chngReqNum,myTCust.custFirstName,myTCust.custMiddleName,myTCust.custLastName from TChngReqAppr myTChngReqAppr,TChngReqDetail  myTChngReqDetail , TCustAlgmntChngReqDet myTCustAlgmntChngReqDet, TCustAlgmnt  myTCustAlgmnt,TCust  myTCust,TChngReq myTChngReq,TSalesPos myTSalesPos  where myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=?1 and  "
//				+ " myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId and  myTChngReqDetail.tChngReqDetailId.chngReqDetailId=myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId "
//				+ " and myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.custAlgmntId = myTCustAlgmnt.custAlgmntId and myTCust.custId = myTCustAlgmnt.tCust.custId  and myTChngReq.chngReqId=myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId and myTSalesPos.tSalesPosId.salesPosId=myTChngReq.reqSalesPosId and myTSalesPos.tAlgmntSalesHier.salesHierId=myTChngReq.reqSalesHierId and myTChngReqDetail.tenantId=?2  and myTChngReqDetail.tenantId=myTChngReq.tenantId "
//				+ "and myTChngReqDetail.tenantId=myTCustAlgmntChngReqDet.tenantId and myTChngReqDetail.tenantId=myTCustAlgmnt.tenantId and myTChngReqDetail.tenantId=myTCust.tenantId and myTChngReqDetail.tenantId=myTSalesPos.tenantId and myTChngReqAppr.tChngReqDetail.tChngReqDetailId.chngReqDetailId=myTChngReqDetail.tChngReqDetailId.chngReqDetailId and myTChngReqAppr.targetApprFlag = 'Y' and myTChngReqAppr.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId "),
//		@NamedQuery(name = "GetTCustAlgnByTargetFlag", query = " SELECT  myTChngReqDetail.tChngReqDetailId.chngReqDetailId,myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId,myTCustAlgmnt.tCust.custCd,myTCust.custName,myTChngReq.submitDtTm,myTChngReq.bussReason,myTChngReq.comments,myTSalesPos.posName,myTCustAlgmnt.custAlgmntId,myTChngReq.triggerId,myTCustAlgmnt.targetFlag,myTChngReq.chngReqNum,myTCust.custFirstName,myTCust.custMiddleName,myTCust.custLastName from TChngReqDetail  myTChngReqDetail , TCustAlgmntChngReqDet myTCustAlgmntChngReqDet, TCustAlgmnt  myTCustAlgmnt,TCust  myTCust,TChngReq myTChngReq,TSalesPos myTSalesPos  where myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=?1 and  "
//				+ " myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId and  myTChngReqDetail.tChngReqDetailId.chngReqDetailId=myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId "
//				+ " and myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.custAlgmntId = myTCustAlgmnt.custAlgmntId and myTCust.custId = myTCustAlgmnt.tCust.custId  and myTChngReq.chngReqId=myTChngReqDetail.tChngReqDetailId.tChngReq.chngReqId and myTSalesPos.tSalesPosId.salesPosId=myTChngReq.reqSalesPosId and myTSalesPos.tAlgmntSalesHier.salesHierId=myTChngReq.reqSalesHierId and myTChngReqDetail.tenantId=?2  and myTChngReqDetail.tenantId=myTChngReq.tenantId "
//				+ "and myTChngReqDetail.tenantId=myTCustAlgmntChngReqDet.tenantId and myTChngReqDetail.tenantId=myTCustAlgmnt.tenantId and myTChngReqDetail.tenantId=myTCust.tenantId and myTChngReqDetail.tenantId=myTSalesPos.tenantId and myTChngReqDetail.tChngReqDetailId.chngReqDetailId in ?3 "),
//		@NamedQuery(name = "GetTZipDetailsByTargetFlag", query = "select myTChngReq.chngReqId,myTChngReq.submitDtTm,myTChngReq.bussReason,myTChngReq.comments,myTChngReq.triggerId, myTTerrZipAlgmntChngReqDet.zipChngReqId,myTTerrZipAlgmntChngReqDet.reqDetail,myTSalesPos.posName,myTChngReq.chngReqNum from TChngReq myTChngReq,TTerrZipAlgmntChngReqDet myTTerrZipAlgmntChngReqDet,TChngReqDetail myTChngReqDetail,TSalesPos myTSalesPos where myTChngReq.chngReqId=myTTerrZipAlgmntChngReqDet.tChngReq.chngReqId and  myTSalesPos.tSalesPosId.salesPosId=myTChngReq.reqSalesPosId and myTSalesPos.tAlgmntSalesHier.salesHierId=myTChngReq.reqSalesHierId and "
//				+ " myTChngReqDetail.tChngReq.chngReqId=myTChngReq.chngReqId and  myTChngReq.tenantId=myTChngReqDetail.tenantId and myTTerrZipAlgmntChngReqDet.tenantId=myTChngReqDetail.tenantId and myTChngReq.chngReqId=?1 and myTChngReq.tenantId=?2 and  myTChngReqDetail.tChngReqDetailId.chngReqDetailId in ?3 and myTChngReqDetail.tChngReqTrigger.triggerId= ?4 "),
//		@NamedQuery(name = "GetTZipDetail", query = "select myTChngReq.chngReqId,myTChngReq.submitDtTm,myTChngReq.bussReason,myTChngReq.comments,myTChngReq.triggerId, myTTerrZipAlgmntChngReqDet.zipChngReqId,myTTerrZipAlgmntChngReqDet.reqDetail,myTSalesPos.posName,myTChngReq.chngReqNum from TChngReq myTChngReq,TTerrZipAlgmntChngReqDet myTTerrZipAlgmntChngReqDet,TChngReqDetail myTChngReqDetail,TSalesPos myTSalesPos where myTChngReq.chngReqId=myTTerrZipAlgmntChngReqDet.tChngReq.chngReqId and myTSalesPos.tSalesPosId.salesPosId=myTChngReq.reqSalesPosId and myTSalesPos.tAlgmntSalesHier.salesHierId=myTChngReq.reqSalesHierId and  "
//				+ "  myTTerrZipAlgmntChngReqDet.tenantId=myTChngReqDetail.tenantId and myTChngReq.chngReqId=?1 and myTChngReq.tenantId= ?3 and myTChngReqDetail.tChngReqTrigger.triggerId= ?2 "),
		@NamedQuery(name = "myRequestCount", query = "select count(tchngReq) from TChngReq tchngReq where tchngReq.reqSalesPosId in ?1 and tchngReq.tenantId = ?2 and tchngReq.activeFlag='Y' "),
		@NamedQuery(name = "fetchInactiveCRs", query = "select tcr.chngReqId ,tws.statusName from TWkflwStatus tws,TChngReq tcr,TSalesPos tpos where tcr.reqSalesPosId =tpos.salesPosId and tcr.reqSalesHierId=tpos.tAlgmntSalesHier.salesHierId  and tcr.statusId = tws.tWkflwStatusId.statusId and tcr.activeFlag='Y'  and  tws.tenantId = ?1 and tws.tenantId = tcr.tenantId  and tcr.tenantId = tpos.tenantId AND ( tpos.effEndDt<=?2 OR tpos.activeFlag='N')"),
		/**
		 * 
		 * @author 409793 query to find CR requesting SP EFFECTIVE_END_DATE
		 */
		@NamedQuery(name = "FindCRReqSPEndDt", query = "select myTSalesPos.effEndDt from TChngReq myTChngReq,TSalesPos myTSalesPos where myTChngReq.chngReqId = ?1 and myTChngReq.tenantId= ?2 and myTChngReq.reqSalesPosId = myTSalesPos.salesPosId and myTChngReq.reqSalesHierId =myTSalesPos.tAlgmntSalesHier.salesHierId and myTSalesPos.tenantId= ?2") ,
/**
 * 
 * @author 409793 query to find CR requesting Sales Hierarchy Id
 */
@NamedQuery(name = "GetSPHierId", query = "select myTSalesPos.tAlgmntSalesHier.salesHierId from TSalesPos myTSalesPos where myTSalesPos.salesPosId =?1 and myTSalesPos.tenantId =?2"),

/*Added By 407986 To fetch all mailIds for the requested SalesPosition */
@NamedQuery(name = "GetEmployeeMailIds", query = "select DISTINCT(myTPersonContact.contactDetail)  from TPers myTPers , TEmpAlgmnt myTEmpAlgmnt, TPersContact myTPersonContact where myTEmpAlgmnt.tPers.staffId = myTPers.staffId AND myTPers.staffId = myTPersonContact.staffId AND myTPersonContact.contactTypeId = 1 AND myTEmpAlgmnt.salesPosId = ?1 AND myTEmpAlgmnt.tenantId = ?2 ") ,
/*Added By 407986 For CR Locking To fetch all pending  CRs for the requested SalesPosition */
@NamedQuery(name = "GetSPOtherLockedTriggers", query = "select count(myTChngReq)  from TChngReq myTChngReq  where myTChngReq.reqSalesPosId =?1 and myTChngReq.reqSalesHierId =?2 and myTChngReq.statusId in ?3 and myTChngReq.triggerId in ?4 and myTChngReq.tenantId = ?5 "), 

/*Added By 407986 To fetch primary employee's user id for the requested SalesPosition */
//@NamedQuery(name = "GetSPPrimaryEmpUserId", query = "select myTPers.tUsr.usrId,myTPers.firstName,myTPers.middleName,myTPers.lastName from TEmpAlgmnt myTEmpAlgmnt,TPers myTPers where  myTEmpAlgmnt.tPers.staffId=myTPers.staffId and myTEmpAlgmnt.allocTypeId=1 and myTEmpAlgmnt.activeFlag='Y' and myTEmpAlgmnt.salesPosId= ?1 and myTEmpAlgmnt.salesHierId= ?2 and myTEmpAlgmnt.algmntId= ?3 and myTEmpAlgmnt.bussUnitId= ?4 and myTEmpAlgmnt.salesTeamId= ?5 and myTEmpAlgmnt.tenantId= ?6   group by myTEmpAlgmnt.tPers.staffId ,myTPers.tUsr.usrId"),
	
/*Added By 407986 To fetch ChngReq and ChngReqConfig Details */
@NamedQuery(name = "GetTchngReqAndConfig", query = "select myTChngReq.reqSalesPosId ,myTChngReq.reqSalesHierId,myTChngReq.chngReqConfigId,myTChngReqConfig.prApprTypeId,myTChngReqConfig.secApprTypeId,myTChngReqConfig.duration,myTChngReqConfig.autoAction,myTChngReq.chngReqId,myTChngReq.chngReqNum,myTChngReq.createDt, myTChngReq.createdBy from TChngReqConfig myTChngReqConfig, TChngReq myTChngReq where myTChngReq.chngReqConfigId=myTChngReqConfig.chngReqConfigId and myTChngReq.chngReqId in ?1 and myTChngReq.tenantId =?2 "),
@NamedQuery(name = "updateTchngReqFlag", query = "Update TChngReq myTChngReq set myTChngReq.activeFlag='N' where myTChngReq.chngReqId in ?1"),
@NamedQuery(name = "updateChngReqStatus", query = "Update TChngReq myTChngReq set myTChngReq.statusId = ?1, myTChngReq.updateDt = ?2 ,myTChngReq.updatedBy = ?3 where myTChngReq.chngReqId = ?4 and myTChngReq.tenantId = ?5 and myTChngReq.activeFlag = 'Y'"),
@NamedQuery(name = "findTChngReqBySPIdWithProgressStatus", query = "select myTChngReq.chngReqId from TChngReq myTChngReq where (myTChngReq.reqSalesPosId =?1 or myTChngReq.raiseSalesPosId =?1) and myTChngReq.statusId not in (1,2,3,4,5,6,7,8) and  myTChngReq.tenantId = ?2 and myTChngReq.activeFlag='Y'"),

@NamedQuery(name = "FetchActiveCRsForTrigger", query = "select Count(*) from TChngReq myTChngReq,TSalesPos myTSalesPos where myTChngReq.activeFlag='Y' "+
		" and myTSalesPos.salesPosId = myTChngReq.reqSalesPosId " +
		" and myTChngReq.triggerId =?1 and myTChngReq.statusId =?2 and  myTChngReq.tenantId =?3" +
		" and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?4" +
		" and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId =?5" +
		" and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =?6" ),
@NamedQuery(name = "updateTchngReq", query = "UPDATE TChngReq myTChngReq "
				+ " SET myTChngReq.statusId = ?1 ,myTChngReq.raiseSalesPosId = ?2, myTChngReq.submitDtTm = ?3,myTChngReq.raiseSalesHierId = ?4,myTChngReq.submittedBy = ?5,myTChngReq.updatedBy = ?6,myTChngReq.updateDt = ?7,myTChngReq.bussReason = ?8, myTChngReq.lastSalesPosId =?9 ," +
				" myTChngReq.lastSalesHierId = ?10  where myTChngReq.chngReqId IN ?11 "),
@NamedQuery(name = "findAllTChngReqByCustomer", query = "select myTChngReq from TChngReq myTChngReq where myTChngReq.chngReqId in (select myTCustAlgmntChngReqDet.tChngReq.chngReqId from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet,TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.tCust.custId = ?1 and myTCustAlgmnt.salesPosId = ?2 and myTCustAlgmnt.salesHierId = ?3 and myTCustAlgmnt.algmntId = ?4 and myTCustAlgmnt.bussUnitId = ?5 and myTCustAlgmnt.salesTeamId = ?6 and myTCustAlgmnt.tenantId = ?7 and myTCustAlgmnt.custAlgmntId = myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.custAlgmntId)"),
@NamedQuery(name = "findAllTChngReqBySalesposition", query = "select myTChngReq from TChngReq myTChngReq where myTChngReq.reqSalesPosId = ?1 and myTChngReq.reqSalesHierId = ?2 and myTChngReq.reqSalesHierId.tenantId = ?3 and myTChngReq.activeFlag = 'Y'"),
@NamedQuery(name = "findAllTChngReqForApproval", query = "select myTChngReq from TChngReq myTChngReq where myTChngReq.reqSalesPosId = ?1 and myTChngReq.reqSalesHierId = ?2 and myTChngReq.reqSalesHierId.tenantId = ?3 and myTChngReq.statusId = ?4 and myTChngReq.activeFlag = 'Y'"),
@NamedQuery(name = "FindTChngReqByTChngReqTrack", query = "select myTChngReq from TChngReq myTChngReq where myTChngReq.tChngReqTrack = ?1 "),
@NamedQuery(name = "CountTChngReqsByTChngReqTrack", query = "select Count(myTChngReq) from TChngReq myTChngReq where myTChngReq.tChngReqTrack = ?1 "),
@NamedQuery(name = "findTChngReqByParentChangeRequestId", query = "select myTChngReq from TChngReq myTChngReq where myTChngReq.prnChngReq.chngReqId = ?1 "),
@NamedQuery(name = "findCustomerAlignmentSalesPostionIdByChangeRequestId", query = "select myTCustAlgnm.salesPosId from TChngReq myTChngReq,TCustAlgmntChngReqDet myTCustAlgnmChngReq,TCustAlgmnt myTCustAlgnm where myTChngReq.chngReqId = myTCustAlgnmChngReq.tCustAlgmntChngReqDetId.chngReqId and myTCustAlgnmChngReq.tCustAlgmntChngReqDetId.custAlgmntId = myTCustAlgnm.custAlgmntId and myTChngReq.prnChngReq.chngReqId = ?1 and myTChngReq.tenantId = ?2 group by myTCustAlgnm.salesPosId"),
@NamedQuery(name = "findZipAlignmentSalesPostionIdByChangeRequestId", query = "select myTTerrZipAlgnmChngReq.salesPosId from TChngReq myTChngReq,TTerrZipAlgmntChngReqDet myTTerrZipAlgnmChngReq where myTChngReq.chngReqId = myTTerrZipAlgnmChngReq.tChngReq.chngReqId and myTChngReq.prnChngReq.chngReqId = ?1 and myTChngReq.tenantId = ?2 group by myTTerrZipAlgnmChngReq.salesPosId"),
@NamedQuery(name = "findCustomerIdByChangeRequestId", query = "select c.custId from TChngReq cr,TCustAlgmntChngReqDet cacr,TCustAlgmnt ca,TCust c  where cr.chngReqId = cacr.tCustAlgmntChngReqDetId.chngReqId and cacr.tCustAlgmntChngReqDetId.custAlgmntId =  ca.custAlgmntId and ca.tCust.custId = c.custId and cr.chngReqId = ?1 and cr.tenantId = ?2 group by c.custId "),
@NamedQuery(name = "findParentChangeRequestIdByChildChangeRequestId", query = "select myTChngReq.prnChngReq.chngReqId from TChngReq myTChngReq where myTChngReq.chngReqId = ?1 and myTChngReq.tenantId = ?2"),
@NamedQuery(name = "findZipAlignmentByChangeRequestId", query = "select z from TChngReq ch,TTerrZipAlgmntChngReqDet zch,TTerrZipAlgmnt z where ch.chngReqId = zch.tChngReq.chngReqId and zch.postalCd = z.postalCd and zch.geoId = z.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId and zch.salesPosId = z.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId and zch.salesHierId = z.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId and ch.chngReqId = ?1 and zch.algmntFlag = ?2 and ch.tenantId = ?3 "),
@NamedQuery(name = "findMirrorZipAlignmentByParentChangeRequestId", query = "select zch.postalCd,zch.salesPosId from TChngReq ch,TTerrZipAlgmntChngReqDet zch,TTerrZipAlgmnt z where ch.prnChngReq.chngReqId = zch.tChngReq.chngReqId and zch.postalCd = z.postalCd and zch.geoId = z.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId and zch.salesPosId = z.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId and zch.salesHierId = z.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId and ch.prnChngReq.chngReqId = ?1 and zch.algmntFlag = ?2 and ch.tenantId = ?3 group by z.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId"),
/** Changes For Update Chnage Request Status -- start **/
@NamedQuery(name = "findChangeRequestByStatusId", query = "select myTChngReq from TChngReq myTChngReq where myTChngReq.statusId in (?1) and myTChngReq.tenantId = ?2 and myTChngReq.activeFlag='Y' "),
@NamedQuery(name = "findCRIdsNotInCRIdList", query = "select myTChngReq.chngReqId from TChngReq myTChngReq where myTChngReq.chngReqId not in (?1) and myTChngReq.tenantId = ?2 and myTChngReq.activeFlag='Y' "),
@NamedQuery(name = "updateTchngReqFlagAsInActive", query = "Update TChngReq myTChngReq set myTChngReq.activeFlag='N' where myTChngReq.chngReqId not in (?1) and ( (myTChngReq.updateDt is null and TIMESTAMPDIFF(second, myTChngReq.createDt, now()) > ?2) or TIMESTAMPDIFF(second, myTChngReq.updateDt, now()) > ?3 ) and myTChngReq.activeFlag='Y' ")
/** Changes For Update Chnage Request Status -- end **/
})


@Table(name = "t_chng_req", uniqueConstraints = @UniqueConstraint(columnNames = { "chng_req_id" }))
public class TChngReq implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "chng_req_id", nullable = false, length = 255)
	private Long chngReqId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "raise_sales_pos_id", nullable = false, length = 255)
	private Long raiseSalesPosId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "status_id", nullable = false, length = 255)
	private Integer statusId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "submit_dt_tm", nullable = true, length = 19)
	private Date submitDtTm;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "req_sales_pos_id", nullable = false, length = 255)
	private Long reqSalesPosId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "req_sales_hier_id", nullable = false, length = 255)
	private Long reqSalesHierId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "raise_sales_hier_id", nullable = false, length = 255)
	private Long raiseSalesHierId;

	/**
	 * 
	 * @generated
	 */

	@Column(name = "submitted_by", nullable = true, length = 255)
	private Integer submittedBy;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "created_by", nullable = false, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19)
	private Date createDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "updated_by", nullable = true, length = 255)
	private Integer updatedBy;

	/**
	 * 
	 * @generated
	 */
	@Version
	@Column(name = "update_dt", nullable = true, length = 19)
	private Date updateDt;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "action_by", nullable = true, length = 255)
	private Integer actionBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "action_dt_tm", nullable = true, length = 19)
	private Date actionDtTm;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "buss_reason", nullable = true, length = 500)
	private String bussReason;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "trigger_id", nullable = false, length = 255)
	private Integer triggerId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "comments", nullable = true, length = 500)
	private String comments;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "last_sales_pos_id", nullable = true, length = 255)
	private Long lastSalesPosId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "last_sales_hier_id", nullable = true, length = 255)
	private Long lastSalesHierId;

	@Column(name = "chng_req_config_id", nullable = false, length = 255)
	private Integer chngReqConfigId;
	
	@Column(name = "chng_req_num", nullable = true, length = 255)
	private Long chngReqNum;
	
	@ManyToOne
	@JoinColumn(name = "chng_req_track_id", referencedColumnName = "chng_req_track_id", nullable = true, insertable = true, updatable = true)
	private TChngReqTrack tChngReqTrack;

    @ManyToOne
	@JoinColumn(name = "chng_req_config_id", referencedColumnName = "chng_req_config_id", nullable = false, insertable = false, updatable = false)
	// @Valid
	private TChngReqConfig tChngReqConfig;

    @ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "prn_chng_req_id", referencedColumnName = "chng_req_id", nullable = true, insertable = true, updatable = true)
	private TChngReq prnChngReq;
    
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tChngReq")
	private Set<TChngReqAppr> tChngReqApprss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tChngReq")
	private Set<TChngReqNotify> tChngReqNotifiess;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReq")
	private Set<TCustAlgmntChngReqDet> tCustAlgmntChngReqDetss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReq")
	private Set<TCustAffChngReqDet> tCustAffChngReqDetss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReq")
	private Set<TPrdAlgmntChngReqDet> tPrdAlgmntChngReqDetss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReq")
	private Set<TCustCallPlanChngReqDet> tCustCallPlanChngReqDetss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReq")
	private Set<TTerrZipAlgmntChngReqDet> tTerrZipAlgmntChngReqDetss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReq")
	private Set<TSalesPosChngReqDet> tSalesPosChngReqDetss;

	/**
	 * 
	 * @generated
	 */
	public TChngReq() {
	}
	
	

	public Long getChngReqNum() {
		return chngReqNum;
	}



	public void setChngReqNum(Long chngReqNum) {
		this.chngReqNum = chngReqNum;
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

	public void setRaiseSalesPosId(final Long raiseSalesPosId) {
		this.raiseSalesPosId = raiseSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getRaiseSalesPosId() {
		return this.raiseSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStatusId(final Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getStatusId() {
		return this.statusId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSubmitDtTm(final Date submitDtTm) {
		if (submitDtTm != null) {
			this.submitDtTm = (Date) submitDtTm.clone();
		} else {
			this.submitDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getSubmitDtTm() {
		if (this.submitDtTm != null) {
			return (Date) this.submitDtTm.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setReqSalesPosId(final Long reqSalesPosId) {
		this.reqSalesPosId = reqSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getReqSalesPosId() {
		return this.reqSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setReqSalesHierId(final Long reqSalesHierId) {
		this.reqSalesHierId = reqSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getReqSalesHierId() {
		return this.reqSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRaiseSalesHierId(final Long raiseSalesHierId) {
		this.raiseSalesHierId = raiseSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getRaiseSalesHierId() {
		return this.raiseSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSubmittedBy(final Integer submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getSubmittedBy() {
		return this.submittedBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setActiveFlag(final Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getActiveFlag() {
		return this.activeFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCreatedBy(final Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCreateDt(final Date createDt) {
		if (createDt != null) {
			this.createDt = (Date) createDt.clone();
		} else {
			this.createDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreateDt() {
		if (this.createDt != null) {
			return (Date) this.createDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setUpdatedBy(final Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setUpdateDt(final Date updateDt) {
		if (updateDt != null) {
			this.updateDt = (Date) updateDt.clone();
		} else {
			this.updateDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		if (this.updateDt != null) {
			return (Date) this.updateDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setTenantId(final Short tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getTenantId() {
		return this.tenantId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setActionBy(final Integer actionBy) {
		this.actionBy = actionBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getActionBy() {
		return this.actionBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setActionDtTm(final Date actionDtTm) {
		if (actionDtTm != null) {
			this.actionDtTm = (Date) actionDtTm.clone();
		} else {
			this.actionDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getActionDtTm() {
		if (this.actionDtTm != null) {
			return (Date) this.actionDtTm.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussReason(final String bussReason) {
		this.bussReason = bussReason;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBussReason() {
		return this.bussReason;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTriggerId(final Integer triggerId) {
		this.triggerId = triggerId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTriggerId() {
		return this.triggerId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * 
	 * @generated
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * 
	 * @generated
	 */

	public TChngReqConfig getTChngReqConfig() {
		return this.tChngReqConfig;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTChngReqConfig(final TChngReqConfig tChngReqConfig) {
		this.tChngReqConfig = tChngReqConfig;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TChngReqAppr> getTChngReqApprss() {
		return this.tChngReqApprss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReqApprss(final Set<TChngReqAppr> tChngReqApprss) {
		this.tChngReqApprss = tChngReqApprss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TChngReqNotify> getTChngReqNotifiess() {
		return this.tChngReqNotifiess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReqNotifiess(final Set<TChngReqNotify> tChngReqNotifiess) {
		this.tChngReqNotifiess = tChngReqNotifiess;
	}

	public Long getLastSalesPosId() {
		return lastSalesPosId;
	}

	public void setLastSalesPosId(Long lastSalesPosId) {
		this.lastSalesPosId = lastSalesPosId;
	}

	public Long getLastSalesHierId() {
		return lastSalesHierId;
	}

	public void setLastSalesHierId(Long lastSalesHierId) {
		this.lastSalesHierId = lastSalesHierId;
	}

	public Integer getChngReqConfigId() {
		return chngReqConfigId;
	}

	public void setChngReqConfigId(Integer chngReqConfigId) {
		this.chngReqConfigId = chngReqConfigId;
	}

	public TChngReqTrack gettChngReqTrack() {
		return tChngReqTrack;
	}

	public void settChngReqTrack(TChngReqTrack tChngReqTrack) {
		this.tChngReqTrack = tChngReqTrack;
	}



	public TChngReqConfig gettChngReqConfig() {
		return tChngReqConfig;
	}



	public void settChngReqConfig(TChngReqConfig tChngReqConfig) {
		this.tChngReqConfig = tChngReqConfig;
	}



	public Set<TChngReqAppr> gettChngReqApprss() {
		return tChngReqApprss;
	}



	public void settChngReqApprss(Set<TChngReqAppr> tChngReqApprss) {
		this.tChngReqApprss = tChngReqApprss;
	}



	public Set<TChngReqNotify> gettChngReqNotifiess() {
		return tChngReqNotifiess;
	}

	public void settChngReqNotifiess(Set<TChngReqNotify> tChngReqNotifiess) {
		this.tChngReqNotifiess = tChngReqNotifiess;
	}

	public Set<TCustAlgmntChngReqDet> gettCustAlgmntChngReqDetss() {
		return tCustAlgmntChngReqDetss;
	}



	public void settCustAlgmntChngReqDetss(
			Set<TCustAlgmntChngReqDet> tCustAlgmntChngReqDetss) {
		this.tCustAlgmntChngReqDetss = tCustAlgmntChngReqDetss;
	}



	public Set<TCustAffChngReqDet> gettCustAffChngReqDetss() {
		return tCustAffChngReqDetss;
	}



	public void settCustAffChngReqDetss(Set<TCustAffChngReqDet> tCustAffChngReqDetss) {
		this.tCustAffChngReqDetss = tCustAffChngReqDetss;
	}



	public Set<TPrdAlgmntChngReqDet> gettPrdAlgmntChngReqDetss() {
		return tPrdAlgmntChngReqDetss;
	}



	public void settPrdAlgmntChngReqDetss(
			Set<TPrdAlgmntChngReqDet> tPrdAlgmntChngReqDetss) {
		this.tPrdAlgmntChngReqDetss = tPrdAlgmntChngReqDetss;
	}



	public Set<TCustCallPlanChngReqDet> gettCustCallPlanChngReqDetss() {
		return tCustCallPlanChngReqDetss;
	}



	public void settCustCallPlanChngReqDetss(
			Set<TCustCallPlanChngReqDet> tCustCallPlanChngReqDetss) {
		this.tCustCallPlanChngReqDetss = tCustCallPlanChngReqDetss;
	}



	public Set<TTerrZipAlgmntChngReqDet> gettTerrZipAlgmntChngReqDetss() {
		return tTerrZipAlgmntChngReqDetss;
	}



	public void settTerrZipAlgmntChngReqDetss(
			Set<TTerrZipAlgmntChngReqDet> tTerrZipAlgmntChngReqDetss) {
		this.tTerrZipAlgmntChngReqDetss = tTerrZipAlgmntChngReqDetss;
	}



	public Set<TSalesPosChngReqDet> gettSalesPosChngReqDetss() {
		return tSalesPosChngReqDetss;
	}



	public void settSalesPosChngReqDetss(
			Set<TSalesPosChngReqDet> tSalesPosChngReqDetss) {
		this.tSalesPosChngReqDetss = tSalesPosChngReqDetss;
	}

	public TChngReq getPrnChngReq() {
		return prnChngReq;
	}


	public void setPrnChngReq(TChngReq prnChngReq) {
		this.prnChngReq = prnChngReq;
	}


	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TChngReq that) {
		setChngReqId(that.getChngReqId());
		setRaiseSalesPosId(that.getRaiseSalesPosId());
		setStatusId(that.getStatusId());
		setSubmitDtTm(that.getSubmitDtTm());
		setReqSalesPosId(that.getReqSalesPosId());
		setReqSalesHierId(that.getReqSalesHierId());
		setRaiseSalesHierId(that.getRaiseSalesHierId());
		setSubmittedBy(that.getSubmittedBy());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setActionBy(that.getActionBy());
		setActionDtTm(that.getActionDtTm());
		setBussReason(that.getBussReason());
		setTriggerId(that.getTriggerId());
		setComments(that.getComments());
		setLastSalesPosId(that.getLastSalesPosId());
		setLastSalesHierId(that.getLastSalesHierId());

	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chngReqId == null) ? 0 : chngReqId.hashCode());
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
		buffer.append("raiseSalesPosId=[").append(raiseSalesPosId).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("submitDtTm=[").append(submitDtTm).append("] ");
		buffer.append("reqSalesPosId=[").append(reqSalesPosId).append("] ");
		buffer.append("reqSalesHierId=[").append(reqSalesHierId).append("] ");
		buffer.append("raiseSalesHierId=[").append(raiseSalesHierId)
				.append("] ");
		buffer.append("submittedBy=[").append(submittedBy).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("actionBy=[").append(actionBy).append("] ");
		buffer.append("actionDtTm=[").append(actionDtTm).append("] ");
		buffer.append("bussReason=[").append(bussReason).append("] ");
		buffer.append("triggerId=[").append(triggerId).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
		buffer.append("lastSalesPosId=[").append(lastSalesPosId).append("] ");
		buffer.append("lastSalesHierId=[").append(lastSalesHierId).append("] ");
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
		final TChngReq other = (TChngReq) obj;
		if (chngReqId == null) {
			if (other.chngReqId != null) {
				return false;
			}
		} else if (!chngReqId.equals(other.chngReqId)) {
			return false;
		}
		return true;
	}
}
