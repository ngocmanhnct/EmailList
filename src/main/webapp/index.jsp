<%-- Web Pages/index.jsp --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Murach's Java Servlets and JSP</title>
        <link rel="stylesheet" href="style.css" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <h1>Join our email list</h1>
            <p>To join our email list, enter your name and email address below.</p>
            
            <form action="emailList" method="post">
                
                <input type="hidden" name="action" value="add">
                
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label>First Name:</label>
                    <input type="text" name="firstName" required>
                </div>
                
                <div class="form-group">
                    <label>Last Name:</label>
                    <input type="text" name="lastName" required>
                </div>
                
                <input type="submit" value="Join Now">
            </form>
        </div>
    </body>
</html>