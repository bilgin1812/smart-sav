# Users schema
 
# --- !Ups

create table   if not exists user0(

email                         varchar(255) not null ,
password                      varchar(255),
fullname              	    varchar(255),
is_admin              			boolean,
CONSTRAINT email_pk PRIMARY KEY (email)
);

create table  if not exists sav(

id                        bigint not null,
user_email                     varchar(255),
date_recu                  date,
message                    varchar(255),
souhait                   varchar(255),
CONSTRAINT id_pk PRIMARY KEY (id),

);

create sequence user0_seq start with 2000;
create sequence sav_seq start with 2000;

alter table sav add constraint fk_sav_user0_1 foreign key (user_email) references user0 (email) on delete restrict on update restrict;
create index ix_sav_user0_1 on sav (user_email);

# --- !Downs

drop table if exists user0;
drop table if exists sav;

drop sequence if exists user0_seq;
drop sequence if exists sav_seq;





# --- !Ups

create table company (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_company primary key (id))
;

create table computer (
  id                        bigint not null,
  name                      varchar(255),
  introduced                timestamp,
  discontinued              timestamp,
  company_id                bigint,
  constraint pk_computer primary key (id))
;

create sequence company_seq start with 1000;

create sequence computer_seq start with 1000;

alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_computer_company_1 on computer (company_id);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists company;

drop table if exists computer;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists company_seq;

drop sequence if exists computer_seq;

