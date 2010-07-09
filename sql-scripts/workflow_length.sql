select num as workflow_length, count(*) as count
from (
SELECT transaction_id, count(*) as num
  FROM [Genspace].[dbo].[analysis_events]
where date > '01-08-2009'
group by transaction_id
) a
group by a.num