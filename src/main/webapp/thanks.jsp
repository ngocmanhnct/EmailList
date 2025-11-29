<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style.css" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <h1>Thanks for joining!</h1>
            <p>Here is the information that you entered:</p>
            <p>Email: ${param.email}</p>
            <p>First Name: ${param.firstName}</p>
            <p>Last Name: ${param.lastName}</p>
            <p>To enter another email address, click on the Back button in your browser or the Return button below.</p>
            
            <form action="index.jsp" method="get">
                <input type="submit" value="Return">
            </form>
        </div>
    </body>
</html>