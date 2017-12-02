-- First Query Report


WITH tbl_avg_cust AS
(
		SELECT
			cust,
			prod,
			count(quant)         AS C1,
			sum(quant)           AS S1,
			round(avg(quant), 0) AS CUST_AVG
		FROM sales
		GROUP BY cust, prod

), tbl_other_product AS
(
		SELECT

			prod,
			count(quant) AS C2,
			sum(quant)   AS S2
		FROM sales
		GROUP BY  prod

), tbl_other_cust AS
(
	SELECT cust, count(quant) AS C3, sum(quant) AS S3
	FROM sales
	GROUP BY cust
)
SELECT t1.cust AS "CUSTOMER", t1.prod AS "PRODUCT", CUST_AVG AS "THE_AVG",(S2-S1)/(C2-C1) AS "OTHER_PROD_AVG", (S3-S1)/(C3-C1) AS "OTHER_CUST_AVG"
FROM tbl_avg_cust t1, tbl_other_product t2, tbl_other_cust t3
WHERE  t1.prod = t2.prod AND t1.cust = t3.cust ORDER BY t1.cust,t1.prod;