
/* ---------------------------------------------------------------------- */
/* registration											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'registration')
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
		 and tables.name = 'registration'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_1, @constraintname_1
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_1+' drop constraint '+@constraintname_1)
	   FETCH NEXT from refcursor into @reftable_1, @constraintname_1
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [registration]
END


CREATE TABLE [registration]
(
	[ID] INT  NOT NULL IDENTITY,
	[PHONE] VARCHAR(50)  NULL,
	[INTERESTS] TEXT  NULL,
	[STATE] VARCHAR(50)  NULL,
	[online_status] INT  NULL,
	[PASSWORD] VARCHAR(50)  NOT NULL,
	[CITY] VARCHAR(50)  NULL,
	[USERNAME] VARCHAR(50)  NOT NULL,
	[CREATEDAT] DATETIME  NULL,
	[first_name] VARCHAR(50)  NOT NULL,
	[DATAVISIBILITY] INT  NULL,
	[work_title] VARCHAR(50)  NULL,
	[last_name] VARCHAR(50)  NOT NULL,
	[ZIPCODE] VARCHAR(5)  NULL,
	[lab_affiliation] VARCHAR(100)  NULL,
	[ADDR1] VARCHAR(50)  NULL,
	[ADDR2] VARCHAR(50)  NULL,
	[EMAIL] VARCHAR(50)  NULL,
	[LOGDATA] INT  NULL,
	[ROOTFOLDER_ID] INT  NULL,
	CONSTRAINT [registration_PK] PRIMARY KEY ([ID])
);

/* ---------------------------------------------------------------------- */
/* ANALYSISEVENT											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'ANALYSISEVENT')
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
		 and tables.name = 'ANALYSISEVENT'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_2, @constraintname_2
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_2+' drop constraint '+@constraintname_2)
	   FETCH NEXT from refcursor into @reftable_2, @constraintname_2
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [ANALYSISEVENT]
END


CREATE TABLE [ANALYSISEVENT]
(
	[ID] INT  NOT NULL IDENTITY,
	[CREATEDAT] DATETIME  NULL,
	[TRANSACTION_ID] INT  NULL,
	[TOOL_ID] INT  NULL,
	CONSTRAINT [ANALYSISEVENT_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_ANALYSISEVENT_TOOL_ID] ON [ANALYSISEVENT] ([TOOL_ID]);

CREATE INDEX [FK_ANALYSISEVENT_TRANSACTION_ID] ON [ANALYSISEVENT] ([TRANSACTION_ID]);

/* ---------------------------------------------------------------------- */
/* ANALYSISEVENTPARAMETER											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'ANALYSISEVENTPARAMETER')
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
		 and tables.name = 'ANALYSISEVENTPARAMETER'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_3, @constraintname_3
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_3+' drop constraint '+@constraintname_3)
	   FETCH NEXT from refcursor into @reftable_3, @constraintname_3
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [ANALYSISEVENTPARAMETER]
END


CREATE TABLE [ANALYSISEVENTPARAMETER]
(
	[ID] INT  NOT NULL IDENTITY,
	[PARAMETERVALUE] VARCHAR(255)  NULL,
	[PARAMETERKEY] VARCHAR(255)  NULL,
	[EVENT_ID] INT  NULL,
	CONSTRAINT [ANALYSISEVENTPARAMETER_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_ANALYSISEVENTPARAMETER_EVENT_ID] ON [ANALYSISEVENTPARAMETER] ([EVENT_ID]);

/* ---------------------------------------------------------------------- */
/* Friend											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'Friend')
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
		 and tables.name = 'Friend'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_4, @constraintname_4
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_4+' drop constraint '+@constraintname_4)
	   FETCH NEXT from refcursor into @reftable_4, @constraintname_4
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [Friend]
END


CREATE TABLE [Friend]
(
	[ID] INT  NOT NULL IDENTITY,
	[MUTUAL] TINYINT(1) default 0 NULL,
	[VISIBLE] TINYINT(1) default 0 NULL,
	[id_1] INT  NULL,
	[id_2] INT  NULL,
	CONSTRAINT [Friend_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_Friend_id_2] ON [Friend] ([id_2]);

CREATE INDEX [FK_Friend_id_1] ON [Friend] ([id_1]);

/* ---------------------------------------------------------------------- */
/* INCOMINGWORKFLOW											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'INCOMINGWORKFLOW')
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
		 and tables.name = 'INCOMINGWORKFLOW'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_5, @constraintname_5
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_5+' drop constraint '+@constraintname_5)
	   FETCH NEXT from refcursor into @reftable_5, @constraintname_5
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [INCOMINGWORKFLOW]
END


CREATE TABLE [INCOMINGWORKFLOW]
(
	[ID] INT  NOT NULL IDENTITY,
	[CREATEDAT] DATETIME  NULL,
	[NAME] VARCHAR(255)  NULL,
	[SENDER_ID] INT  NULL,
	[WORKFLOW_ID] INT  NULL,
	[RECEIVER_ID] INT  NULL,
	CONSTRAINT [INCOMINGWORKFLOW_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_INCOMINGWORKFLOW_RECEIVER_ID] ON [INCOMINGWORKFLOW] ([RECEIVER_ID]);

CREATE INDEX [FK_INCOMINGWORKFLOW_SENDER_ID] ON [INCOMINGWORKFLOW] ([SENDER_ID]);

CREATE INDEX [FK_INCOMINGWORKFLOW_WORKFLOW_ID] ON [INCOMINGWORKFLOW] ([WORKFLOW_ID]);

/* ---------------------------------------------------------------------- */
/* NETWORK											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'NETWORK')
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
		 and tables.name = 'NETWORK'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_6, @constraintname_6
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_6+' drop constraint '+@constraintname_6)
	   FETCH NEXT from refcursor into @reftable_6, @constraintname_6
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [NETWORK]
END


CREATE TABLE [NETWORK]
(
	[ID] INT  NOT NULL IDENTITY,
	[NAME] VARCHAR(255)  NULL,
	[owner] INT  NULL,
	CONSTRAINT [NETWORK_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_NETWORK_owner] ON [NETWORK] ([owner]);

/* ---------------------------------------------------------------------- */
/* TOOL											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'TOOL')
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
		 and tables.name = 'TOOL'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_7, @constraintname_7
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_7+' drop constraint '+@constraintname_7)
	   FETCH NEXT from refcursor into @reftable_7, @constraintname_7
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [TOOL]
END


CREATE TABLE [TOOL]
(
	[ID] INT  NOT NULL IDENTITY,
	[MOSTCOMMONPARAMETERS] VARCHAR(255)  NULL,
	[USAGECOUNT] INT  NULL,
	[DESCRIPTION] VARCHAR(255)  NULL,
	[NAME] VARCHAR(255)  NULL,
	[MOSTCOMMONPARAMETERSCOUNT] INT  NULL,
	[WFCOUNTHEAD] INT  NULL,
	[NUMRATING] INT  NULL,
	[SUMRATING] INT  NULL,
	CONSTRAINT [TOOL_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [NAME] ON [TOOL] ([NAME]);

/* ---------------------------------------------------------------------- */
/* TOOLCOMMENT											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='TOOLCOMMENT_FK_1')
	ALTER TABLE [TOOLCOMMENT] DROP CONSTRAINT [TOOLCOMMENT_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'TOOLCOMMENT')
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
		 and tables.name = 'TOOLCOMMENT'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_8, @constraintname_8
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_8+' drop constraint '+@constraintname_8)
	   FETCH NEXT from refcursor into @reftable_8, @constraintname_8
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [TOOLCOMMENT]
END


CREATE TABLE [TOOLCOMMENT]
(
	[ID] INT  NOT NULL IDENTITY,
	[CREATEDAT] DATETIME  NULL,
	[COMMENT] VARCHAR(255)  NULL,
	[CREATOR_ID] INT  NULL,
	[TOOL_ID] INT  NULL,
	CONSTRAINT [TOOLCOMMENT_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_TOOLCOMMENT_TOOL_ID] ON [TOOLCOMMENT] ([TOOL_ID]);

CREATE INDEX [FK_TOOLCOMMENT_CREATOR_ID] ON [TOOLCOMMENT] ([CREATOR_ID]);

BEGIN
ALTER TABLE [TOOLCOMMENT] ADD CONSTRAINT [TOOLCOMMENT_FK_1] FOREIGN KEY ([CREATOR_ID]) REFERENCES [registration] ([ID])
END
;

/* ---------------------------------------------------------------------- */
/* TOOLRATING											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='TOOLRATING_FK_1')
	ALTER TABLE [TOOLRATING] DROP CONSTRAINT [TOOLRATING_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'TOOLRATING')
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
		 and tables.name = 'TOOLRATING'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_9, @constraintname_9
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_9+' drop constraint '+@constraintname_9)
	   FETCH NEXT from refcursor into @reftable_9, @constraintname_9
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [TOOLRATING]
END


CREATE TABLE [TOOLRATING]
(
	[ID] INT  NOT NULL IDENTITY,
	[CREATEDAT] DATETIME  NULL,
	[RATING] INT  NULL,
	[TOOL_ID] INT  NULL,
	[CREATOR_ID] INT  NULL,
	CONSTRAINT [TOOLRATING_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_TOOLRATING_CREATOR_ID] ON [TOOLRATING] ([CREATOR_ID]);

CREATE INDEX [FK_TOOLRATING_TOOL_ID] ON [TOOLRATING] ([TOOL_ID]);

BEGIN
ALTER TABLE [TOOLRATING] ADD CONSTRAINT [TOOLRATING_FK_1] FOREIGN KEY ([CREATOR_ID]) REFERENCES [registration] ([ID])
END
;

/* ---------------------------------------------------------------------- */
/* TRANSACTION											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'TRANSACTION')
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
		 and tables.name = 'TRANSACTION'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_10, @constraintname_10
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_10+' drop constraint '+@constraintname_10)
	   FETCH NEXT from refcursor into @reftable_10, @constraintname_10
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [TRANSACTION]
END


CREATE TABLE [TRANSACTION]
(
	[ID] INT  NOT NULL IDENTITY,
	[DATASETNAME] VARCHAR(255)  NULL,
	[CLIENTID] VARCHAR(255)  NULL,
	[HOSTNAME] VARCHAR(255)  NULL,
	[DATE] DATETIME  NULL,
	[USER_ID] INT  NULL,
	[WORKFLOW_ID] INT  NULL,
	CONSTRAINT [TRANSACTION_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_TRANSACTION_USER_ID] ON [TRANSACTION] ([USER_ID]);

CREATE INDEX [FK_TRANSACTION_WORKFLOW_ID] ON [TRANSACTION] ([WORKFLOW_ID]);

/* ---------------------------------------------------------------------- */
/* USERWORKFLOW											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'USERWORKFLOW')
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
		 and tables.name = 'USERWORKFLOW'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_11, @constraintname_11
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_11+' drop constraint '+@constraintname_11)
	   FETCH NEXT from refcursor into @reftable_11, @constraintname_11
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [USERWORKFLOW]
END


CREATE TABLE [USERWORKFLOW]
(
	[ID] INT  NOT NULL IDENTITY,
	[NAME] VARCHAR(255)  NULL,
	[WORKFLOW_ID] INT  NULL,
	[OWNER_ID] INT  NULL,
	[FOLDER_ID] INT  NULL,
	[CREATEDAT] DATETIME  NULL,
	CONSTRAINT [USERWORKFLOW_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_USERWORKFLOW_OWNER_ID] ON [USERWORKFLOW] ([OWNER_ID]);

CREATE INDEX [FK_USERWORKFLOW_FOLDER_ID] ON [USERWORKFLOW] ([FOLDER_ID]);

CREATE INDEX [FK_USERWORKFLOW_WORKFLOW_ID] ON [USERWORKFLOW] ([WORKFLOW_ID]);

/* ---------------------------------------------------------------------- */
/* User_Network											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'User_Network')
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
		 and tables.name = 'User_Network'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_12, @constraintname_12
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_12+' drop constraint '+@constraintname_12)
	   FETCH NEXT from refcursor into @reftable_12, @constraintname_12
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [User_Network]
END


CREATE TABLE [User_Network]
(
	[ID] INT  NOT NULL IDENTITY,
	[VISIBLE] TINYINT(1) default 0 NULL,
	[VERIFIED] TINYINT(1) default 0 NULL,
	[network_id] INT  NULL,
	[user_id] INT  NULL,
	CONSTRAINT [User_Network_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_User_Network_network_id] ON [User_Network] ([network_id]);

CREATE INDEX [FK_User_Network_user_id] ON [User_Network] ([user_id]);

/* ---------------------------------------------------------------------- */
/* WORKFLOW											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'WORKFLOW')
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
		 and tables.name = 'WORKFLOW'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_13, @constraintname_13
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_13+' drop constraint '+@constraintname_13)
	   FETCH NEXT from refcursor into @reftable_13, @constraintname_13
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [WORKFLOW]
END


CREATE TABLE [WORKFLOW]
(
	[ID] INT  NOT NULL IDENTITY,
	[USAGECOUNT] INT  NULL,
	[CREATEDAT] DATETIME  NULL,
	[CREATOR_ID] INT  NULL,
	[PARENT_ID] INT  NULL,
	[CREATIONTRANSACTION_ID] INT  NULL,
	[NUMRATING] INT  NULL,
	[SUMRATING] INT  NULL,
	[legacy_id] INT  NULL,
	CONSTRAINT [WORKFLOW_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_WORKFLOW_CREATIONTRANSACTION_ID] ON [WORKFLOW] ([CREATIONTRANSACTION_ID]);

CREATE INDEX [FK_WORKFLOW_PARENT_ID] ON [WORKFLOW] ([PARENT_ID]);

CREATE INDEX [FK_WORKFLOW_CREATOR_ID] ON [WORKFLOW] ([CREATOR_ID]);

/* ---------------------------------------------------------------------- */
/* WORKFLOWCOMMENT											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='WORKFLOWCOMMENT_FK_1')
	ALTER TABLE [WORKFLOWCOMMENT] DROP CONSTRAINT [WORKFLOWCOMMENT_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'WORKFLOWCOMMENT')
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
		 and tables.name = 'WORKFLOWCOMMENT'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_14, @constraintname_14
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_14+' drop constraint '+@constraintname_14)
	   FETCH NEXT from refcursor into @reftable_14, @constraintname_14
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [WORKFLOWCOMMENT]
END


CREATE TABLE [WORKFLOWCOMMENT]
(
	[ID] INT  NOT NULL IDENTITY,
	[CREATEDAT] DATETIME  NULL,
	[COMMENT] VARCHAR(255)  NULL,
	[WORKFLOW_ID] INT  NULL,
	[CREATOR_ID] INT  NULL,
	CONSTRAINT [WORKFLOWCOMMENT_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_WORKFLOWCOMMENT_CREATOR_ID] ON [WORKFLOWCOMMENT] ([CREATOR_ID]);

CREATE INDEX [FK_WORKFLOWCOMMENT_WORKFLOW_ID] ON [WORKFLOWCOMMENT] ([WORKFLOW_ID]);

BEGIN
ALTER TABLE [WORKFLOWCOMMENT] ADD CONSTRAINT [WORKFLOWCOMMENT_FK_1] FOREIGN KEY ([CREATOR_ID]) REFERENCES [registration] ([ID])
END
;

/* ---------------------------------------------------------------------- */
/* WORKFLOWFOLDER											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'WORKFLOWFOLDER')
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
		 and tables.name = 'WORKFLOWFOLDER'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_15, @constraintname_15
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_15+' drop constraint '+@constraintname_15)
	   FETCH NEXT from refcursor into @reftable_15, @constraintname_15
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [WORKFLOWFOLDER]
END


CREATE TABLE [WORKFLOWFOLDER]
(
	[ID] INT  NOT NULL IDENTITY,
	[NAME] VARCHAR(255)  NULL,
	[OWNER_ID] INT  NULL,
	[PARENT_ID] INT  NULL,
	CONSTRAINT [WORKFLOWFOLDER_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_WORKFLOWFOLDER_PARENT_ID] ON [WORKFLOWFOLDER] ([PARENT_ID]);

CREATE INDEX [FK_WORKFLOWFOLDER_OWNER_ID] ON [WORKFLOWFOLDER] ([OWNER_ID]);

/* ---------------------------------------------------------------------- */
/* WORKFLOWRATING											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='WORKFLOWRATING_FK_1')
	ALTER TABLE [WORKFLOWRATING] DROP CONSTRAINT [WORKFLOWRATING_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'WORKFLOWRATING')
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
		 and tables.name = 'WORKFLOWRATING'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_16, @constraintname_16
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_16+' drop constraint '+@constraintname_16)
	   FETCH NEXT from refcursor into @reftable_16, @constraintname_16
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [WORKFLOWRATING]
END


CREATE TABLE [WORKFLOWRATING]
(
	[ID] INT  NOT NULL IDENTITY,
	[CREATEDAT] DATETIME  NULL,
	[RATING] INT  NULL,
	[WORKFLOW_ID] INT  NULL,
	[CREATOR_ID] INT  NULL,
	CONSTRAINT [WORKFLOWRATING_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_WORKFLOWRATING_WORKFLOW_ID] ON [WORKFLOWRATING] ([WORKFLOW_ID]);

CREATE INDEX [FK_WORKFLOWRATING_CREATOR_ID] ON [WORKFLOWRATING] ([CREATOR_ID]);

BEGIN
ALTER TABLE [WORKFLOWRATING] ADD CONSTRAINT [WORKFLOWRATING_FK_1] FOREIGN KEY ([CREATOR_ID]) REFERENCES [registration] ([ID])
END
;

/* ---------------------------------------------------------------------- */
/* WORKFLOWTOOL											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'WORKFLOWTOOL')
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
		 and tables.name = 'WORKFLOWTOOL'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_17, @constraintname_17
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_17+' drop constraint '+@constraintname_17)
	   FETCH NEXT from refcursor into @reftable_17, @constraintname_17
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [WORKFLOWTOOL]
END


CREATE TABLE [WORKFLOWTOOL]
(
	[ID] INT  NOT NULL IDENTITY,
	[CARDINALITY] INT  NULL,
	[WORKFLOW_ID] INT  NULL,
	[TOOL_ID] INT  NULL,
	CONSTRAINT [WORKFLOWTOOL_PK] PRIMARY KEY ([ID])
);

CREATE INDEX [FK_WORKFLOWTOOL_TOOL_ID] ON [WORKFLOWTOOL] ([TOOL_ID]);

CREATE INDEX [FK_WORKFLOWTOOL_WORKFLOW_ID] ON [WORKFLOWTOOL] ([WORKFLOW_ID]);

/* ---------------------------------------------------------------------- */
/* access											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'access')
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
		 and tables.name = 'access'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_18, @constraintname_18
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_18+' drop constraint '+@constraintname_18)
	   FETCH NEXT from refcursor into @reftable_18, @constraintname_18
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [access]
END


CREATE TABLE [access]
(
	[id] INT  NOT NULL IDENTITY,
	[name] VARCHAR(50)  NULL,
	CONSTRAINT [access_PK] PRIMARY KEY ([id])
);

/* ---------------------------------------------------------------------- */
/* annotation											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='annotation_FK_1')
	ALTER TABLE [annotation] DROP CONSTRAINT [annotation_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='annotation_FK_2')
	ALTER TABLE [annotation] DROP CONSTRAINT [annotation_FK_2];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'annotation')
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
		 and tables.name = 'annotation'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_19, @constraintname_19
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_19+' drop constraint '+@constraintname_19)
	   FETCH NEXT from refcursor into @reftable_19, @constraintname_19
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [annotation]
END


CREATE TABLE [annotation]
(
	[id] INT  NOT NULL IDENTITY,
	[wspid] INT  NULL,
	[annotation] VARCHAR(1000)  NULL,
	[creator] INT  NULL,
	[createdAt] DATETIME default CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT [annotation_PK] PRIMARY KEY ([id])
);

CREATE INDEX [wspid] ON [annotation] ([wspid]);

CREATE INDEX [creator] ON [annotation] ([creator]);

BEGIN
ALTER TABLE [annotation] ADD CONSTRAINT [annotation_FK_1] FOREIGN KEY ([wspid]) REFERENCES [workspace] ([id]) ON UPDATE RESTRICT ON DELETE CASCADE
END
;

BEGIN
ALTER TABLE [annotation] ADD CONSTRAINT [annotation_FK_2] FOREIGN KEY ([creator]) REFERENCES [registration] ([ID]) ON UPDATE RESTRICT ON DELETE CASCADE
END
;

/* ---------------------------------------------------------------------- */
/* history											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='history_FK_1')
	ALTER TABLE [history] DROP CONSTRAINT [history_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='history_FK_2')
	ALTER TABLE [history] DROP CONSTRAINT [history_FK_2];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'history')
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
		 and tables.name = 'history'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_20, @constraintname_20
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_20+' drop constraint '+@constraintname_20)
	   FETCH NEXT from refcursor into @reftable_20, @constraintname_20
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [history]
END


CREATE TABLE [history]
(
	[id] INT  NOT NULL IDENTITY,
	[wspid] INT  NULL,
	[uid] INT  NULL,
	[type] CHAR(1)  NULL,
	[accessedAt] DATETIME default CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT [history_PK] PRIMARY KEY ([id])
);

CREATE INDEX [wspid] ON [history] ([wspid]);

CREATE INDEX [uid] ON [history] ([uid]);

BEGIN
ALTER TABLE [history] ADD CONSTRAINT [history_FK_1] FOREIGN KEY ([wspid]) REFERENCES [workspace] ([id]) ON UPDATE RESTRICT ON DELETE CASCADE
END
;

BEGIN
ALTER TABLE [history] ADD CONSTRAINT [history_FK_2] FOREIGN KEY ([uid]) REFERENCES [registration] ([ID]) ON UPDATE RESTRICT ON DELETE CASCADE
END
;

/* ---------------------------------------------------------------------- */
/* setting											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'setting')
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
		 and tables.name = 'setting'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_21, @constraintname_21
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_21+' drop constraint '+@constraintname_21)
	   FETCH NEXT from refcursor into @reftable_21, @constraintname_21
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [setting]
END


CREATE TABLE [setting]
(
	[ID] INT  NOT NULL IDENTITY,
	[data_value] VARCHAR(255)  NULL,
	[data_key] VARCHAR(255)  NULL,
	CONSTRAINT [setting_PK] PRIMARY KEY ([ID])
);

/* ---------------------------------------------------------------------- */
/* short_news											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'short_news')
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
		 and tables.name = 'short_news'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_22, @constraintname_22
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_22+' drop constraint '+@constraintname_22)
	   FETCH NEXT from refcursor into @reftable_22, @constraintname_22
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [short_news]
END


CREATE TABLE [short_news]
(
	[snid] BIGINT(20)  NOT NULL IDENTITY,
	[publishing_time] DATETIME default CURRENT_TIMESTAMP NOT NULL,
	[news_type] VARCHAR(10)  NULL,
	[author] VARCHAR(128)  NOT NULL,
	[subject] VARCHAR(128)  NOT NULL,
	[body] VARCHAR(1024)  NOT NULL,
	CONSTRAINT [short_news_PK] PRIMARY KEY ([snid])
);

CREATE INDEX [publishing_time] ON [short_news] ([publishing_time]);

CREATE INDEX [author] ON [short_news] ([author]);

CREATE INDEX [news_type] ON [short_news] ([news_type]);

/* ---------------------------------------------------------------------- */
/* tig_nodes											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='tig_nodes_FK_1')
	ALTER TABLE [tig_nodes] DROP CONSTRAINT [tig_nodes_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'tig_nodes')
BEGIN
	 DECLARE @reftable_23 nvarchar(60), @constraintname_23 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'tig_nodes'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_23, @constraintname_23
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_23+' drop constraint '+@constraintname_23)
	   FETCH NEXT from refcursor into @reftable_23, @constraintname_23
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [tig_nodes]
END


CREATE TABLE [tig_nodes]
(
	[nid] BIGINT(20)  NOT NULL IDENTITY,
	[parent_nid] BIGINT(20)  NULL,
	[uid] BIGINT(20)  NOT NULL,
	[node] VARCHAR(255)  NOT NULL,
	CONSTRAINT [tig_nodes_PK] PRIMARY KEY ([nid]),
	UNIQUE ([parent_nid],[uid],[node])
);

CREATE INDEX [node] ON [tig_nodes] ([node]);

CREATE INDEX [uid] ON [tig_nodes] ([uid]);

CREATE INDEX [parent_nid] ON [tig_nodes] ([parent_nid]);

BEGIN
ALTER TABLE [tig_nodes] ADD CONSTRAINT [tig_nodes_FK_1] FOREIGN KEY ([uid]) REFERENCES [tig_users] ([uid]) ON UPDATE RESTRICT ON DELETE RESTRICT
END
;

/* ---------------------------------------------------------------------- */
/* tig_pairs											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='tig_pairs_FK_1')
	ALTER TABLE [tig_pairs] DROP CONSTRAINT [tig_pairs_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='tig_pairs_FK_2')
	ALTER TABLE [tig_pairs] DROP CONSTRAINT [tig_pairs_FK_2];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'tig_pairs')
BEGIN
	 DECLARE @reftable_24 nvarchar(60), @constraintname_24 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'tig_pairs'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_24, @constraintname_24
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_24+' drop constraint '+@constraintname_24)
	   FETCH NEXT from refcursor into @reftable_24, @constraintname_24
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [tig_pairs]
END


CREATE TABLE [tig_pairs]
(
	[nid] BIGINT(20)  NULL,
	[uid] BIGINT(20)  NOT NULL,
	[pkey] VARCHAR(255)  NOT NULL,
	[pval] TEXT  NULL,
	[id] INT  NOT NULL IDENTITY,
	CONSTRAINT [tig_pairs_PK] PRIMARY KEY ([id])
);

CREATE INDEX [pkey] ON [tig_pairs] ([pkey]);

CREATE INDEX [uid] ON [tig_pairs] ([uid]);

CREATE INDEX [nid] ON [tig_pairs] ([nid]);

BEGIN
ALTER TABLE [tig_pairs] ADD CONSTRAINT [tig_pairs_FK_1] FOREIGN KEY ([nid]) REFERENCES [tig_nodes] ([nid]) ON UPDATE RESTRICT ON DELETE RESTRICT
END
;

BEGIN
ALTER TABLE [tig_pairs] ADD CONSTRAINT [tig_pairs_FK_2] FOREIGN KEY ([uid]) REFERENCES [tig_users] ([uid]) ON UPDATE RESTRICT ON DELETE RESTRICT
END
;

/* ---------------------------------------------------------------------- */
/* tig_users											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'tig_users')
BEGIN
	 DECLARE @reftable_25 nvarchar(60), @constraintname_25 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'tig_users'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_25, @constraintname_25
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_25+' drop constraint '+@constraintname_25)
	   FETCH NEXT from refcursor into @reftable_25, @constraintname_25
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [tig_users]
END


CREATE TABLE [tig_users]
(
	[uid] BIGINT(20)  NOT NULL IDENTITY,
	[user_id] VARCHAR(2049)  NOT NULL,
	[sha1_user_id] CHAR(128)  NOT NULL,
	[user_pw] VARCHAR(255)  NULL,
	[acc_create_time] DATETIME default CURRENT_TIMESTAMP NOT NULL,
	[last_login] DATETIME default '0000-00-00 00:00:00' NOT NULL,
	[last_logout] DATETIME default '0000-00-00 00:00:00' NOT NULL,
	[online_status] INT default 0 NULL,
	[failed_logins] INT default 0 NULL,
	[account_status] INT default 1 NULL,
	CONSTRAINT [tig_users_PK] PRIMARY KEY ([uid]),
	UNIQUE ([sha1_user_id])
);

CREATE INDEX [user_pw] ON [tig_users] ([user_pw]);

CREATE INDEX [user_id] ON [tig_users] ([user_id]);

CREATE INDEX [last_login] ON [tig_users] ([last_login]);

CREATE INDEX [last_logout] ON [tig_users] ([last_logout]);

CREATE INDEX [account_status] ON [tig_users] ([account_status]);

CREATE INDEX [online_status] ON [tig_users] ([online_status]);

/* ---------------------------------------------------------------------- */
/* tools											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'tools')
BEGIN
	 DECLARE @reftable_26 nvarchar(60), @constraintname_26 nvarchar(60)
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
	 FETCH NEXT from refcursor into @reftable_26, @constraintname_26
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_26+' drop constraint '+@constraintname_26)
	   FETCH NEXT from refcursor into @reftable_26, @constraintname_26
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [tools]
END


CREATE TABLE [tools]
(
	[id] INT  NOT NULL IDENTITY,
	[tool] VARCHAR(255)  NULL,
	[description] VARCHAR(255)  NULL,
	CONSTRAINT [tools_PK] PRIMARY KEY ([id])
);

/* ---------------------------------------------------------------------- */
/* workspace											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='workspace_FK_1')
	ALTER TABLE [workspace] DROP CONSTRAINT [workspace_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'workspace')
BEGIN
	 DECLARE @reftable_27 nvarchar(60), @constraintname_27 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'workspace'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_27, @constraintname_27
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_27+' drop constraint '+@constraintname_27)
	   FETCH NEXT from refcursor into @reftable_27, @constraintname_27
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [workspace]
END


CREATE TABLE [workspace]
(
	[id] INT  NOT NULL IDENTITY,
	[title] VARCHAR(200)  NULL,
	[creator] INT  NULL,
	[description] VARCHAR(1000)  NULL,
	[location] VARCHAR(1000)  NULL,
	[createdAt] DATETIME default CURRENT_TIMESTAMP NOT NULL,
	[version] INT  NULL,
	[lastSync] DATETIME default '0000-00-00 00:00:00' NOT NULL,
	[locked] TINYINT(1)  NULL,
	[lastLockedUser] INT  NULL,
	CONSTRAINT [workspace_PK] PRIMARY KEY ([id])
);

CREATE INDEX [creator] ON [workspace] ([creator]);

BEGIN
ALTER TABLE [workspace] ADD CONSTRAINT [workspace_FK_1] FOREIGN KEY ([creator]) REFERENCES [registration] ([ID]) ON UPDATE RESTRICT ON DELETE CASCADE
END
;

/* ---------------------------------------------------------------------- */
/* workspace_root											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'workspace_root')
BEGIN
	 DECLARE @reftable_28 nvarchar(60), @constraintname_28 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'workspace_root'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_28, @constraintname_28
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_28+' drop constraint '+@constraintname_28)
	   FETCH NEXT from refcursor into @reftable_28, @constraintname_28
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [workspace_root]
END


CREATE TABLE [workspace_root]
(
	[id] INT  NOT NULL IDENTITY,
	[location] VARCHAR(1000)  NULL,
	CONSTRAINT [workspace_root_PK] PRIMARY KEY ([id])
);

/* ---------------------------------------------------------------------- */
/* workspace_user											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='workspace_user_FK_1')
	ALTER TABLE [workspace_user] DROP CONSTRAINT [workspace_user_FK_1];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='workspace_user_FK_2')
	ALTER TABLE [workspace_user] DROP CONSTRAINT [workspace_user_FK_2];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type ='RI' AND name='workspace_user_FK_3')
	ALTER TABLE [workspace_user] DROP CONSTRAINT [workspace_user_FK_3];

IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'workspace_user')
BEGIN
	 DECLARE @reftable_29 nvarchar(60), @constraintname_29 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'workspace_user'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_29, @constraintname_29
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_29+' drop constraint '+@constraintname_29)
	   FETCH NEXT from refcursor into @reftable_29, @constraintname_29
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [workspace_user]
END


CREATE TABLE [workspace_user]
(
	[id] INT  NOT NULL IDENTITY,
	[wspid] INT  NULL,
	[uid] INT  NULL,
	[gid] INT  NULL,
	CONSTRAINT [workspace_user_PK] PRIMARY KEY ([id]),
	UNIQUE ([wspid],[uid])
);

CREATE INDEX [uid] ON [workspace_user] ([uid]);

CREATE INDEX [gid] ON [workspace_user] ([gid]);

BEGIN
ALTER TABLE [workspace_user] ADD CONSTRAINT [workspace_user_FK_1] FOREIGN KEY ([wspid]) REFERENCES [workspace] ([id]) ON UPDATE RESTRICT ON DELETE CASCADE
END
;

BEGIN
ALTER TABLE [workspace_user] ADD CONSTRAINT [workspace_user_FK_2] FOREIGN KEY ([uid]) REFERENCES [registration] ([ID]) ON UPDATE RESTRICT ON DELETE CASCADE
END
;

BEGIN
ALTER TABLE [workspace_user] ADD CONSTRAINT [workspace_user_FK_3] FOREIGN KEY ([gid]) REFERENCES [access] ([id]) ON UPDATE RESTRICT ON DELETE CASCADE
END
;

/* ---------------------------------------------------------------------- */
/* xmpp_stanza											*/
/* ---------------------------------------------------------------------- */


