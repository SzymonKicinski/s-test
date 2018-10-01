DROP DATABASE "AutoCenter";
CREATE DATABASE "AutoCenter"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Polish_Poland.1250'
    LC_CTYPE = 'Polish_Poland.1250'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

create sequence app_role_seq;
create table app_role (
    id bigint not null primary key default nextval('app_role_seq'),
    role_name varchar(50),
    constraint unique_role_name unique (role_name)
);
insert into app_role (role_name) values ('admin');
insert into app_role (role_name) values ('user');

create sequence app_user_seq;
create table app_user(
    id bigint not null primary key default nextval('app_user_seq'),
    username varchar(50),
    password varchar(255),
    email varchar(50),
    active integer,
    constraint unique_username unique (username),
    constraint unique_email unique (email)
);

create sequence user_role_seq;
create table user_role(
    id bigint not null primary key default nextval('user_role_seq'),
    user_id bigint,
    role_id bigint,
    constraint fk_user_id foreign key (user_id) references app_user (id),
    constraint fk_role_id foreign key (role_id) references app_role (id)
);

create sequence driving_type_seq;
create table driving_type(
    id bigint not null primary key default nextval('driving_type_seq'),
    value bigint,
    driving_type varchar(10),
    constraint unique_driving_value unique (value),
    constraint unique_driving_type unique (driving_type)
);
insert into driving_type (value, driving_type) values (1, 'City');
insert into driving_type (value, driving_type) values (2, 'Highway');
insert into driving_type (value, driving_type) values (3, 'Mixed');

create sequence fuel_type_seq;
create table fuel_type(
    id bigint not null primary key default nextval('fuel_type_seq'),
    value bigint,
    fuel_type varchar(10),
    constraint unique_fuel_value unique (value),
    constraint unique_fuel_type unique (fuel_type)
);
insert into fuel_type (value, fuel_type) values (1, 'Petrol');
insert into fuel_type (value, fuel_type) values (2, 'Diesel');
insert into fuel_type (value, fuel_type) values (3, 'LPG');

create sequence exploitation_type_seq;
create table exploitation_type(
    id bigint not null primary key default nextval('exploitation_type_seq'),
    value bigint,
    exploitation_type varchar(15),
    constraint unique_exploitation_value unique (value),
    constraint unique_exploitation_type unique (exploitation_type)
);
insert into exploitation_type (value, exploitation_type) values (1, 'Maintenance');
insert into exploitation_type (value, exploitation_type) values (2, 'Repair');
insert into exploitation_type (value, exploitation_type) values (3, 'Tuning');

create sequence user_car_seq;
create table user_car(
    id bigint not null primary key default nextval('user_car_seq'),
    user_id bigint,
    car_api_id bigint,
    car_name varchar(100),
    constraint fk_user_id foreign key (user_id) references app_user (id)
);

create sequence user_car_detail_seq;
create table user_car_detail(
    id bigint not null primary key default nextval('user_car_detail_seq'),
    user_car_id bigint,
    vin varchar(17),
    licence_plate_number varchar(10),
    color varchar(30),
    image_url varchar(2083),
    constraint fk_user_car_id foreign key (user_car_id) references user_car (id),
    constraint unique_user_car_id unique (user_car_id),
    constraint unique_vin unique (vin),
    constraint unique_licence_plate_number unique (licence_plate_number)
);

create sequence fuel_economy_seq;
create table fuel_economy(
    id bigint not null primary key default nextval('fuel_economy_seq'),
    user_car_id bigint,
    date date,
    driving_type bigint,
    fuel_type bigint,
    distance_driven numeric(7, 2),
    fuel_amount_filled numeric(5, 2),
    price_per_litre numeric(4,2),
    consumption numeric(7, 2),
    fill_up_cost numeric(7, 2),
    constraint fk_user_car_id foreign key (user_car_id) references user_car (id),
    constraint fk_fuel_type foreign key (fuel_type) references fuel_type (value),
    constraint fk_driving_type foreign key (driving_type) references driving_type (value)
);

create sequence repair_seq;
create table repair(
    id bigint not null primary key default nextval('repair_seq'),
    user_car_id bigint,
    date date,
    mileage bigint,
    description text,
    exploitation_type bigint,
    cost numeric(8,2),
    constraint fk_user_car_id foreign key (user_car_id) references user_car (id),
    constraint fk_exploitation_type foreign key (exploitation_type) references exploitation_type (value)
);

create sequence user_detail_seq;
create table user_detail(
    id bigint not null primary key default nextval('user_detail_seq'),
    user_id bigint,
    firstname varchar(20),
    surname varchar(20),
    address varchar(60),
    city varchar(30),
    zip_code varchar(10),
    phone_number varchar(15),
    constraint fk_user_id foreign key (user_id) references app_user (id),
    constraint unique_user_id unique (user_id)
);