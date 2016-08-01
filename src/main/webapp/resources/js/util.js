
/* textMax check*/
function chkword(obj , maxByte) {
		var korSize = 3;
		var enterSize = 2;
	
        var strValue = obj.val();
        var strLen = strValue.length;
        var totalByte = 0;
        var len = 0;
        var oneChar = "";
        var str2 = "";
        
        var obj2 =  $( "#"+obj.attr('id') +"Length");
        
        for (var i = 0; i < strLen; i++) {
            oneChar = strValue.charAt(i);
            if (escape(oneChar).length > 4) {
                totalByte += korSize;
            }else if( escape(oneChar)== '%0A' ){
            	totalByte += enterSize;
            }else{
                totalByte++;
            }
 
            // 입력한 문자 길이보다 넘치면 잘라내기 위해 저장
            if (totalByte <= maxByte) {
                len = i + 1;
            }
        }
        
        // 넘어가는 글자는 자른다.
        if (totalByte > maxByte) {
            alert(maxByte + "자를 초과 입력 할 수 없습니다.");
            str2 = strValue.substr(0, len);
            obj.val(str2);
            chkword(obj , maxByte);
        }else{
        	obj2.html( totalByte+" / "+ maxByte ); 
        }
}

/*errText add*/
function addErrMSG(data) {
	if(data.sendMsg.val() == 'hasErr'){
		var errMsg = "<div class='alert alert-danger alert-dismissible'>" +
        "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button>" +
       "<h4><i class='icon fa fa-ban'></i> Alert!</h4>잘못 된 값을 입력하였습니다.</div>";
		  
		$('.box-header').append(errMsg);
		
	}
	data.sendMsg.val(''); // sendMsg 값 제거
}

/*delelteText add*/
function addDeleteMSG(data) {
	var deleteMsgText =""; 
	if(data.sendMsg.val() == 'delSucc'){
		deleteMsgText = "<div class='alert alert-success alert-dismissible'>" +
        "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button>" +
       "<h4><i class='icon fa fa-check'></i>Success !</h4>글 삭제를 성공했습니다.</div>";
	}else if(data.sendMsg.val() == 'delFail'){
		deleteMsgText = "<div class='alert alert-danger alert-dismissible'>" +
        "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button>" +
        "<h4><i class='icon fa fa-ban'></i>Fail !</h4>답변이 있는 글은 삭제 할 수 없습니다.</div>";
	}
	data.sendMsg.val('');  // sendMsg 값 제거
	$('.box-header').append(deleteMsgText);
}