IF EXISTS (SELECT 1 FROM sysobjects WHERE type = 'U' AND name = 'xmpp_stanza')
BEGIN
	 DECLARE @reftable_30 nvarchar(60), @constraintname_30 nvarchar(60)
	 DECLARE refcursor CURSOR FOR
	 select reftables.name tablename, cons.name constraintname
	  from sysobjects tables,
		   sysobjects reftables,
		   sysobjects cons,
		   sysreferences ref
	   where tables.id = ref.rkeyid
		 and cons.id = ref.constid
		 and reftables.id = ref.fkeyid
		 and tables.name = 'xmpp_stanza'
	 OPEN refcursor
	 FETCH NEXT from refcursor into @reftable_30, @constraintname_30
	 while @@FETCH_STATUS = 0
	 BEGIN
	   exec ('alter table '+@reftable_30+' drop constraint '+@constraintname_30)
	   FETCH NEXT from refcursor into @reftable_30, @constraintname_30
	 END
	 CLOSE refcursor
	 DEALLOCATE refcursor
	 DROP TABLE [xmpp_stanza]
END


CREATE TABLE [xmpp_stanza]
(
	[id] BIGINT(20)  NOT NULL IDENTITY,
	[stanza] TEXT  NOT NULL,
	CONSTRAINT [xmpp_stanza_PK] PRIMARY KEY ([id])
);
