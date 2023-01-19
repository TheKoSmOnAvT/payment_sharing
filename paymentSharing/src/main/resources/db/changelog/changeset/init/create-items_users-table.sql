create table items_users(
  id bigserial primary key,
  id_item bigint,
  foreign key (id_item) references payment (id),
  user_id bigint,
  foreign key (user_id) references users (id)
);