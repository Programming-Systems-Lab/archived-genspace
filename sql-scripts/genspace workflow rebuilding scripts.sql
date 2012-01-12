update workflow w 
inner join (select w.id,concat(count(*),";",group_concat(wt.tool_id order by cardinality)) h from workflow w
inner join workflowtool wt on wt.workflow_id=w.id
group by w.id) zz on zz.id=w.id
set toolhash=zz.h;


update workflow w
inner join (select min(id) as newid,toolhash from workflow group by toolhash
having count(*) > 1) zz on zz.toolhash=w.toolhash
set w.newid=zz.newid;


update workflow set newid=null where newid=id;


update transaction t inner join workflow w on w.id=t.`WORKFLOW_ID`
set t.WORKFLOW_ID=w.newid
where w.newid is not null;

delete from workflowtool where workflow_id in(select id from workflow where newid is not null);


delete from workflow where newid is not null;


update workflow w inner join 
(select w.id,count(*) as len from workflow w inner join workflowtool wt on wt.`WORKFLOW_ID`=w.id
group by w.id) zz on zz.id=w.id
set w.length=zz.len


update
workflow w
inner join workflow w2 on w2.toolhash=concat((w.length-1),";",substring(w.toolhash,locate(';',w.toolhash)+1, length(w.toolhash)-locate(';',w.toolhash)-locate(",",reverse(w.toolhash))))
set w.parent_id=w2.id