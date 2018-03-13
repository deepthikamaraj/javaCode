<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><html>
<head>
<title>All Services</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script>
$(document).ready(function() {
	var serviceInterfaceNames = '${serviceInterfaceNames}';
	var serviceInterfaceJson = JSON.stringify(serviceInterfaceNames);	
	
 $('#classes').change(function(event) {
    var serviceClass = $('select#classes').val();    
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "exposingRestServices/displayMethods.do",
		data : {"serviceName":serviceClass, "client":"am"},
		dataType : 'json',
		timeout : 100000,
		success : function(response) {
		    	var select = $('#Method');
                select.find('option').remove(); 
                $('#msg').html('');  
                $.each(response, function(index, value) {
                    $('<option>').val(index).text(value).appendTo(select);
           		});
		
		}
	});
	}); 
	
	$('#Method').change(function(event) {
	    
		 $('#msg').html('');
	});
});
</script>

<script>
 //$('#invokeMethod').on('click',function(e){
 function validate() {
	 var serviceClass = $('select#classes').val();
    var serviceMethod = $('select#Method').val();
	if(serviceClass == null && serviceMethod == "Select Method" || serviceMethod == null) {
    		$('#msg').html('');
			alert('Select Class Name/Method Name');
    		return;
    }

		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "exposingRestServices/invokeMethod.do",
			data : {"serviceName":serviceClass, "methodFolderName":serviceMethod,"client":"am"},
			dataType : 'json',
			timeout : 100000,
			success : function(response) {
				
				var responseObj = JSON.stringify(response,null,3);	 
				
				console.log("SUCCESS: ", responseObj);
				$('#msg').html(responseObj);
				
			},
			error : function(e) {
			 alert('Error while invoking the Method');
        	 }
		});
}

</script>
</head>
<body>
	<h2 align="center">Invoke Rest Services Methods</h2>
		
		<form action="">
        <b> Select Class: </b>
		<select id="classes">
		<option selected disabled value="">Select Class</option>
		 <c:forEach items="${serviceInterfaceNames}" var="serviceInterface">
			<option value="${serviceInterface}">
				${serviceInterface}
			</option>
		</c:forEach>
		</select>
		
		
        <br/> <br/> <br/>
        <b> Select Method: </b>
        <select id="Method">
                <option>Select Method</option>
        </select>
        <br/> <br/> <br/>
		<input id="invoke" type="button" onclick="return validate()" value="Invoke"/>
		<br/> <br/> <br/>
		<pre>
		<div id="msg"></div>
		</pre>
		</form>
		
		
</body>
</html>