
create table p_user(
  id number primary key,
  name varchar2(255),
  user_id varchar2(10),
  password varchar2(255),
  email varchar2(255),
  last_updated date
);

create table p_roles(
  user_id varchar2(10),
  role varchar2(20)
);

create table p_tasks(
  id number primary key,
  user_id varchar2(10),
  task varchar2(255),
  status varchar2(20),
  lastUpdated date
);

