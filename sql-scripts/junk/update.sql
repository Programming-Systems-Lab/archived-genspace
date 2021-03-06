UPDATE [Genspace].[dbo].[workflows]
   SET [tool] = 'ARACNE Analysis'
 WHERE tool like 'ARACNE%'

UPDATE [Genspace].[dbo].[workflows]
   SET [tool] = 'MINDY Analysis'
 WHERE tool like 'MINDY%'

UPDATE [Genspace].[dbo].[workflows]
   SET [tool] = 'NetBoost Analysis'
 WHERE tool like 'Net%'

UPDATE [Genspace].[dbo].[workflows]
   SET [tool] = 'Hierarchical Clustering'
 WHERE tool like 'Hier%'

UPDATE [Genspace].[dbo].[workflows]
   SET [tool] = 'Evidence Integration Analysis'
 WHERE tool like 'Evidence%'

UPDATE [Genspace].[dbo].[workflows]
   SET [tool] = 't Test Analysis'
 WHERE tool like 'T Test%'

UPDATE [Genspace].[dbo].[workflows]
   SET [tool] = 'MatrixREDUCE Analysis'
 WHERE tool like 'MatrixREDUCE%'