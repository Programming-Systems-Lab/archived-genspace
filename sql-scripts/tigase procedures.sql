drop procedure `TigBuddies`;
drop PROCEDURE `TigBuddyDetails`;
drop procedure `TigRosterDetails`;

delimiter //
CREATE PROCEDURE `TigBuddies`(_user_jid varchar(2049) CHARSET utf8)
begin
    SELECT r.username as login FROM registration r
	inner join Friend f on f.id_2=r.id
	inner join registration r2 on r2.id=f.id_1
	where f.mutual = 1 and r2.username=_user_jid
	union 
	SELECT r.username as login FROM registration r
	inner join User_Network n on n.user_id=r.id
	inner join User_Network n2 on n2.network_id=n.network_id
	inner join registration r2 on r2.id=n2.user_id
	where  r2.username=_user_jid;
end //

CREATE PROCEDURE `TigBuddyDetails`(_user_jid varchar(2049) CHARSET utf8, _buddy_jid varchar(2049) CHARSET utf8)
begin
    # a query for the details of a user's entire roster
    # returns columns: group, jid, name, subscription
   # SELECT "Friends" as "group", username as "jid", first_name as "name", "both" as "subscription" FROM registration;
   DECLARE f INTEGER;
	SELECT fr.id into f  from Friend fr 
	inner join registration r2 on r2.id=fr.id_2
	inner join registration r on r.id=fr.id_1
	where r.username=_user_jid and r2.username=_buddy_jid and fr.mutual = 1 and fr.visible = 1;
	IF f is null
	THEN
			SELECT net.name as "group", r.username as "jid",concat(r.first_name," ",r.last_name) as "name", "both" as "subscription" FROM registration r
			inner join User_Network n on n.user_id=r.id
			inner join User_Network n2 on n2.network_id=n.network_id
			inner join registration r2 on r2.id=n2.user_id
			inner join Network net on net.id=n.network_id
			where  r2.username=_user_jid and r.username=_buddy_jid;
	ELSE
		SELECT "Friends" as "group",username as "jid", concat(first_name," ",last_name) as "name", "both" as "subscription" from registration where username=_buddy_jid;
	END IF;
end //

CREATE PROCEDURE `TigRosterDetails`(_user_jid varchar(2049) CHARSET utf8)
begin
    # a query for the details of a user's entire roster
    # returns columns: group, jid, name, subscription
select * from (    SELECT "Friends" as "group", r.username as "jid", concat(r.first_name," ",r.last_name)  as "name", "both" as "subscription" FROM registration r
	inner join Friend f on f.id_2=r.id
	inner join registration r2 on r2.id=f.id_1
	where f.mutual = 1 and r2.username=_user_jid
	union 
	SELECT net.name as "group", r.username as "jid", concat(r.first_name," ",r.last_name)  as "name", "both" as "subscription" FROM registration r
	inner join User_Network n on n.user_id=r.id
	inner join User_Network n2 on n2.network_id=n.network_id
	inner join registration r2 on r2.id=n2.user_id
	inner join Network net on net.id=n.network_id
	where  r2.username=_user_jid) t group by jid ;
end //