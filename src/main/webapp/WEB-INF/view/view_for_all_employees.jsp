<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>
<body>

 <h3> information for all employee </h3>
<br><br>

 <security:authorize access="hasRole('HR')">
<input type="button" value="salary" onclick="window.location.href = 'hr_info'">
Only for HR stuff
 </security:authorize>
 <br><br>
 <security:authorize access="hasRole('Manager')">
 <input type="button" value="Performance" onclick="window.location.href = 'manager_info'">
 Only for Managers
 </security:authorize>

</body>
</html>