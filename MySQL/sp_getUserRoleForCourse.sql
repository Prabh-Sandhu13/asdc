USE `CSCI5308_22_DEVINT`;
DROP procedure IF EXISTS `sp_getUserRoleForCourse`;

DELIMITER $$
USE `CSCI5308_22_DEVINT`$$
CREATE PROCEDURE `sp_getUserRoleForCourse` (IN userId VARCHAR(255), IN courseId VARCHAR(255))
BEGIN
	Select role_id  from role_assignment where user_id = userId and course_id = courseId;
END$$

DELIMITER ;