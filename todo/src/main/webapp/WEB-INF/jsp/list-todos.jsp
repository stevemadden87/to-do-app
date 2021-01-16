<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <div class="row">
    <div class="col-md-6 col-md-offset-3 ">
      <div class="panel panel-primary">
        <div class="panel-heading">Add TODO</div>
        <div class="panel-body">
          <form:form method="post" modelAttribute="todo">
            <form:hidden path="id" />
            <fieldset class="form-group">
              <form:label path="description">Description</form:label>
              <form:input path="description" type="text" class="form-control"
                required="required" />
              <form:errors path="description" cssClass="text-warning" />
            </fieldset>

            <fieldset class="form-group">
              <form:label path="targetDate">Target Date</form:label>
              <form:input path="targetDate" type="text" class="form-control"
                required="required" />
              <form:errors path="targetDate" cssClass="text-warning" />
            </fieldset>

            <button type="submit" class="btn btn-success">Save</button>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>