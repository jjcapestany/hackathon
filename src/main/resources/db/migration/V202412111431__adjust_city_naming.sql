ALTER TABLE city
    ADD name VARCHAR(255);

ALTER TABLE city
    ADD x_coord DECIMAL;

ALTER TABLE city
    ADD y_coord DECIMAL;

ALTER TABLE city
    DROP COLUMN city_name;

INSERT INTO public.city (id, name, population, x_coord, y_coord, fuel)
VALUES (1, 'Coral Reef Community',150000, 0.88, 0.155, 0);
INSERT INTO public.city (id, name, population, x_coord, y_coord, fuel)
VALUES (2, 'Turtleback Island', 30000, 0.125, 0.71, 0);
INSERT INTO public.city (id, name, population, x_coord, y_coord, fuel)
VALUES (3, 'Sunset Cove District', 225000, 0.595, 0.49, 0);
INSERT INTO public.city (id, name, population, x_coord, y_coord, fuel)
VALUES (4, 'Mangrove Coastline', 350000, 0.425, 0.374, 0);
INSERT INTO public.city (id, name, population, x_coord, y_coord, fuel)
VALUES (5, 'Lagoon Bay Village', 130000, 0.55, 0.285, 0);
INSERT INTO public.city (id, name, population, x_coord, y_coord, fuel)
VALUES (6, 'TF Main', 0, 0.485, 0.385, 0);
INSERT INTO public.city (id, name, population, x_coord, y_coord, fuel)
VALUES (7, 'Stormbreaker Cliffs', 50000, 0.655, 0.36, 0);
INSERT INTO public.city (id, name, population, x_coord, y_coord, fuel)
VALUES (8, 'Moonlit Cove', 80000, 0.58, 0.365, 0);