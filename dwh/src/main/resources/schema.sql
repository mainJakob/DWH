DROP TABLE IF EXISTS fact_sale;
DROP TABLE IF EXISTS dim_product;
DROP TABLE IF EXISTS dim_time;
DROP TABLE IF EXISTS dim_location;

CREATE TABLE dim_location
(
    id      BIGINT   AUTO_INCREMENT  ,
    country VARCHAR(255) NOT NULL,
    city    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    name    VARCHAR(255) NOT NULL,
    chain   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_dim_location PRIMARY KEY (id)
);
CREATE TABLE dim_time
(
    id    BIGINT AUTO_INCREMENT  ,
    date_day   INT    NOT NULL,
    date_month INT    NOT NULL,
    date_year  INT    NOT NULL,
    date_time  DATE   NOT NULL,
    CONSTRAINT pk_dim_time PRIMARY KEY (id)
);
CREATE TABLE dim_product
(
    id       BIGINT  AUTO_INCREMENT     ,
    name     VARCHAR(255) NOT NULL,
    price    FLOAT        NOT NULL,
    category VARCHAR(255) NOT NULL,
    CONSTRAINT pk_dim_product PRIMARY KEY (id)
);
CREATE TABLE fact_sale
(
    id          BIGINT AUTO_INCREMENT ,
    price       FLOAT  NOT NULL,
    final_price  FLOAT  NOT NULL,
    quantity    INT    NOT NULL,
    discount    FLOAT  NOT NULL,
    time_id     BIGINT NOT NULL,
    location_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    CONSTRAINT pk_fact_sale PRIMARY KEY (id)
);

ALTER TABLE fact_sale
    ADD CONSTRAINT FK_FACT_SALE_ON_LOCATION FOREIGN KEY (location_id) REFERENCES dim_location (id);

ALTER TABLE fact_sale
    ADD CONSTRAINT FK_FACT_SALE_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES dim_product (id);

ALTER TABLE fact_sale
    ADD CONSTRAINT FK_FACT_SALE_ON_TIME FOREIGN KEY (time_id) REFERENCES dim_time (id);


