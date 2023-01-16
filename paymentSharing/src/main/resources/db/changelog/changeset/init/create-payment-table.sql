create table payment(
  id bigserial primary key,
  name text,
  user_paid bigint,
  foreign key (user_paid) references users (id),
  datetime timestamp
);