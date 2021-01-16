<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <title></title>
      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
   </head>
   <body>
      <div class="container">
         <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
               <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <h2>${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
         </c:if>
      </div>
      <div class="container">
         <div>




        <div class="col-xs-12">
    <div class="col-xs-3"><a><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addTodo">
            Add Todo
            </button></a></div>
    <div class="col-xs-3"><a><div>
      <form action="/todo">
            <select class="form-control" id="records" name="size">
                 <option value="${noItems}" selected>Displaying ${noItems} of ${totalItems}</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11" >11</option>
                <option value="12" >12</option>
                <option value="13" >13</option>
                <option value="14" >14</option>
                <option value="15" >15</option>
            </select>
         <input type="hidden" name="currentPage" value="${pageNum}">
        <button type="submit" class="btn btn-dark">Go</button>
    </form>
  </div></a></div>
</div>
<br>
<br>
<br>
<c:choose>
   <c:when test="${not empty todos}">
     <!-- Button trigger modal -->
         </div>
         <br>
         <div class="panel panel-primary">
            <div class="panel-body">
   

               <table class="table table-striped">
                  <thead>
                     <tr>
                        <th width="40%">Item</th>
                        <th width="40%">Last Updated</th>
                        <th width="20%"></th>
                     </tr>
                  </thead>
                <tbody>
                     <c:forEach items="${todos}" var="todo">
                        <tr>
                           <td>
                              <c:if test="${!todo.isDone}"><a type="button" class=" btn-sm btn-warning"
                                 href="/todo/state?id=${todo.id}">Not Done</a>  ${todo.description}</c:if>
                              <c:if test="${todo.isDone}"><a type="button" class="btn-sm btn-success"
                                 href="/todo/state?id=${todo.id}">Done</a> ${todo.description}</c:if>
                           </td>
                           <td>${todo.lastUpdated}</td>
                           <td><button type="button" class="btn btn-info editTodo" data-toggle="modal" data-id="${todo.id}" data-target="#editTodo">
                              Edit
                              </button>
                              <a type="button" class="btn btn-danger"
                                 href="/todo/delete?id=${todo.id}">Delete</a>
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
                     <div class="d-inline">
    <nav aria-label="Navigation for countries">
        <ul class="pagination">
            <c:if test="${pageNum != 0}">
                <li class="page-item"><a class="page-link"
                    href="/todo?currentPage=${pageNum - 1}&size=${size}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="0" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                    
                    </c:when>

                </c:choose>
            </c:forEach>


            <c:forEach begin="0" end="${noOfPages - 1}" var="i">
                <c:choose>
                    <c:when test="${pageNum eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                            href="/todo?currentPage=${i}&size=${size}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            
          <c:if test="${pageNum < noOfPages - 1}">
                <li class="page-item"><a class="page-link"
                    href="/todo?currentPage=${pageNum + 1}&size=${size}">Next</a>
                </li>
            </c:if>

                
     
        </ul>
    </nav>
     </div>
         </div>
      </div>
      
   </c:when>
   <c:otherwise>
       Select less items to display
   </c:otherwise>
</c:choose>    
      <!-- Modal -->
      <div class="modal fade" id="addTodo" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalCenterTitle">New Todo</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body">
                  <form:form method="POST" 
                     action="new" modelAttribute="todoItem">
                     <div class="form-group">
                        <label class="control-label">Description</label>
                        <div>
                           <input type="text" class="form-control input-lg" id="description" name="description" value="">
                        </div>
                     </div>
                     <div class="form-group">
                        <div>
                           <button type="submit" value="Submit" class="btn btn-success">Add</button>
                        </div>
                     </div>
                  </form:form>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
               </div>
            </div>
         </div>
      </div>
      <!-- Modal -->
      <div class="modal fade" id="editTodo" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalCenterTitle">Edit</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body">
                  <form:form method="POST" 
                     action="edit" modelAttribute="todoItem">
                     <div class="form-group">
                        <div class="control">
                           <div class="todoID">
                           </div>
                        </div>
                        <label class="control-label">Description</label>
                        <div>
                           <input type="text" class="form-control input-lg" id="description" name="description" value="">
                        </div>
                     </div>
                     <div class="form-group">
                        <div>
                           <button type="submit" value="Submit" class="btn btn-success">Done</button>
                        </div>
                     </div>
                  </form:form>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
               </div>
            </div>
         </div>
      </div>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jsrender/0.9.90/jsrender.min.js"></script>
      <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
      <script type="text/javascript">
         $(".editTodo").click(function(e) {
         var tmpl = $.templates('<input name="itemId" type="hidden" value="{{:name}}"placeholder="Text input">');
         var result = {name: $(e.currentTarget).data('id')};
         // Render template for person object
         var htmlResult = tmpl.render(result);
         $("#editTodo").addClass("is-active");
         $(".todoID").html(htmlResult);
         });
         
         $(".btn-close").click(function() {
          $("#editTodo").removeClass("is-active");
         });
         
         $(".btn-save").click(function() {
          alert('Saved');
         });
      </script>
   </body>
</html>