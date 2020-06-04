DELIMITER $$
CREATE PROCEDURE `sp_deleteToken`(
IN token VARCHAR(255)
)
BEGIN
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
	DELETE from forgot_password where temporary_lik = token;
	COMMIT;
END$$
DELIMITER ;
