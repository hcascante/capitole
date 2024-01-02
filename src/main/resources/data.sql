-- V1__initial_schema.sql

-- Crear la tabla de precios
CREATE TABLE IF NOT EXISTS PRICES (
                        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        BRAND_ID BIGINT,
                        START_DATE TIMESTAMP,
                        END_DATE TIMESTAMP,
                        PRICE_LIST BIGINT,
                        PRODUCT_ID BIGINT,
                        PRIORITY BIGINT,
                        PRICE DECIMAL(10, 2),
                        CURR VARCHAR(3)
);

-- Crear un índice para mejorar el rendimiento de las consultas
CREATE INDEX idx_prices_brand_product_dates
    ON PRICES (BRAND_ID, PRODUCT_ID, START_DATE, END_DATE);

-- Crear la tabla de marcas
CREATE TABLE IF NOT EXISTS BRANDS (
                        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        BRAND_NAME VARCHAR
);

-- Crear la tabla de productos
CREATE TABLE IF NOT EXISTS PRODUCTS (
                                      ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      PRODUCT_NAME VARCHAR
);


INSERT INTO BRANDS (BRAND_NAME) VALUES ('ZARA'), ('H&M');
INSERT INTO PRODUCTS (PRODUCT_NAME) VALUES ('REMERA'), ('BUZO');

-- Insertar datos de ejemplo
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES
    (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
    (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
    (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
    (1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');