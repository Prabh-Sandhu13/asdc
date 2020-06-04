USE `CSCI5308_22_DEVINT`;
DROP procedure IF EXISTS `sp_addTAToCourse`;

DELIMITER $$
USE `CSCI5308_22_DEVINT`$$
CREATE PROCEDURE `sp_addTAToCourse`(
	IN userId VARCHAR(40)
	,IN courseId VARCHAR(40)
	)
BEGIN
	INSERT INTO role_assignment (
		user_id
		,course_id
		,yt_id
		,role_id
		)
	VALUES (
		userId
		,courseId
		,0
		,2
		);
END$$

DELIMITER ;