(function(){
	
	var myApp = angular.module('myApp', []);
	
	var MainCtrl=function($scope,$http){
		$scope.mainMenu = true;
		$scope.menuselected = undefined;
		$scope.menuList = [
		     {"name":"Attribute Setup","code":"attrsetup"},
			 {"name":"Business Reason","code":"businessreason"},
			 {"name":"Call Plan ","code":"callplan"},
			 {"name":"Change Request ","code":"cr"},
			 {"name":"Customer Affiliation ","code":"customeraffiliation"},
			 {"name":"Customer Alignment","code":"customeralignment"},
			 {"name":"Customer GIS ","code":"customergis"},
			 {"name":"Customer ProductAlignment ","code":"customerproductalignment"},
			 {"name":"Dashboard ","code":"dashboard"},
			 {"name":"Employee Alignment ","code":"employeealignment"},
			 {"name":"Employee ","code":"employee"},
			 {"name":"Entity TemplateSetup ","code":"entitytemplatesetup"},
			 {"name":"Geography Alignment","code":"geographyalignment"},
			 {"name":"Metric Offline","code":"metric"},
			 {"name":"Metric Result ","code":"metricresult"},
			 {"name":"Metric Setup ","code":"metricsetup"},
			 {"name":"Product Alignment ","code":"productplignment"},
			 {"name":"Product ","code":"product"},
			 {"name":"Sales Hierarchy ","code":"saleshierarchy"},
			 {"name":"Sales OrgRole ","code":"salesorgrole"},
			 {"name":"Sales Position ","code":"salesposition"},
			 {"name":"SP View","code":"spview"},
			 {"name":"Template Alignment ","code":"templatealignment"},
			 {"name":"******opserv-sp-cr-process-batch******","code":"cr-process-batch"},
			 {"name":"updateChangeRequests","code":"updateChangeRequests"},
			 {"name":"createMirrorCR","code":"createMirrorCR"},
			 {"name":"getMirrorCRsForPush","code":"getMirrorCRsForPush"},
			 {"name":"geocreateMirrorCR","code":"geocreateMirrorCR"},
			 {"name":"geoUpdateChangeRequests","code":"geoUpdateChangeRequests"},
			 {"name":"CustomerPullUpdateChangeRequests","code":"CustomerPullUpdateChangeRequests"},
			 {"name":"CustomerPullMirrorCRsForPush","code":"CustomerPullMirrorCRsForPush"},
			 {"name":"CustomerPullcreateMirrorCR","code":"CustomerPullcreateMirrorCR"},
			 {"name":"CustomerEditcreateMirrorCR","code":"CustomerEditcreateMirrorCR"},
			 {"name":"CustomerEditMirrorCRsForEdit","code":"CustomerEditMirrorCRsForEdit"},
			 {"name":"CustomerEditupdateChangeRequests","code":"CustomerEditupdateChangeRequests"},


			 {"name":"Customer Push Offline","code":"customerpushoffline"}
		];
		
		
		var onUserComplete=function(response){
		  
			$scope.responsedata=response.data;
			  
		};				
		
		var onError=function(reason){
			$scope.error="Could not fetch the details";
			console.log(reason);
		};
		
		$scope.loadLinks=function(menu){
		  $scope.menuselected=menu.name;
		  switch (menu.code) {
			case 'attrsetup':
				attrsetup();
				break;
			case 'businessreason':
				 businessreason();
				break;
			case 'callplan':
				 callplan();
				break;
			case 'cr':
				cr();
				break;
			case 'customeraffiliation':
				 customeraffiliation();
				break;
			case 'customeralignment':
				 customeralignment();
				break;
			case 'customergis':
				 customergis();
				break;
			case 'customerproductalignment':
				 customerproductalignment();
				break;
			case 'dashboard':
				 dashboard();
				break;
			case 'employeealignment':
				 employeealignment();
				break;
			case 'employee':
				 employee();
				break;
			case 'entitytemplatesetup':
				 entitytemplatesetup();
				break;
			case 'geographyalignment':
				 geographyalignment();
				break;
			case 'metric':
				 metric();
				break;
			case 'metricresult':
				 metricresult();
				break;
			case 'metricsetup':
				 metricsetup();
				break;
			case 'productplignment':
				 productplignment();
				break;
			case 'product':
				 product();
				break;
			case 'saleshierarchy':
				 saleshierarchy();
				break;
			case 'salesorgrole':
				 salesorgrole();
				break;
			case 'salesposition':
				 salesposition();
				break;
			case 'spview':
				 spview();
				break;
			case 'templatealignment':
				templatealignment();
				break;
			case 'cr-process-batch':
				break;
			case 'customerpushoffline':
				customerpushoffline();
				break;
			case 'createMirrorCR':
				createMirrorCR();
				break;
			case 'updateChangeRequests':
				updateChangeRequests();
				break;
			case 'getMirrorCRsForPush':
				getMirrorCRsForPush();
				break;
			case 'geocreateMirrorCR':
				geocreateMirrorCR();
				break;
			case 'getProductDetails':
				getProductDetails();
				break;
			case 'createProduct':
				createProduct();
				break;
			case 'geoUpdateChangeRequests':
				geoUpdateChangeRequests();
				break;
			case 'CustomerPullUpdateChangeRequests':
				CustomerPullUpdateChangeRequests();
				break;
			case 'CustomerPullMirrorCRsForPush':
				CustomerPullMirrorCRsForPush();
				break;
			case 'CustomerPullcreateMirrorCR':
				CustomerPullcreateMirrorCR();
				break;
			case 'CustomerEditcreateMirrorCR':
				CustomerEditcreateMirrorCR();
				break;
			case 'CustomerEditMirrorCRsForEdit':
				CustomerEditMirrorCRsForEdit();
				break;
			case 'CustomerEditupdateChangeRequests':
				CustomerEditupdateChangeRequests();
				break;
			default:
				alert("No links found. Please add");
				break;
			}
		};
		
		var attrsetup=function(){
			$scope.reqLinks = [
				{"moduleName":"deleteAttributeGroup","linkstatic":"/attributeSetupDeleteGrp/","dynamic":{"inputType":"text","pholder":"grpId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"deleteAttributeFromGroup","linkstatic":"/attributeSetupDeleteAttr/","dynamic":{"inputType":"text","pholder":"grpId/attrId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var businessreason=function(){
			$scope.reqLinks = [
				{"moduleName":"getBusinessReasons","linkstatic":"/businessReason/getBusinessReasons/","dynamic":{"inputType":"text","pholder":"algnId/buId/stId/trTypeId/custCategoryId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var callplan=function(){
			$scope.reqLinks = [
				{"moduleName":"getCustomerCallPlanDetails","linkstatic":"/callPlan/getCustomerCallPlanDetails/","dynamic":{"inputType":"text","pholder":"algnId/buId/stId/spId/shId/custId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"createDirBasedCallPlan","linkstatic":"/callPlan/createDirBasedCallPlan/","dynamic":{"inputType":"text","pholder":"custAlgId/custId/callPlanTypeId/plannedCalls"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var customeraffiliation=function(){
			$scope.reqLinks = [
				{"moduleName":"getCustomerAffiliation","linkstatic":"/custAffiliation/","dynamic":{"inputType":"text","pholder":"custId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"getCustomerAffiliationByAlignment","linkstatic":"/custAffiliation/","dynamic":{"inputType":"text","pholder":"custId/al/bu/st"},"client":"am","requestMethod":"GET"},
				{"moduleName":"getAccountCustomerAffiliations","linkstatic":"/custAffiliation/account/","dynamic":{"inputType":"text","pholder":"custId"},"client":"am","requestMethod":"GET"}
				];
			$scope.mainMenu = false;
		};
		
		var cr=function(){
		
			$scope.reqLinks = [
				{"moduleName":"AllChangeRequestsForApproval","linkstatic":"/changeRequest/fetchAllChangeRequestsForApproval/","dynamic":{"inputType":"text","pholder":"salesPosId/salesHierId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"AllChangeRequests","linkstatic":"/changeRequest/fetchAllChangeRequests/","dynamic":{"inputType":"text","pholder":"salesPosId/salesHierId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"AllChangeRequestsOfCustomer","linkstatic":"/changeRequest/fetchAllChangeRequestsOfCustomer/","dynamic":{"inputType":"text","pholder":"custId/salesPosId/salesHierId/alignId/businessId/salesTeamId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"submit cr","linkstatic":"/changeRequest/submitCR/","dynamic":{"inputType":"text","pholder":"crID"},"client":"am","requestMethod":"GET"},
				{"moduleName":"Cancel cr","linkstatic":"/changeRequest/cancelCR/","dynamic":{"inputType":"text","pholder":"crID"},"client":"am","requestMethod":"GET"},
				{"moduleName":"CRLineItemsByCRId cr","linkstatic":"/changeRequest/getCRLineItemsByCRId/","dynamic":{"inputType":"text","pholder":"crID"},"client":"am","requestMethod":"GET"},
				{"moduleName":"CustomerAlignmentChangeRequestDetailsByChangeRequest","linkstatic":"/changeRequest/getCACRLineItemsByCRId/","dynamic":{"inputType":"text","pholder":"crID"},"client":"am","requestMethod":"GET"},
				{"moduleName":"ChangeRequestsTasksFromApproverSalesPosition","linkstatic":"/changeRequest/getApprTasks/","dynamic":{"inputType":"text","pholder":"salesPositionID/hierID"},"client":"am","requestMethod":"GET"},
				{"moduleName":"ZipAlignmentChangeRequestDetailsByChangeRequest","linkstatic":"/changeRequest/getZACRLineItemsByCRId/","dynamic":{"inputType":"text","pholder":"crID"},"client":"am","requestMethod":"GET"},
				{"moduleName":"approveCR","linkstatic":"/changeRequest/approveCR/","dynamic":{"inputType":"text","pholder":"crID/spID/hierID/comments"},"client":"am","requestMethod":"GET"},
				{"moduleName":"rejectCR","linkstatic":"/changeRequest/rejectCR/","dynamic":{"inputType":"text","pholder":"crID/spID/hierID/comments"},"client":"am","requestMethod":"GET"},
				{"moduleName":"ChangeRequestApproversDetails","linkstatic":"/changeRequest/getCRApproversDetails/","dynamic":{"inputType":"text","pholder":"crID"},"client":"am","requestMethod":"GET"}
			]; 
		 
			$scope.mainMenu = false;
		};
		var customeralignment=function(){
			$scope.reqLinks = [
				{"moduleName":"getAllCustomerAlignments","linkstatic":"/customer/alignment/","dynamic":{"inputType":"text","pholder":"custId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"getAllCustomerAlignmentsBuId","linkstatic":"/customer/alignment/","dynamic":{"inputType":"text","pholder":"custId/buId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var customergis=function(){
			$scope.reqLinks = [
				{"moduleName":"getCustomers","linkstatic":"/gis/customer","client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var customerproductalignment=function(){
			$scope.reqLinks = [
				{"moduleName":"getAllCustomerProductsByCustIdSpId","linkstatic":"/salespos/alignment/","dynamic":{"inputType":"text","pholder":"custId/spId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"getAllCustomerProductsBySpId","linkstatic":"/salespos/alignment/","dynamic":{"inputType":"text","pholder":"spId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var dashboard=function(){
			$scope.reqLinks = [
				{"moduleName":"getProductCountForSalesPositions","linkstatic":"/dashboard/prdCount/","dynamic":{"inputType":"text","pholder":"spList"},"client":"am","requestMethod":"GET"},
				{"moduleName":"getProductNamesForSalesPositions","linkstatic":"/dashboard/prdNames/","dynamic":{"inputType":"text","pholder":"spList"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var employeealignment=function(){
			$scope.reqLinks = [
				{"moduleName":"getAllEmployeeAlignmentsBySalesPosition","linkstatic":"/empAlignment/salesPos/","dynamic":{"inputType":"text","pholder":"al/bu/st/sp/hier"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var employee=function(){
			$scope.reqLinks = [
				{"moduleName":"getEmployeeDetails","linkstatic":"/employee/","dynamic":{"inputType":"text","pholder":"staffId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"assgEmp","linkstatic":"/employee/assgEmp/","dynamic":{"inputType":"text","pholder":"staffId/alignmentId/allocPercentage/businessUnitId/salesTeamId/salesPositionId/salesOrgHierarchyId/startdate/endDate"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var entitytemplatesetup=function(){
			$scope.reqLinks = [
				{"moduleName":"getEntityTemplateById","linkstatic":"/getEntityTemplateById/","dynamic":{"inputType":"text","pholder":"templateId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"getEntityTemplateByEntity","linkstatic":"/getEntityTemplateByEntity/","dynamic":{"inputType":"text","pholder":"entity"},"client":"am","requestMethod":"GET"},
				{"moduleName":"getAlignmentEntityTempaltes","linkstatic":"/getAlignmentEntityTempaltes/","dynamic":{"inputType":"text","pholder":"templateId"},"client":"am","requestMethod":"GET"},
				{"moduleName":"assignTemplatesToAlignment","linkstatic":"/assignTemplatesToAlignment/","dynamic":{"inputType":"text","pholder":"templateId/al/bu/st"},"client":"am","requestMethod":"GET"},
				{"moduleName":"copyAndCreateEntityTemplate","linkstatic":"/copyAndCreateEntityTemplate/","dynamic":{"inputType":"text","pholder":"srctemplateId/newTemplateName"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var geographyalignment=function(){
			$scope.reqLinks = [
			  {"moduleName":"getAlignmentEntityTempaltes","linkstatic":"/getAlignmentEntityTempaltes/","dynamic":{"inputType":"text","pholder":"templateId"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"assignTemplatesToAlignment","linkstatic":"/assignTemplatesToAlignment/","dynamic":{"inputType":"text","pholder":"templateId/al/bu/st"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var metric=function(){
			$scope.reqLinks = [
			  {"moduleName":"processCalculativeMetrics","linkstatic":"/metrics/processCalculativeMetrics/","dynamic":{"inputType":"text","pholder":"al/bu/st/spId/shId/metricTriggerType"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"checkCustomerPushMetricViolation","linkstatic":"/metrics/checkCustomerPushMetricViolation/","dynamic":{"inputType":"text","pholder":"al/bu/st/srcsp/srcsh/tarsp/tarsh"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"checkCustomerPullMetricViolation","linkstatic":"/metrics/checkCustomerPullMetricViolation/","dynamic":{"inputType":"text","pholder":"al/bu/st/srcsp/srcsh/tarsp/tarsh"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"checkCustomerEditMetricViolation","linkstatic":"/metrics/checkCustomerEditMetricViolation/","dynamic":{"inputType":"text","pholder":"al/bu/st/tarsp/tarsh"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"checkZipMovementMetricViolation","linkstatic":"/metrics/checkZipMovementMetricViolation/","dynamic":{"inputType":"text","pholder":"al/bu/st/srcsp/srcsh/tarsp/tarsh"},"client":"am","requestMethod":"GET"},
			];
			$scope.mainMenu = false;
		};
		var metricresult=function(){
			$scope.reqLinks = [
			  {"moduleName":"getMetricResultsWithoutTriggerTypes","linkstatic":"/metric/metricResult/","dynamic":{"inputType":"text","pholder":"spId/shId/al/bu/st"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getMetricResultsByTriggerTypes","linkstatic":"/metric/metricResult/","dynamic":{"inputType":"text","pholder":"spId/shId/al/bu/stId/metricTriggerTypes"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getAllMetricResults","linkstatic":"/metric/getAllMetricResults/","dynamic":{"inputType":"text","pholder":"spId/shId/al/bu/stId/metricTriggerType"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getMetricResultsBySalesPositions","linkstatic":"/metric/getMetricsBySP/","dynamic":{"inputType":"text","pholder":"mtrId/spIds"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getMetricsByPostalCodes","linkstatic":"/metric/getMetricsByPostalCodes/","dynamic":{"inputType":"text","pholder":"mtrId/postalcodes"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getAllMetricResultsByPostalCodes","linkstatic":"/metric/getAllMetricResultsByPostalCodes/","dynamic":{"inputType":"text","pholder":"postalcodes"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getAllMetricResultsByPostalCodesAndAlgmnt","linkstatic":"/metric/getAllMetricResultsByPostalCodesAndAlgmnt/","dynamic":{"inputType":"text","pholder":"postalcodes/al"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getMetricExecutionConfig","linkstatic":"/metric/getMetricExecutionConfig/","dynamic":{"inputType":"text","pholder":"al/bu/stId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var metricsetup=function(){
			$scope.reqLinks = [
			  {"moduleName":"createMetric","linkstatic":"/createMetrics/","dynamic":{"inputType":"text","pholder":"metName/al/bu/st/catgry"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"updateMetric","linkstatic":"/updateMetric/","dynamic":{"inputType":"text","pholder":"metId/metName/al/bu/st/catgry"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getMetricsByAlignment","linkstatic":"/getMetricsByAlignment/","dynamic":{"inputType":"text","pholder":"al/bu/st"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"configureMetric","linkstatic":"/configureMetric/","dynamic":{"inputType":"text","pholder":"metId/shId/al/bu/st"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"configureMetricExecutionTrigger","linkstatic":"/configureMetricExecutionTrigger/","dynamic":{"inputType":"text","pholder":"metId/shId/triggerId/al/bu/st"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"configureMetricExecutionTriggerList","linkstatic":"/configureMetricExecutionTriggerList/","dynamic":{"inputType":"text","pholder":"metId/shId/triggerIds/al/bu/st"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var productplignment=function(){
			$scope.reqLinks = [
			  {"moduleName":"getAllProductAlignmentsBySalesPosition","linkstatic":"/salespos/alignment/","dynamic":{"inputType":"text","pholder":"spId/algId/buId/stId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var product=function(){
			$scope.reqLinks = [
			  {"moduleName":"getProductDetailsByProductId","linkstatic":"/product/","dynamic":{"inputType":"text","pholder":"prdId"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getAllProductDetailsByProductIds","linkstatic":"/products/","dynamic":{"inputType":"text","pholder":"prdIds"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var saleshierarchy=function(){
			$scope.reqLinks = [
			  {"moduleName":"getSalesHierarchyByAlignment","linkstatic":"/saleshierarchy/alignment/","dynamic":{"inputType":"text","pholder":"al/bu/st"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getZipAssignments","linkstatic":"/saleshierarchy/getZipAssignment/","dynamic":{"inputType":"text","pholder":"al/bu/st"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var salesorgrole=function(){
			$scope.reqLinks = [
			  {"moduleName":"createRole","linkstatic":"/salesRole/createRole/","dynamic":{"inputType":"text","pholder":"name"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"updateRole","linkstatic":"/salesRole/updateRole/","dynamic":{"inputType":"text","pholder":"id/name"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var salesposition=function(){
			$scope.reqLinks = [
			  {"moduleName":"getAllChildSalesPositions","linkstatic":"/salespos/getAllChildSPs/","dynamic":{"inputType":"text","pholder":"spId"},"client":"am","requestMethod":"GET"},
			  {"moduleName":"getSalesPositionInformation","linkstatic":"/salespos/SalesPositionInfo/","dynamic":{"inputType":"text","pholder":"spId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var spview=function(){
			$scope.reqLinks = [
			  {"moduleName":"getCustomerAlignmentViewHeader","linkstatic":"/customerAlignmentViewHeader","client":"am","requestMethod":"GET"},
			  {"moduleName":"getCustomerAlignments","linkstatic":"/customerAlignmentViewList","client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var templatealignment=function(){
			$scope.reqLinks = [
			  {"moduleName":"getEntityTemplate","linkstatic":"/template/alignment/","dynamic":{"inputType":"text","pholder":"entId/al/bu/st"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var customerpushoffline=function(){
			$scope.reqLinks = [
			  {"moduleName":"lockCustomerAlignment","linkstatic":"/customerpushoffline/lockcustomer/","dynamic":{"inputType":"text","pholder":"custId/spId/custAlgmtId/userId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var updateChangeRequests=function(){
			$scope.reqLinks = [
			  {"moduleName":"updateChangeRequests","linkstatic":"/customerpushoffline/updateChangeRequests/","dynamic":{"inputType":"text","pholder":"srcsp/srcsh/tarsp/tarsh/custId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var createMirrorCR=function(){
			$scope.reqLinks = [
			  {"moduleName":"createMirrorCR","linkstatic":"/customerpushoffline/createMirrorCR/","dynamic":{"inputType":"text","pholder":"salespoId/buId/salesTeamId/almntId/hierarchyId/salespoTrgId/hierarchyTrgId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var getMirrorCRsForPush=function(){
			$scope.reqLinks = [
			  {"moduleName":"getMirrorCRsForPush","linkstatic":"/customerpushoffline/getMirrorCRsForPush/","dynamic":{"inputType":"text","pholder":"srcsp/srcsh/tarsp/tarsh/custId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		var geocreateMirrorCR=function(){
			$scope.reqLinks = [
			  {"moduleName":"geocreateMirrorCR","linkstatic":"/customerpushoffline/geocreateMirrorCR/","dynamic":{"inputType":"text","pholder":"salespoId/buId/salesTeamId/almntId/hierarchyId/salespoTrgId/hierarchyTrgId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
				
		var getProductDetails=function(){
			$scope.reqLinks = [
			  {"moduleName":"getProductDetails","linkstatic":"/getProductDetails/","dynamic":{"inputType":"text","pholder":"prodId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		
		var createProduct=function(){
			$scope.reqLinks = [
			  {"moduleName":"createProduct","linkstatic":"/createProduct/","dynamic":{"inputType":"text","pholder":"prdName/prdCode"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var geoUpdateChangeRequests=function(){
			$scope.reqLinks = [
			  {"moduleName":"geoUpdateChangeRequests","linkstatic":"/GeoMovement/geoUpdateChangeRequests/","dynamic":{"inputType":"text","pholder":"chngreqId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var CustomerPullUpdateChangeRequests=function(){
			$scope.reqLinks = [
			  {"moduleName":"CustomerPullUpdateChangeRequests","linkstatic":"/CustomerPullOffline/geoUpdateChangeRequests/","dynamic":{"inputType":"text","pholder":"chngreqId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var CustomerPullMirrorCRsForPush=function(){
			$scope.reqLinks = [
			  {"moduleName":"CustomerPullMirrorCRsForPush","linkstatic":"/CustomerPullOffline/getMirrorCRsForPush/","dynamic":{"inputType":"text","pholder":"chngreqId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var CustomerPullcreateMirrorCR=function(){
			$scope.reqLinks = [
			  {"moduleName":"CustomerPullcreateMirrorCR","linkstatic":"/CustomerPullOffline/createMirrorCR/","dynamic":{"inputType":"text","pholder":"salespoId/buId/salesTeamId/almntId/hierarchyId/salespoTrgId/hierarchyTrgId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var CustomerEditcreateMirrorCR=function(){
			$scope.reqLinks = [
			  {"moduleName":"CustomerEditcreateMirrorCR","linkstatic":"/CustomerEditOffline/createMirrorCR/","dynamic":{"inputType":"text","pholder":"salespoId/buId/salesTeamId/almntId/hierarchyId/salespoTrgId/hierarchyTrgId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var CustomerEditMirrorCRsForEdit=function(){
			$scope.reqLinks = [
			  {"moduleName":"CustomerEditMirrorCRsForEdit","linkstatic":"/CustomerEditOffline/getMirrorCRsForEdit/","dynamic":{"inputType":"text","pholder":"chngreqId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		
		var CustomerEditupdateChangeRequests=function(){
			$scope.reqLinks = [
			  {"moduleName":"CustomerEditupdateChangeRequests","linkstatic":"/CustomerEditOffline/updateChangeRequests/","dynamic":{"inputType":"text","pholder":"chngreqId"},"client":"am","requestMethod":"GET"}
			];
			$scope.mainMenu = false;
		};
		$scope.searchResult=function(req){
			
			if(req.dynamic!=undefined){
				alert("Have you provide all arguments " +req.dynamic.pholder +" and client ?");
			}
			$scope.error='';
			$scope.responsedata=undefined;
			console.log(req);
			 var url='';
			 if(req.linkDynamic !=undefined){
				url = req.linkstatic+req.linkDynamic;
			 }else{
				url = req.linkstatic;
			 }
			 if(req.client != undefined){
				url = url+'?client='+req.client;
			 }
			console.log(url);
			if(req.requestMethod=='GET'){
				$http.get("/sprs"+url).then(onUserComplete,onError);
			}
			if(req.requestMethod=='POST'){
				$http.post("/sprs"+url).then(onUserComplete,onError);
			}
		};
		
		$scope.goBackToMainMenu=function(){
			$scope.mainMenu = true;
			$scope.responsedata=undefined;
			$scope.error='';
		};
	};
	
	myApp.controller("MainCtrl",MainCtrl);
}());