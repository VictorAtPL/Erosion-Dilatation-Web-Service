<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.image {
	display: inline-block;
	max-width: 48%;
}
</style>
</head>
<body>
<f:view>
Aplikacja umożliwiająca przetwarzanie obrazów graficznych. Realizuje nałożenie filtru o efektach erozji i dylatacji.<br />

<h:form id="form" enctype="multipart/form-data">
	Filtr: <br />
	<h:selectOneRadio required="true" value="#{uploadPage.filter}">
		<f:selectItem itemValue="erozja" itemLabel="wartość minimalna wyznaczona z otoczenia [3,3] (efekt erozji - zmniejszenie lub eliminacja drobnych, jasnych detali obrazu)"/>
		<f:selectItem itemValue="dylatacja" itemLabel="wartość maksymalna wyznaczona z otoczenia [3,3] (efekt dylatacji - uwypuklenie poprzez powiększenie jasnych elementów obrazu)"/>
	</h:selectOneRadio>
	<h:inputFile id="uploadedFile" value="#{uploadPage.uploadedFile}" validator="#{uploadPage.validateFile}"/>
	<h:commandButton value="Nałóż filtr" action="#{uploadPage.upload}"/>
	<h:messages globalOnly="false" />
	<br />
	<h:graphicImage rendered="false" styleClass="image" binding="#{uploadPage.graphicImageBefore}" />
	<h:graphicImage rendered="false" styleClass="image" binding="#{uploadPage.graphicImageAfter}" />
</h:form>


</f:view>
</body>
</html>