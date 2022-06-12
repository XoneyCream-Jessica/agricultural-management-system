alter table product add column listener_id BIGINT(32) DEFAULT -1 COMMENT 'listenerId';



alter table growing_environment MODIFY collect_date datetime;
alter table growing_environment change pe_id product_id varchar(50);

alter table growing_environment add column temperature decimal(11,2);
alter table growing_environment add column humidity decimal(11,2);
alter table growing_environment add column pest_name varchar(200);

