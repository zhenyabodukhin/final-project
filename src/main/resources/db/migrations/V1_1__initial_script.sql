create table m_users
(
	id bigserial not null
		constraint m_users_pk
			primary key,
	login varchar not null,
	password varchar not null,
	created timestamp with time zone default CURRENT_TIMESTAMP(0) not null,
	changed timestamp with time zone,
	is_deleted boolean default false
);

ALTER SEQUENCE public.m_users_id_seq
    INCREMENT 1
    START 31
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

alter table m_users owner to postgres;

create unique index m_users_login_uindex
	on m_users (login);

create table m_roles
(
	id bigserial not null
		constraint m_roles_pk
			primary key,
	user_id bigint not null
		constraint m_roles_m_users_id_fk
			references m_users
				on update cascade,
	role varchar default 'ROLE_USER'::character varying not null
);

ALTER SEQUENCE public.m_roles_id_seq
    INCREMENT 1
    START 15
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

alter table m_roles owner to postgres;

create table m_address
(
	id bigserial not null
		constraint m_adress_pk
			primary key,
	street varchar not null,
	n_house varchar not null,
	n_flat integer,
	is_pizza boolean default false,
	n_floor integer,
	n_porch integer
);

ALTER SEQUENCE public.m_address_id_seq
    INCREMENT 1
    START 2
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

alter table m_address owner to postgres;

create index m_adress_street_index
	on m_address (street);

create table m_orders
(
	id bigserial not null
		constraint m_orders_pk
			primary key,
	user_id bigint not null
		constraint m_orders_m_users_id_fk
			references m_users
				on update cascade,
	address_id bigint not null
		constraint m_orders_m_adress_id_fk
			references m_address
				on update cascade,
	time timestamp with time zone default CURRENT_TIMESTAMP(0) not null,
	phone_number varchar default 375 not null,
	is_done boolean default false
);

ALTER SEQUENCE public.m_orders_id_seq
    INCREMENT 1
    START 2
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

alter table m_orders owner to postgres;

create table m_price
(
	id bigserial not null
		constraint m_price_pk
			primary key,
	price double precision not null
);

ALTER SEQUENCE public.m_price_id_seq
    INCREMENT 1
    START 7
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

alter table m_price owner to postgres;

create table m_size
(
	id bigserial not null
		constraint m_size_pk
			primary key,
	size integer not null,
	price_id bigint default 1 not null
		constraint m_size_m_price_id_fk
			references m_price
				on update cascade on delete set default
);

ALTER SEQUENCE public.m_size_id_seq
    INCREMENT 1
    START 4
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

alter table m_size owner to postgres;

create table m_dough_type
(
	id bigserial not null
		constraint m_dough_type_pk
			primary key,
	type varchar not null,
	price_id bigint default 1 not null
		constraint m_dough_type_m_price_id_fk
			references m_price
				on update cascade on delete set default
);

ALTER SEQUENCE public.m_dough_type_id_seq
    INCREMENT 1
    START 4
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

alter table m_dough_type owner to postgres;

create table m_goods
(
	id bigserial not null
		constraint m_goods_pk
			primary key,
	name varchar not null,
	price_id bigint default 1 not null
		constraint m_goods_m_price_id_fk
			references m_price
				on update cascade on delete set default,
	weight double precision not null,
	size_id integer default 1 not null
		constraint m_goods_m_size_id_fk
			references m_size
				on update cascade on delete set default,
	dough_id integer default 1 not null
		constraint m_goods_m_dough_type_id_fk
			references m_dough_type
				on update cascade on delete set default,
	ingredients varchar(255)  default ''
);

ALTER SEQUENCE public.m_goods_id_seq
    INCREMENT 1
    START 2
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

alter table m_goods owner to postgres;

create table order_goods
(
	id bigserial not null
		constraint order_goods_pk
			primary key,
	order_id bigint not null
		constraint order_goods_m_orders_id_fk
			references m_orders
				on update cascade,
	good_id bigint not null
		constraint order_goods_m_goods_id_fk
			references m_goods
				on update cascade,
	count integer not null
);

ALTER SEQUENCE public.order_goods_id_seq
    INCREMENT 1
    START 2
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

alter table order_goods owner to postgres;

create index m_goods_id_index
	on m_goods (id);

create index m_goods_name_index
	on m_goods (name);

create unique index m_price_id_uindex
	on m_price (id);

create unique index m_price_price_uindex
	on m_price (price);

