create table items_users(
  id serial primary key,
  id_payment bigint,
  foreign key (id_payment) references payment (id),
  user_id bigint,
  foreign key (user_id) references users (id)
);