create table payment(
  id serial primary key,
  name text,
  user_paid bigint,
  foreign key (user_paid) references users (id),
  datatime timestamp
);