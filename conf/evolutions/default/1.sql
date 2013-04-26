# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint not null,
  username                  varchar(255),
  email_address             varchar(255),
  password                  varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  date_of_birth             varchar(255),
  street_address            varchar(255),
  zip_code                  varchar(255),
  city                      varchar(255),
  swedish                   boolean,
  constraint pk_user primary key (id))
;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

