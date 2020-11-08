<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>

	<title>Hobby</title>
	
	<link rel="stylesheet" href="<c:url value="/resources/css/hobby.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/profile.css"/>">
	 <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/resources/js/hobby.js"/>"></script>
	 
</head>
<body>
	<!-- include header -->
	<jsp:include page="header.jsp" />
	<div class="godown-60" id="godown"></div>
	<div class=" main-container">
		<div class="container">
			<div class="container bs-docs-container" style="padding-top:10px;">
		 	<div class="ne-hobby-name">
		 	<img alt="" width="100px" height="100px" src="<%=request.getContextPath()%>/imgDisplay?id=${hobby.hobby_id }&type=hobby">
		 	<div class="hobbyname"><h3 >${hobby.hobby_name }</h3><a class="btn  btn-default" href="#" role="button"  data-toggle="modal" data-target="#myModal">Create Hobby</a></div>
		 	
		 	</div>
		 	<p class="hobbydesc">${hobby.hobby_desc }</p>
		 	
		 	<h3 class="mb-3">Posts</h3>
		      <div class="row">
		        <div class="col-md-9" role="main">
						<div class="ne-hobby-item">
						<c:forEach var="obj" items="${articles}">
							<div class="ne-hobby-article" >
								<dl>
									<dt style="font-size: 16px;"><a href="javascript:openDetail('ne-user-${obj.user.user_id}')" style="color: #212529;"><img alt="" width="30px" height="30px" src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user.user_id }&type=user">&nbsp;&nbsp;&nbsp;${obj.user.user_name }</a></dt>
									<dt style="font-size: 20px;"><a href="<%=request.getContextPath()%>/post?article_id=${obj.artice_id}" >${obj.title }</a></dt>
									<dd style="font-size: 18px;">${obj.content }</dd>
								</dl>
								<div class="ne-hobby-comment">
									<div class="ne-comment-num">
									<a  href="javascript:openCloseComment('ne-artice-${obj.artice_id}')"  >${obj.comment_num } comments </a>  </div>
									<c:forEach var="comment" items="${obj.comments}">
										<div class="ne—comment ne-artice-${obj.artice_id}" style="display:none">
											<dl>
											<dt><img alt="" width="20px" height="20px" src="<%=request.getContextPath()%>/imgDisplay?id=${comment.user.user_id }&type=user">&nbsp;&nbsp;&nbsp;${obj.user.user_name }</dt>
											<dd>${comment.content }</dd>
											</dl>
										</div>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
						</div>
					</div>
		        <div class="col-md-3" >
		          <nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix-top">			
		          <ul class="section-nav" id="members">
					<c:forEach var="obj" items="${users}">
					<li class="toc-entry toc-h2" ><a href="javascript:openDetail('ne-user-${obj.user_id}')"><img alt="" width="20px" height="20px" src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user_id }&type=user">&nbsp;&nbsp;&nbsp;${obj.user_name}</a></li>
					<div style="display:none" class="ne-user-${obj.user_id}">
						<img alt="" width="90px" height="100px" src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user_id}&type=user">&nbsp;&nbsp;&nbsp;
						<dl>
						<dt>${obj.user_name}</dt>
						<dt>email:${obj.email}</dt>
						<dt>location:${obj.location }</dt>
						</dl>
					</div>
					</c:forEach>
					</ul>
					
		
		          </nav>
		        </div>
		        
		      </div>
		    </div>
	    </div>
    </div>
<!--   <div class="modal fade" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
   
        <div class="modal-header">
          <h4 class="modal-title">Create Hobby</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
   
        <div class="modal-body">
            <form enctype="multipart/form-data" method="post" id="form1" name="form1" >

          <div class="form-group">
            <label for="recipient-name" class="control-label">Hobby Name:</label>
            <input type="text" class="form-control" id="hobbyname">
          </div>

          <div class="form-group">
            <label for="recipient-name" class="control-label">Hobby Name:</label>
              <input id="file-fr"  style="display: block;" name="file" type="file" multiple>
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Description:</label>
            <textarea class="form-control"  id="description"></textarea>
          </div>
    </form>

   
        <div class="modal-footer">
           <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="sub()">Save message</button>
        </div>
   
      </div>
    </div>
  </div>
  
</div>    -->

<%-- <script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript">
function sub(){
	var description=$("#description").val();
	var hobbyname=$("#hobbyname").val();
	$("#form1").ajaxSubmit({
		url:'<%=request.getContextPath()%>/savehobby?hobby_name='+hobbyname+'&hobby_desc='+description,
		dataType:'json',
		type:'post',
		success:function(res){
			if(res.result){
				alert(res.msg);
				$('#myModal').modal('hide');
				location.reload();
			}else{
				alert(res.msg);
			}
		}
	});
}

</script> --%>
</body>
</html>
