
/* ---------------------------------------------------------------------- */
/* sf_guard_group											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'sf_guard_group')
BEGIN
	 DECLARE @reftable_31 nvarchar(60), @constraintname_31 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'sf_guard_group'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_31, @constraintname_31
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_31+' drop constraint '+@constraintname_31)
	   FETCH NEXT from refcursor into @reftable_31, @constraintname_31
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [sf_guard_group]
END


CREATE TABLE [sf_guard_group]
(
	[id] INT  NOT NULL IDENTITY,
	[name] VARCHAR(255)  NOT NULL,
	[description] TEXT  NULL,
	CONSTRAINT [sf_guard_group_PK] PRIMARY KEY ([id]),
	UNIQUE ([name])
);

/* ---------------------------------------------------------------------- */
/* sf_guard_permission											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'sf_guard_permission')
BEGIN
	 DECLARE @reftable_32 nvarchar(60), @constraintname_32 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'sf_guard_permission'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_32, @constraintname_32
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_32+' drop constraint '+@constraintname_32)
	   FETCH NEXT from refcursor into @reftable_32, @constraintname_32
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [sf_guard_permission]
END


CREATE TABLE [sf_guard_permission]
(
	[id] INT  NOT NULL IDENTITY,
	[name] VARCHAR(255)  NOT NULL,
	[description] TEXT  NULL,
	CONSTRAINT [sf_guard_permission_PK] PRIMARY KEY ([id]),
	UNIQUE ([name])
);

/* ---------------------------------------------------------------------- */
/* sf_guard_group_permission											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='sf_guard_group_permission_FK_1')
	ALTER TABLE [sf_guard_group_permission] DROP CONSTRAINT [sf_guard_group_permission_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='sf_guard_group_permission_FK_2')
	ALTER TABLE [sf_guard_group_permission] DROP CONSTRAINT [sf_guard_group_permission_FK_2];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'sf_guard_group_permission')
BEGIN
	 DECLARE @reftable_33 nvarchar(60), @constraintname_33 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'sf_guard_group_permission'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_33, @constraintname_33
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_33+' drop constraint '+@constraintname_33)
	   FETCH NEXT from refcursor into @reftable_33, @constraintname_33
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [sf_guard_group_permission]
END


CREATE TABLE [sf_guard_group_permission]
(
	[group_id] INT  NOT NULL,
	[permission_id] INT  NOT NULL,
	CONSTRAINT [sf_guard_group_permission_PK] PRIMARY KEY ([group_id],[permission_id])
);

BEGIN
ALTER TABLE [sf_guard_group_permission] ADD CONSTRAINT [sf_guard_group_permission_FK_1] FOREIGN KEY ([group_id]) REFERENCES [sf_guard_group] ([id]) ON DELETE CASCADE
END
;

BEGIN
ALTER TABLE [sf_guard_group_permission] ADD CONSTRAINT [sf_guard_group_permission_FK_2] FOREIGN KEY ([permission_id]) REFERENCES [sf_guard_permission] ([id]) ON DELETE CASCADE
END
;

/* ---------------------------------------------------------------------- */
/* sf_guard_user											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'sf_guard_user')
BEGIN
	 DECLARE @reftable_34 nvarchar(60), @constraintname_34 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'sf_guard_user'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_34, @constraintname_34
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_34+' drop constraint '+@constraintname_34)
	   FETCH NEXT from refcursor into @reftable_34, @constraintname_34
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [sf_guard_user]
END


CREATE TABLE [sf_guard_user]
(
	[id] INT  NOT NULL IDENTITY,
	[username] VARCHAR(128)  NOT NULL,
	[algorithm] VARCHAR(128) default 'sha1' NOT NULL,
	[salt] VARCHAR(128)  NOT NULL,
	[password] VARCHAR(128)  NOT NULL,
	[created_at] DATETIME  NULL,
	[last_login] DATETIME  NULL,
	[is_active] INT default 1 NOT NULL,
	[is_super_admin] INT default 0 NOT NULL,
	CONSTRAINT [sf_guard_user_PK] PRIMARY KEY ([id]),
	UNIQUE ([username])
);

/* ---------------------------------------------------------------------- */
/* sf_guard_user_permission											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='sf_guard_user_permission_FK_1')
	ALTER TABLE [sf_guard_user_permission] DROP CONSTRAINT [sf_guard_user_permission_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='sf_guard_user_permission_FK_2')
	ALTER TABLE [sf_guard_user_permission] DROP CONSTRAINT [sf_guard_user_permission_FK_2];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'sf_guard_user_permission')
BEGIN
	 DECLARE @reftable_35 nvarchar(60), @constraintname_35 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'sf_guard_user_permission'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_35, @constraintname_35
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_35+' drop constraint '+@constraintname_35)
	   FETCH NEXT from refcursor into @reftable_35, @constraintname_35
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [sf_guard_user_permission]
END


CREATE TABLE [sf_guard_user_permission]
(
	[user_id] INT  NOT NULL,
	[permission_id] INT  NOT NULL,
	CONSTRAINT [sf_guard_user_permission_PK] PRIMARY KEY ([user_id],[permission_id])
);

BEGIN
ALTER TABLE [sf_guard_user_permission] ADD CONSTRAINT [sf_guard_user_permission_FK_1] FOREIGN KEY ([user_id]) REFERENCES [sf_guard_user] ([id]) ON DELETE CASCADE
END
;

BEGIN
ALTER TABLE [sf_guard_user_permission] ADD CONSTRAINT [sf_guard_user_permission_FK_2] FOREIGN KEY ([permission_id]) REFERENCES [sf_guard_permission] ([id]) ON DELETE CASCADE
END
;

/* ---------------------------------------------------------------------- */
/* sf_guard_user_group											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='sf_guard_user_group_FK_1')
	ALTER TABLE [sf_guard_user_group] DROP CONSTRAINT [sf_guard_user_group_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='sf_guard_user_group_FK_2')
	ALTER TABLE [sf_guard_user_group] DROP CONSTRAINT [sf_guard_user_group_FK_2];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'sf_guard_user_group')
BEGIN
	 DECLARE @reftable_36 nvarchar(60), @constraintname_36 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'sf_guard_user_group'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_36, @constraintname_36
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_36+' drop constraint '+@constraintname_36)
	   FETCH NEXT from refcursor into @reftable_36, @constraintname_36
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [sf_guard_user_group]
END


CREATE TABLE [sf_guard_user_group]
(
	[user_id] INT  NOT NULL,
	[group_id] INT  NOT NULL,
	CONSTRAINT [sf_guard_user_group_PK] PRIMARY KEY ([user_id],[group_id])
);

BEGIN
ALTER TABLE [sf_guard_user_group] ADD CONSTRAINT [sf_guard_user_group_FK_1] FOREIGN KEY ([user_id]) REFERENCES [sf_guard_user] ([id]) ON DELETE CASCADE
END
;

BEGIN
ALTER TABLE [sf_guard_user_group] ADD CONSTRAINT [sf_guard_user_group_FK_2] FOREIGN KEY ([group_id]) REFERENCES [sf_guard_group] ([id]) ON DELETE CASCADE
END
;

/* ---------------------------------------------------------------------- */
/* sf_guard_remember_key											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='sf_guard_remember_key_FK_1')
	ALTER TABLE [sf_guard_remember_key] DROP CONSTRAINT [sf_guard_remember_key_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'sf_guard_remember_key')
BEGIN
	 DECLARE @reftable_37 nvarchar(60), @constraintname_37 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'sf_guard_remember_key'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_37, @constraintname_37
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_37+' drop constraint '+@constraintname_37)
	   FETCH NEXT from refcursor into @reftable_37, @constraintname_37
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [sf_guard_remember_key]
END


CREATE TABLE [sf_guard_remember_key]
(
	[user_id] INT  NOT NULL,
	[remember_key] VARCHAR(32)  NULL,
	[ip_address] VARCHAR(50)  NOT NULL,
	[created_at] DATETIME  NULL,
	CONSTRAINT [sf_guard_remember_key_PK] PRIMARY KEY ([user_id],[ip_address])
);

BEGIN
ALTER TABLE [sf_guard_remember_key] ADD CONSTRAINT [sf_guard_remember_key_FK_1] FOREIGN KEY ([user_id]) REFERENCES [sf_guard_user] ([id]) ON DELETE CASCADE
END
;
