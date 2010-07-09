SELECT p.date, p.analysis, p.transaction_id, p.parameter_key, p.parameter_value, e.username, e.host, e.dataset
  FROM [Genspace].[dbo].[analysis_events_parameters] p
INNER JOIN [Genspace].[dbo].[analysis_events] e
ON p.date = e.date and p.analysis = e.analysis and p.transaction_id = e.transaction_id