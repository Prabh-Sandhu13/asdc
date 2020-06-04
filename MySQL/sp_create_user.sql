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
	
	IF NOT EXISTS(Select * from users where email = emailId)
    THEN
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
		,emailId
		,password
		,bannerId
		);        
    ELSE
	UPDATE users set banner_id = bannerId where email = emailId;
    END IF;
END $$
DELIMITER ;
