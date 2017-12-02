-- Second Query Report


CREATE VIEW quater_table AS
(

		SELECT
			cust,
			prod,
			month,
			quant,
			CASE
			WHEN month IN (1, 2, 3)
				THEN 'Q1'
			WHEN month IN (4, 5, 6)
				THEN 'Q2'
			WHEN month IN (7, 8, 9)
				THEN 'Q3'
			WHEN month IN (10, 11, 12)
				THEN 'Q4'
			END AS Quarter
		FROM sales
);


CREATE VIEW AVG_OF_QUATER AS
(

SELECT cust,prod ,round(avg(t1.quant)) AS A1, t1.Quarter
FROM quater_table t1
GROUP BY t1.Quarter,cust,prod
ORDER BY cust,prod
);


CREATE VIEW q1 AS (SELECT * FROM
	(SELECT cust,prod FROM sales group by cust,prod order by cust,prod) a1,
		(SELECT 'Q1' AS QUATERR UNION SELECT 'Q2' UNION SELECT 'Q3' UNION SELECT 'Q4') AS A2);



-- FOR BEFORE_AFTER AVG



create view v3 as (select q1.cust, q1.prod, q1.QUATERR, AVG_OF_QUATER.A1 as before from q1 left outer join AVG_OF_QUATER
                   on q1.cust=AVG_OF_QUATER.cust and q1.prod = AVG_OF_QUATER.prod and
                      (case when q1.QUATERR = 'Q2' then AVG_OF_QUATER.quarter = 'Q1'
                   		    when q1.QUATERR = 'Q3' then AVG_OF_QUATER.quarter = 'Q2'
                   		    when q1.QUATERR = 'Q4' then AVG_OF_QUATER.quarter = 'Q3' end)
                    order by cust, prod, quarter);

create view v4 as (select q1.cust, q1.prod, q1.QUATERR, AVG_OF_QUATER.A1 as after from q1 left outer join AVG_OF_QUATER
                  on q1.cust=AVG_OF_QUATER.cust and q1.prod=AVG_OF_QUATER.prod and
                     (case when q1.QUATERR = 'Q1' then AVG_OF_QUATER.quarter = 'Q2'
                   		    when q1.QUATERR = 'Q2' then AVG_OF_QUATER.quarter = 'Q3'
                   		    when q1.QUATERR = 'Q3' then AVG_OF_QUATER.quarter = 'Q4' end)
                  order by cust, prod, quarter);


select v3.cust, v3.prod, v3.QUATERR, v3.before as before_avg, v4.after as after_avg
		from v3 natural join v4;