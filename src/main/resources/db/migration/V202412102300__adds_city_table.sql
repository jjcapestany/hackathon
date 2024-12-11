CREATE TABLE city
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    city_name  VARCHAR(255),
    population INTEGER,
    x_axis     INTEGER,
    y_axis     INTEGER,
    fuel       INTEGER,
    water_id   BIGINT,
    aid_id     BIGINT,
    food_id    BIGINT,
    CONSTRAINT pk_city PRIMARY KEY (id)
);