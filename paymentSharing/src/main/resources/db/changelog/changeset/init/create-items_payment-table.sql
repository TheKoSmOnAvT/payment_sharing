create table items_payment(
  id bigserial primary key,
  id_payment bigint,
  foreign key (id_payment) REFERENCES payment (id),
  name text,
  cost double precision
);