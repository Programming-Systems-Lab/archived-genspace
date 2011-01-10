
/* ---------------------------------------------------------------------- */
/* user_networks											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='user_networks_FK_1')
	ALTER TABLE [user_networks] DROP CONSTRAINT [user_networks_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='user_networks_FK_2')
	ALTER TABLE [user_networks] DROP CONSTRAINT [user_networks_FK_2];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'user_networks')
BEGIN
	 DECLARE @reftable_1 nvarchar(60), @constraintname_1 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'user_networks'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_1, @constraintname_1
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_1+' drop constraint '+@constraintname_1)
	   FETCH NEXT from refcursor into @reftable_1, @constraintname_1
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [user_networks]
END


CREATE TABLE [user_networks]
(
	[username] VARCHAR(50)  NOT NULL,
	[network] VARCHAR(50)  NOT NULL,
	CONSTRAINT [user_networks_PK] PRIMARY KEY ([username],[network])
);

CREATE INDEX [PK_user_networks] ON [user_networks] ([username],[network]);

BEGIN
ALTER TABLE [user_networks] ADD CONSTRAINT [user_networks_FK_1] FOREIGN KEY ([username]) REFERENCES [networks] ([id])
END
;

BEGIN
ALTER TABLE [user_networks] ADD CONSTRAINT [user_networks_FK_2] FOREIGN KEY ([network]) REFERENCES [networks] ([network])
END
;

/* ---------------------------------------------------------------------- */
/* networks											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'networks')
BEGIN
	 DECLARE @reftable_2 nvarchar(60), @constraintname_2 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'networks'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_2, @constraintname_2
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_2+' drop constraint '+@constraintname_2)
	   FETCH NEXT from refcursor into @reftable_2, @constraintname_2
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [networks]
END


CREATE TABLE [networks]
(
	[id] INT  NOT NULL IDENTITY,
	[network] VARCHAR(50)  NOT NULL,
	CONSTRAINT [networks_PK] PRIMARY KEY ([id])
);

/* ---------------------------------------------------------------------- */
/* user_IM_handles											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'user_IM_handles')
BEGIN
	 DECLARE @reftable_3 nvarchar(60), @constraintname_3 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'user_IM_handles'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_3, @constraintname_3
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_3+' drop constraint '+@constraintname_3)
	   FETCH NEXT from refcursor into @reftable_3, @constraintname_3
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [user_IM_handles]
END


CREATE TABLE [user_IM_handles]
(
	[username] VARCHAR(50)  NULL,
	[IM_handle] VARCHAR(50)  NULL,
	[IM_service] VARCHAR(50)  NULL,
	[id] INT  NOT NULL IDENTITY,
	CONSTRAINT [user_IM_handles_PK] PRIMARY KEY ([id])
);

/* ---------------------------------------------------------------------- */
/* login_events											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'login_events')
BEGIN
	 DECLARE @reftable_4 nvarchar(60), @constraintname_4 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'login_events'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_4, @constraintname_4
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_4+' drop constraint '+@constraintname_4)
	   FETCH NEXT from refcursor into @reftable_4, @constraintname_4
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [login_events]
END


CREATE TABLE [login_events]
(
	[username] VARCHAR(50)  NULL,
	[date] DATETIME  NULL,
	[id] INT  NOT NULL IDENTITY,
	CONSTRAINT [login_events_PK] PRIMARY KEY ([id])
);

/* ---------------------------------------------------------------------- */
/* analysis_events											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'analysis_events')
BEGIN
	 DECLARE @reftable_5 nvarchar(60), @constraintname_5 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'analysis_events'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_5, @constraintname_5
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_5+' drop constraint '+@constraintname_5)
	   FETCH NEXT from refcursor into @reftable_5, @constraintname_5
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [analysis_events]
END


CREATE TABLE [analysis_events]
(
	[username] VARCHAR(50)  NULL,
	[host] VARCHAR(50)  NULL,
	[date] DATETIME  NULL,
	[analysis] VARCHAR(50)  NULL,
	[dataset] VARCHAR(255)  NULL,
	[transaction_id] VARCHAR(255)  NULL,
	[is_genspace_user] VARCHAR(1)  NULL,
	[id] INT  NOT NULL IDENTITY,
	CONSTRAINT [analysis_events_PK] PRIMARY KEY ([id])
);

/* ---------------------------------------------------------------------- */
/* include_exclude_analysis											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'include_exclude_analysis')
BEGIN
	 DECLARE @reftable_6 nvarchar(60), @constraintname_6 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'include_exclude_analysis'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_6, @constraintname_6
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_6+' drop constraint '+@constraintname_6)
	   FETCH NEXT from refcursor into @reftable_6, @constraintname_6
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [include_exclude_analysis]
END


CREATE TABLE [include_exclude_analysis]
(
	[username] VARCHAR(50)  NULL,
	[analysis] VARCHAR(50)  NULL,
	[action] VARCHAR(50)  NULL,
	[id] INT  NOT NULL IDENTITY,
	CONSTRAINT [include_exclude_analysis_PK] PRIMARY KEY ([id])
);

/* ---------------------------------------------------------------------- */
/* friends											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'friends')
BEGIN
	 DECLARE @reftable_7 nvarchar(60), @constraintname_7 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'friends'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_7, @constraintname_7
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_7+' drop constraint '+@constraintname_7)
	   FETCH NEXT from refcursor into @reftable_7, @constraintname_7
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [friends]
END


CREATE TABLE [friends]
(
	[user1] VARCHAR(50)  NULL,
	[user2] VARCHAR(50)  NULL,
	[id] INT  NOT NULL IDENTITY,
	CONSTRAINT [friends_PK] PRIMARY KEY ([id])
);

/* ---------------------------------------------------------------------- */
/* test_analysis_events											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'test_analysis_events')
BEGIN
	 DECLARE @reftable_8 nvarchar(60), @constraintname_8 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'test_analysis_events'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_8, @constraintname_8
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_8+' drop constraint '+@constraintname_8)
	   FETCH NEXT from refcursor into @reftable_8, @constraintname_8
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [test_analysis_events]
END


CREATE TABLE [test_analysis_events]
(
	[username] VARCHAR(50)  NULL,
	[host] VARCHAR(50)  NULL,
	[date] DATETIME  NULL,
	[analysis] VARCHAR(50)  NULL,
	[dataset] VARCHAR(255)  NULL,
	[transaction_id] VARCHAR(255)  NULL,
	[is_genspace_user] VARCHAR(1)  NULL,
	[id] INT  NOT NULL IDENTITY,
	CONSTRAINT [test_analysis_events_PK] PRIMARY KEY ([id])
);

/* ---------------------------------------------------------------------- */
/* registration											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'registration')
BEGIN
	 DECLARE @reftable_9 nvarchar(60), @constraintname_9 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'registration'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_9, @constraintname_9
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_9+' drop constraint '+@constraintname_9)
	   FETCH NEXT from refcursor into @reftable_9, @constraintname_9
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [registration]
END


CREATE TABLE [registration]
(
	[username] VARCHAR(50)  NOT NULL,
	[password] VARCHAR(50)  NOT NULL,
	[email] VARCHAR(50)  NULL,
	[im_email] VARCHAR(50)  NULL,
	[im_password] VARCHAR(50)  NULL,
	[first_name] VARCHAR(50)  NOT NULL,
	[last_name] VARCHAR(50)  NOT NULL,
	[work_title] VARCHAR(50)  NULL,
	[phone] VARCHAR(50)  NULL,
	[lab_affiliation] VARCHAR(100)  NOT NULL,
	[addr1] VARCHAR(50)  NULL,
	[addr2] VARCHAR(50)  NULL,
	[city] VARCHAR(50)  NULL,
	[state] VARCHAR(50)  NULL,
	[zipcode] VARCHAR(5)  NULL,
	CONSTRAINT [registration_PK] PRIMARY KEY ([username])
);

CREATE INDEX [PK_dbo.registration] ON [registration] ([username]);

/* ---------------------------------------------------------------------- */
/* data_visibility											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='data_visibility_FK_1')
	ALTER TABLE [data_visibility] DROP CONSTRAINT [data_visibility_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'data_visibility')
BEGIN
	 DECLARE @reftable_10 nvarchar(60), @constraintname_10 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'data_visibility'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_10, @constraintname_10
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_10+' drop constraint '+@constraintname_10)
	   FETCH NEXT from refcursor into @reftable_10, @constraintname_10
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [data_visibility]
END


CREATE TABLE [data_visibility]
(
	[username] VARCHAR(50)  NOT NULL,
	[logdata] INT  NOT NULL,
	[datavisibility] INT  NOT NULL,
	CONSTRAINT [data_visibility_PK] PRIMARY KEY ([username])
);

CREATE INDEX [PK_data_visibility] ON [data_visibility] ([username]);

BEGIN
ALTER TABLE [data_visibility] ADD CONSTRAINT [data_visibility_FK_1] FOREIGN KEY ([username]) REFERENCES [registration] ([username])
END
;

/* ---------------------------------------------------------------------- */
/* user_visibility											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='user_visibility_FK_1')
	ALTER TABLE [user_visibility] DROP CONSTRAINT [user_visibility_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'user_visibility')
BEGIN
	 DECLARE @reftable_11 nvarchar(60), @constraintname_11 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'user_visibility'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_11, @constraintname_11
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_11+' drop constraint '+@constraintname_11)
	   FETCH NEXT from refcursor into @reftable_11, @constraintname_11
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [user_visibility]
END


CREATE TABLE [user_visibility]
(
	[username] VARCHAR(50)  NOT NULL,
	[uservisibility] INT  NOT NULL,
	[id] INT  NOT NULL IDENTITY,
	CONSTRAINT [user_visibility_PK] PRIMARY KEY ([id])
);

BEGIN
ALTER TABLE [user_visibility] ADD CONSTRAINT [user_visibility_FK_1] FOREIGN KEY ([username]) REFERENCES [registration] ([username])
END
;

/* ---------------------------------------------------------------------- */
/* audit											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='audit_FK_1')
	ALTER TABLE [audit] DROP CONSTRAINT [audit_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'audit')
BEGIN
	 DECLARE @reftable_12 nvarchar(60), @constraintname_12 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'audit'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_12, @constraintname_12
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_12+' drop constraint '+@constraintname_12)
	   FETCH NEXT from refcursor into @reftable_12, @constraintname_12
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [audit]
END


CREATE TABLE [audit]
(
	[username] VARCHAR(50)  NOT NULL,
	[action] VARCHAR(500)  NOT NULL,
	[tablename] VARCHAR(50)  NOT NULL,
	[beforevalue] VARCHAR(1000)  NOT NULL,
	[aftervalue] VARCHAR(1000)  NOT NULL,
	[time] DATETIME  NOT NULL,
	[id] INT  NOT NULL IDENTITY,
	CONSTRAINT [audit_PK] PRIMARY KEY ([id])
);

BEGIN
ALTER TABLE [audit] ADD CONSTRAINT [audit_FK_1] FOREIGN KEY ([username]) REFERENCES [registration] ([username])
END
;

/* ---------------------------------------------------------------------- */
/* network_visibility											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='network_visibility_FK_1')
	ALTER TABLE [network_visibility] DROP CONSTRAINT [network_visibility_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'network_visibility')
BEGIN
	 DECLARE @reftable_13 nvarchar(60), @constraintname_13 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'network_visibility'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_13, @constraintname_13
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_13+' drop constraint '+@constraintname_13)
	   FETCH NEXT from refcursor into @reftable_13, @constraintname_13
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [network_visibility]
END


CREATE TABLE [network_visibility]
(
	[id] INT  NOT NULL IDENTITY,
	[username] VARCHAR(50)  NOT NULL,
	[user_data_option] INT  NOT NULL,
	[networkname] VARCHAR(50)  NOT NULL,
	CONSTRAINT [network_visibility_PK] PRIMARY KEY ([id])
);

CREATE INDEX [PK_network_visibility] ON [network_visibility] ([id]);

CREATE INDEX [uq] ON [network_visibility] ([username],[user_data_option],[networkname]);

BEGIN
ALTER TABLE [network_visibility] ADD CONSTRAINT [network_visibility_FK_1] FOREIGN KEY ([username]) REFERENCES [registration] ([username])
END
;

/* ---------------------------------------------------------------------- */
/* Outbox											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='Outbox_FK_1')
	ALTER TABLE [Outbox] DROP CONSTRAINT [Outbox_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='Outbox_FK_2')
	ALTER TABLE [Outbox] DROP CONSTRAINT [Outbox_FK_2];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'Outbox')
BEGIN
	 DECLARE @reftable_14 nvarchar(60), @constraintname_14 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'Outbox'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_14, @constraintname_14
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_14+' drop constraint '+@constraintname_14)
	   FETCH NEXT from refcursor into @reftable_14, @constraintname_14
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [Outbox]
END


CREATE TABLE [Outbox]
(
	[MessageID] INT  NOT NULL IDENTITY,
	[Date] DATETIME  NOT NULL,
	[FromUser] VARCHAR(50)  NOT NULL,
	[ToUser] VARCHAR(50)  NOT NULL,
	[Message] VARCHAR(200)  NOT NULL,
	CONSTRAINT [Outbox_PK] PRIMARY KEY ([MessageID])
);

CREATE INDEX [PK_Outbox] ON [Outbox] ([MessageID]);

BEGIN
ALTER TABLE [Outbox] ADD CONSTRAINT [Outbox_FK_1] FOREIGN KEY ([FromUser]) REFERENCES [registration] ([username])
END
;

BEGIN
ALTER TABLE [Outbox] ADD CONSTRAINT [Outbox_FK_2] FOREIGN KEY ([ToUser]) REFERENCES [registration] ([username])
END
;

/* ---------------------------------------------------------------------- */
/* Inbox											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='Inbox_FK_1')
	ALTER TABLE [Inbox] DROP CONSTRAINT [Inbox_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='Inbox_FK_2')
	ALTER TABLE [Inbox] DROP CONSTRAINT [Inbox_FK_2];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'Inbox')
BEGIN
	 DECLARE @reftable_15 nvarchar(60), @constraintname_15 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'Inbox'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_15, @constraintname_15
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_15+' drop constraint '+@constraintname_15)
	   FETCH NEXT from refcursor into @reftable_15, @constraintname_15
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [Inbox]
END


CREATE TABLE [Inbox]
(
	[MessageID] INT  NOT NULL IDENTITY,
	[Date] DATETIME  NOT NULL,
	[FromUser] VARCHAR(50)  NOT NULL,
	[ToUser] VARCHAR(50)  NOT NULL,
	[Message] VARCHAR(200)  NOT NULL,
	CONSTRAINT [Inbox_PK] PRIMARY KEY ([MessageID])
);

CREATE INDEX [PK_Inbox] ON [Inbox] ([MessageID]);

BEGIN
ALTER TABLE [Inbox] ADD CONSTRAINT [Inbox_FK_1] FOREIGN KEY ([FromUser]) REFERENCES [registration] ([username])
END
;

BEGIN
ALTER TABLE [Inbox] ADD CONSTRAINT [Inbox_FK_2] FOREIGN KEY ([ToUser]) REFERENCES [registration] ([username])
END
;

/* ---------------------------------------------------------------------- */
/* workflows											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'workflows')
BEGIN
	 DECLARE @reftable_16 nvarchar(60), @constraintname_16 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'workflows'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_16, @constraintname_16
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_16+' drop constraint '+@constraintname_16)
	   FETCH NEXT from refcursor into @reftable_16, @constraintname_16
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [workflows]
END


CREATE TABLE [workflows]
(
	[id] INT  NOT NULL IDENTITY,
	[parent] INT  NOT NULL,
	[tool] CHAR(200)  NOT NULL,
	CONSTRAINT [workflows_PK] PRIMARY KEY ([id])
);

CREATE INDEX [IX_workflowParent] ON [workflows] ([parent]);

CREATE INDEX [IX_workflowTool] ON [workflows] ([tool]);

CREATE INDEX [PK_workflows] ON [workflows] ([id]);

/* ---------------------------------------------------------------------- */
/* tools											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'tools')
BEGIN
	 DECLARE @reftable_17 nvarchar(60), @constraintname_17 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'tools'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_17, @constraintname_17
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_17+' drop constraint '+@constraintname_17)
	   FETCH NEXT from refcursor into @reftable_17, @constraintname_17
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [tools]
END


CREATE TABLE [tools]
(
	[id] INT  NOT NULL IDENTITY,
	[tool] VARCHAR(100)  NOT NULL,
	[description] TEXT  NULL,
	CONSTRAINT [tools_PK] PRIMARY KEY ([id])
);

CREATE INDEX [PK_tools] ON [tools] ([id]);

/* ---------------------------------------------------------------------- */
/* tool_comments											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'tool_comments')
BEGIN
	 DECLARE @reftable_18 nvarchar(60), @constraintname_18 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'tool_comments'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_18, @constraintname_18
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_18+' drop constraint '+@constraintname_18)
	   FETCH NEXT from refcursor into @reftable_18, @constraintname_18
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [tool_comments]
END


CREATE TABLE [tool_comments]
(
	[pk] INT  NOT NULL IDENTITY,
	[id] INT  NOT NULL,
	[comment] TEXT  NOT NULL,
	[username] CHAR(200)  NOT NULL,
	[posted_on] DATETIME  NOT NULL,
	CONSTRAINT [tool_comments_PK] PRIMARY KEY ([pk])
);

CREATE INDEX [PK_tool_comments] ON [tool_comments] ([pk]);

/* ---------------------------------------------------------------------- */
/* workflow_ratings											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'workflow_ratings')
BEGIN
	 DECLARE @reftable_19 nvarchar(60), @constraintname_19 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'workflow_ratings'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_19, @constraintname_19
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_19+' drop constraint '+@constraintname_19)
	   FETCH NEXT from refcursor into @reftable_19, @constraintname_19
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [workflow_ratings]
END


CREATE TABLE [workflow_ratings]
(
	[pk] INT  NOT NULL IDENTITY,
	[id] INT  NOT NULL,
	[username] VARCHAR(100)  NOT NULL,
	[rating] INT  NOT NULL,
	CONSTRAINT [workflow_ratings_PK] PRIMARY KEY ([pk])
);

CREATE INDEX [PK_workflow_ratings] ON [workflow_ratings] ([pk]);

/* ---------------------------------------------------------------------- */
/* workflow_comments											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'workflow_comments')
BEGIN
	 DECLARE @reftable_20 nvarchar(60), @constraintname_20 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'workflow_comments'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_20, @constraintname_20
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_20+' drop constraint '+@constraintname_20)
	   FETCH NEXT from refcursor into @reftable_20, @constraintname_20
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [workflow_comments]
END


CREATE TABLE [workflow_comments]
(
	[pk] INT  NOT NULL IDENTITY,
	[id] INT  NOT NULL,
	[comment] TEXT  NOT NULL,
	[username] CHAR(200)  NOT NULL,
	[posted_on] DATETIME  NOT NULL,
	CONSTRAINT [workflow_comments_PK] PRIMARY KEY ([pk])
);

CREATE INDEX [PK_workflow_comments] ON [workflow_comments] ([pk]);

/* ---------------------------------------------------------------------- */
/* tool_ratings											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'tool_ratings')
BEGIN
	 DECLARE @reftable_21 nvarchar(60), @constraintname_21 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'tool_ratings'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_21, @constraintname_21
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_21+' drop constraint '+@constraintname_21)
	   FETCH NEXT from refcursor into @reftable_21, @constraintname_21
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [tool_ratings]
END


CREATE TABLE [tool_ratings]
(
	[pk] INT  NOT NULL IDENTITY,
	[id] INT  NOT NULL,
	[rating] INT  NOT NULL,
	[username] CHAR(200)  NOT NULL,
	CONSTRAINT [tool_ratings_PK] PRIMARY KEY ([pk])
);

CREATE INDEX [PK_tool_ratings] ON [tool_ratings] ([pk]);

/* ---------------------------------------------------------------------- */
/* users											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'users')
BEGIN
	 DECLARE @reftable_22 nvarchar(60), @constraintname_22 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'users'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_22, @constraintname_22
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_22+' drop constraint '+@constraintname_22)
	   FETCH NEXT from refcursor into @reftable_22, @constraintname_22
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [users]
END


CREATE TABLE [users]
(
	[id] INT  NOT NULL,
	[username] VARCHAR(50)  NULL,
	[password] VARCHAR(50)  NULL,
	[email] VARCHAR(50)  NULL,
	[organization] VARCHAR(50)  NULL,
	CONSTRAINT [users_PK] PRIMARY KEY ([id])
);

CREATE INDEX [PK_users] ON [users] ([id]);
