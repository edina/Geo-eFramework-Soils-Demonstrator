<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <title>GeFC Demonstrator</title>
    <link rel="stylesheet" href="./theme/default/style.css" type="text/css" />
    <link rel="stylesheet" href="./theme/default/google.css" type="text/css" />
    <link rel="stylesheet" href="./css/gefcdemo.css" type="text/css" />
    <link rel="stylesheet" href="<c:url value="/resources/dijit/themes/tundra/tundra.css" />" type="text/css" />
    <script src="./OpenLayers.js" type="text/javascript"></script>
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;"></script>
    <script src="<c:url value="/resources/dojo/dojo.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/spring/Spring.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/spring/Spring-Dojo.js" />" type="text/javascript"></script>
  </head>
  <body>
    <div class="map" id="map">
      <tiles:insertAttribute name="map" />
    </div>
    <div id="content">
      <tiles:insertAttribute name="content" />
    </div>
    <div id="controls">
      <tiles:insertAttribute name="controls" />
    </div>
  </body>
</html>
