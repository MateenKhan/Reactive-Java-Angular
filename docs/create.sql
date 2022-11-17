drop table `shipment` if exists;
drop table `orders_products` if exists;
drop table `product` if exists;
drop table `orders` if exists;
drop sequence `hibernate_sequence` if exists;

create sequence `hibernate_sequence` start with 1 increment by 1;
create table `orders` (order_id integer not null, order_status varchar(255), shipping_address varchar(255), shipping_date date, primary key (order_id));
create table `orders_products` (order_order_id integer not null, products_product_id integer not null);
create table `product` (product_id integer not null, product_name varchar(255), quantity integer, stock integer, primary key (product_id));
create table `shipment` (shipment_id integer not null, address varchar(255), order_status varchar(255), shipping_date date, primary key (shipment_id));
alter table `orders_products` add constraint UK_sryh7e15i8ax4im9x3gfygj1u unique (products_product_id);
alter table `orders_products` add constraint FK16ex68h7pvahxh1ft6updos1c foreign key (products_product_id) references `product`;
alter table `orders_products` add constraint FKg72n53f6uwco1i8rod765gkoi foreign key (order_order_id) references `orders`;

INSERT INTO PRODUCT VALUES(1, 'Telivision',10,10);
INSERT INTO PRODUCT VALUES(2, 'Bottle',10,10);
