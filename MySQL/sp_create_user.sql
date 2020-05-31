DELIMITER $$

DROP PROCEDURE IF EXISTS sp_create_user $$

CREATE PROCEDURE `sp_create_user`(
	IN bannerId VARCHAR(40)
	,IN firstName VARCHAR(40)
	,IN lastName VARCHAR(40)
	,IN email VARCHAR(255)
	,IN password VARCHAR(255)
	)
BEGIN
	INSERT INTO users(
		first_name
		,last_name
		,email
		,password
		,banner_id
		)
	VALUES(
		firstName
		,lastName
		,email
		,password
		,bannerId
		);
        
END $$
DELIMITER ;
