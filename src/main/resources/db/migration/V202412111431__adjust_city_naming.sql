ALTER TABLE city
    ADD name VARCHAR(255);

ALTER TABLE city
    ADD x_coord INTEGER;

ALTER TABLE city
    ADD y_coord INTEGER;

ALTER TABLE city
    ALTER COLUMN x_coord SET NOT NULL;

ALTER TABLE city
    ALTER COLUMN y_coord SET NOT NULL;

ALTER TABLE city
    DROP COLUMN city_name;

ALTER TABLE city
    DROP COLUMN x_axis;

ALTER TABLE city
    DROP COLUMN y_axis;