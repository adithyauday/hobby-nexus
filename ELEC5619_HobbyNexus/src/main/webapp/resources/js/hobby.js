function openCloseComment(id){
	if($("."+id).is(":hidden")){
		$("."+id).show();
	}else{
		$("."+id).hide();

	}
}

function openDetail(userid){
	$("#members li,#members div").hide();	
	//$("#"+userid).show();
	$("."+userid).show();
}