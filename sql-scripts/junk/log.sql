select username, host, count(*) as count
from [Genspace].[dbo].[analysis_events]
where date > '01-08-2009'
group by username, host
order by username, host
