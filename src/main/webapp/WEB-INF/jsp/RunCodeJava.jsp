<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath%>jquery/jquery.min.js"></script>
<script type="text/javascript">
function runCode(){
	$.ajax({
		url:'<%=basePath%>runCode/',
			type : "POST",
			data : {
				'code' : $('#code').val()
			},
			success : function(data) {
				//$("#rendered").attr("src","data:image/jpeg;base64,"+data);
				$('#result').val(data);
			}
		});
	}
</script>
<title>RunCode for Java</title>
</head>
<body>
	<p>
		<textarea name="textarea" id="code" name="code" cols="150" rows="10">
public class RunCode {
public static void main(String[] args) {
    // TODO Auto-generated method stub
        for(int i=0;i<10;i++){
            System.out.println(i);
        }
    }
}
		</textarea>
	</p>
	<p>
		<input type="button" name="button" id="button" onclick="runCode()" value="执行代码" />
	</p>
	<p>
		<textarea name="textarea2" id="result" name="result" cols="150"
			rows="10"></textarea>
	</p>
</body>
</html>