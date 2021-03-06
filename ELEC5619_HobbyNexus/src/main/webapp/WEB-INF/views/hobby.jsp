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
		 	<div class="hobbyname"><h3 >${hobby.hobby_name }</h3>
		 	</div>
		 	<c:if test="${currentUser != null}">	
		 	<a class="btn btn-default float-right" href="#" role="button"  data-toggle="modal" data-target="#myModal">Create Hobby</a>
		 	<a class="btn btn-default float-right" href="#" role="button"  data-toggle="modal" data-target="#myPost">Create Post</a>
		 	<form class="mx-2 my-auto d-inline w-100 float-right" action="joinhobby/${hobby.hobby_id}" method="post">
		       	<input class="btn btn-default float-right" type="submit" value="Join Hobby" name = "joinButton"/>     
		    </form>
		 	
		 	<!-- <a class="btn btn-default float-right" role="button">Join Hobby</a> -->
		 	</c:if>
		 	</div>
		 	<p class="hobbydesc">${hobby.hobby_desc }</p>
		 	
		 	<h3 class="mb-3">Posts</h3>
		      <div class="row">
		        <div class="col-md-9" role="main">
						<div class="ne-hobby-item">
						<c:forEach var="obj" items="${articles}">
							<div class="ne-hobby-article" >
								<dl>
									<dt style="font-size: 16px;"><a href="/nexus/viewProfile?user_id=${obj.user.user_id}" style="color: #212529;"><img alt="" width="30px" height="30px" src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user.user_id }&type=user">&nbsp;&nbsp;&nbsp;${obj.user.user_name }</a></dt>
									<dt style="font-size: 20px;"><a href="<%=request.getContextPath()%>/post?article_id=${obj.artice_id}" >${obj.title }</a></dt>
									<dd style="font-size: 18px;">${obj.content }</dd>
								</dl>
								<div class="ne-hobby-comment">
									<div class="ne-comment-num">
									<a  href="javascript:openCloseComment('ne-artice-${obj.artice_id}')"  >${obj.comment_num } comments </a>  </div>
									<c:forEach var="comment" items="${obj.comments}">
										<div class="ne???comment ne-artice-${obj.artice_id}" style="display:none">
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
		        	<h4 class="mb-3">Group Members</h4>		        
		        	<nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix-top">			
		          	<ul class="section-nav" id="members">
						<c:forEach var="obj" items="${users}">
							<li class="toc-entry toc-h2" ><a href="/nexus/viewProfile?user_id=${obj.user_id}"><img alt="" width="20px" height="20px" src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user_id }&type=user">&nbsp;&nbsp;&nbsp;${obj.user_name}</a></li>
						</c:forEach>
					</ul>
					</nav>
		        </div>
		        
		      </div>
		    </div>
	    </div>
    </div>
 <div class="modal fade" id="myPost">
    <div class="modal-dialog">
      <div class="modal-content">
   
        <div class="modal-header">
          <h4 class="modal-title">Create Post</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
   
        <div class="modal-body">
            <form  method="post" id="form2" name="form2" >

          <div class="form-group">
            <label for="recipient-name" class="control-label">Article Title<font style="color: red;">*</font>:</label>
            <input type="text" name="title" class="form-control" id="title">
          </div>

          <div class="form-group">
            <label for="message-text" class="control-label">Article Content<font style="color: red;">*</font>:</label>
            <textarea class="form-control" name="content"   id="content"></textarea>
          </div>
    </form>

   
        <div class="modal-footer">
           <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="sub1()">Save</button>
        </div>
   
      </div>
    </div>
  </div>
  
</div> 

<script type="text/javascript">
function sub1(){
	var title=$("#title").val();
	if(title==""||title.length==0){
		alert("Title cannot be empty");
		return;
	}

	var content=$("#content").val();
	if(content==""||content.length==0){
		alert("Content cannot be empty");
		return;
	}
	$.ajax({
		url:'<%=request.getContextPath()%>/saveArticle',
		dataType:'json',
		type:'post',
		data:{'title':title,'content':content,'hobby_id':'${hobby.hobby_id }'},
		success:function(res){
			if(res.result){
				alert(res.msg);
				$('#myPost').modal('hide');
				window.location.href='<%=request.getContextPath()%>/hobby?hobby_id=${hobby.hobby_id }';
			}else{
				alert(res.msg);
			}
		}
	});
}

</script>
</body>
</html>
