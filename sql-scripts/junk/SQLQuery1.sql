SELECT [username]
      ,[host]
      ,[date]
      ,[analysis]
      ,[dataset]
      ,[transaction_id]
      ,[is_genspace_user]
  FROM [Genspace].[dbo].[analysis_events]
order by date desc