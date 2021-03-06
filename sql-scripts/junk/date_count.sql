SELECT CONVERT(CHAR(12), date, 106) AS date,
       Count(*) AS logs
FROM
       [Genspace].[dbo].[analysis_events]
GROUP BY
       DATEPART(yy, date),
       DATEPART(dy, date),
       CONVERT(CHAR(12), date, 106)
ORDER BY
       DATEPART(yy, date) desc,
       DATEPART(dy, date) desc