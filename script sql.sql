CREATE TABLE fidelity_card (
  code int,
  nb_points numeric(4) NOT NULL,

  CONSTRAINT fidelity_card_pk PRIMARY KEY (code)
);

CREATE TABLE actor (
  num int,
  name varchar(50) NOT NULL,
  first_name varchar(50),
  VAT varchar(10),
  mail varchar(50) NOT NULL,
  phone varchar(11) NOT NULL,
  adress varchar(255) NOT NULL,
  account_number varchar(16),
  ref_fidelity_card int,

  CONSTRAINT actor_pk PRIMARY KEY (num),
  CONSTRAINT fidelity_card_fk
          FOREIGN KEY (ref_fidelity_card)
          REFERENCES fidelity_card (code)
);

CREATE TABLE flow (
  id_flow int,
  name varchar(25) NOT NULL,
  direction varchar(6) NOT NULL,

  CONSTRAINT flow_pk PRIMARY KEY (id_flow),
  CONSTRAINT direction_values
          CHECK (direction = 'Entree' OR direction = 'Sortie'),
  CONSTRAINT name_values
          CHECK (name = 'Commande site' OR name = 'Commande fournisseur'
                  OR name = 'Achat' OR name = 'Préparation'  OR name = 'Vente'
                  OR name = 'Plat' OR name = 'Inventaire')
);

CREATE TABLE affair (
  id_affair int,
  date_affair date NOT NULL,
  discount numeric(6,2),
  workflow varchar(15) NOT NULL,
  ref_actor int,
  ref_flow int NOT NULL,

  CONSTRAINT affair_pk PRIMARY KEY (id_affair),
  CONSTRAINT actor_affair_fk
          FOREIGN KEY (ref_actor)
          REFERENCES actor (num),
  CONSTRAINT flow_fk
          FOREIGN KEY (ref_flow)
          REFERENCES flow (id_flow)
);

CREATE TABLE brand (
  id_brand int,
  name varchar(25) NOT NULL,
  CEO varchar(50) NOT NULL,
  creation_date date NOT NULL,
  description TEXT,

  CONSTRAINT brand_pk PRIMARY KEY (id_brand)
);

CREATE TABLE item (
  code varchar(13),
  ref_brand int NOT NULL,
  name varchar(255) NOT NULL,
  catalog_price numeric(6,2) NOT NULL,
  reduction_points numeric(2),
  packaging varchar(15) NOT NULL,
  VAT numeric (4,1) NOT NULL,
  stock_quantity numeric(4) NOT NULL,
  threshold_limit numeric(4) NOT NULL,
  automatic_order BIT NOT NULL,
  production_date date,
  sale_date date NOT NULL,

  CONSTRAINT item_pk PRIMARY KEY (code),
  CONSTRAINT brand_fk
          FOREIGN KEY (ref_brand)
          REFERENCES brand (id_brand)
);

CREATE TABLE promotion (
  code int,
  percent_rate numeric(3) NOT NULL,

  CONSTRAINT promotion_pk PRIMARY KEY (code)
);

CREATE TABLE promotion_date (
  start_date date,
  ref_promotion int,
  ref_item varchar(13),
  end_date date NOT NULL,

  CONSTRAINT promotion_date_pk PRIMARY KEY (start_date, ref_promotion, ref_item),
  CONSTRAINT item_promotion_date_fk
          FOREIGN KEY (ref_item)
          REFERENCES item (code),
  CONSTRAINT promotion_fk
          FOREIGN KEY (ref_promotion)
          REFERENCES promotion (code)
);

CREATE TABLE detail_affair (
  quantity numeric(3) NOT NULL,
  real_price numeric(7,2) NOT NULL,
  VAT numeric(4,1) NOT NULL,
  ref_affair int NOT NULL,
  ref_item varchar(13) NOT NULL,

  CONSTRAINT detail_affair_pk PRIMARY KEY (ref_affair, ref_item),
  CONSTRAINT affair_detail_affair_fk
          FOREIGN KEY (ref_affair)
          REFERENCES affair (id_affair),
  CONSTRAINT item_detail_affair_fk
          FOREIGN KEY (ref_item)
          REFERENCES item (code)
);

CREATE TABLE batch (
  code varchar(50),
  expiration_date date,
  ref_affair int NOT NULL,
  ref_item varchar(13) NOT NULL,

  CONSTRAINT batch_pk PRIMARY KEY (code),
  CONSTRAINT detail_affair_fk
          FOREIGN KEY (ref_affair, ref_item)
          REFERENCES detail_affair (ref_affair, ref_item)
);



INSERT INTO brand values
	( 101
	, 'The Coca-Cola Company'
	, 'James Quincey'
	, "1898-01-29"
	, 'Marque Coca Cola'
	);


INSERT INTO item VALUES 
	( 1
	, 101
	, 'Fanta'
	, 0.99
	, 20
	, 'Canette'
	, 0.20
	, 500
	, 100
	, 1
	, NULL
	, "2021-01-12"
	);

INSERT INTO item VALUES 
	( 2
	, 101
	, 'Coca'
	, 1.02
	, 20
	, 'Canette'
	, 0.20
	, 800
	, 300
	, 0
	, "2005-05-25"
	, "2021-01-12"
	);