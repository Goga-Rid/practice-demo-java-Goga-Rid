CREATE TABLE family_members (
    member_id SERIAL PRIMARY KEY,
    fio VARCHAR(100),
    birthday DATE
);

\COPY family_members (fio, birthday) FROM '/home/goga_rid/ГИА/демо задание/Ресурсы/Family_members_import.csv' DELIMITER ',' CSV HEADER;

CREATE TABLE product (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(50),
    category VARCHAR(50),
    price INT
);

\COPY product (product_name, category, price) FROM '/home/goga_rid/ГИА/демо задание/Ресурсы/Product_import.csv' DELIMITER ',' CSV HEADER;

CREATE TABLE family_members_job (
    memjob_id SERIAL PRIMARY KEY,
    member_id INT REFERENCES family_members(member_id),
    fio VARCHAR(100),
    curr_position VARCHAR(50),
    org_name VARCHAR(100),
    salary INT,
    start_date DATE
);

\COPY family_members_job (fio, curr_position, org_name, salary, start_date) FROM '/home/goga_rid/ГИА/демо задание/Ресурсы/Family_members_job_import.csv' DELIMITER ',' CSV HEADER;

CREATE TABLE expence_product (
    transaction_id SERIAL PRIMARY KEY,
    purchase_date DATE,
    member_id INT REFERENCES family_members(member_id),
    product_id INT REFERENCES product(product_id),
    fio VARCHAR(100),
    product_name VARCHAR(50),
    quantity INT
);

\COPY expence_product (purchase_date, fio, product_name, quantity) FROM '/home/goga_rid/ГИА/демо задание/Ресурсы/Expence_product_import.csv' DELIMITER ',' CSV HEADER;



