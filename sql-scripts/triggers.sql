
CREATE TRIGGER registration_insert AFTER INSERT ON `registration` FOR EACH ROW INSERT INTO taste_users set registration_id = NEW.id;



delimiter |
CREATE TRIGGER transaction_hostname_insert BEFORE INSERT ON `transaction`
FOR EACH ROW

IF NEW.USER_ID is null THEN
	SET NEW.taste_users_id= (SELECT MAX(id) from taste_users where hostname=NEW.hostname);
	IF NEW.taste_users_id is null then
		INSERT INTO taste_users SET hostname=NEW.hostname;
		SET NEW.taste_users_id= (SELECT MAX(id) from taste_users where hostname=NEW.hostname);
	END IF;
ELSE
SET NEW.taste_users_id= (SELECT MAX(id) from taste_users where registration_id=NEW.USER_ID);
END IF;
|
delimeter ;