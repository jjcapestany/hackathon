create table IF NOT EXISTS city
(
    id integer,
    city_name varchar(50),
    population integer,
    x_axis integer,
    y_axis integer
    );
INSERT INTO public.city (id, city_name, population)
VALUES (1, 'Coral Reef Community',150000);
INSERT INTO public.city (id, city_name, population)
VALUES (2, 'Turtleback Island', 30000);
INSERT INTO public.city (id, city_name, population)
VALUES (3, 'Sunset Cove District', 225000);
INSERT INTO public.city (id, city_name, population)
VALUES (4, 'Mangrove Coastline', 350000);
INSERT INTO public.city (id, city_name, population)
VALUES (5, 'Lagoon Bay Village', 130000);
INSERT INTO public.city (id, city_name, population)
VALUES (6, 'TF Main', 0);
INSERT INTO public.city (id, city_name, population)
VALUES (7, 'Stormbreaker Cliffs', 50000);
INSERT INTO public.city (id, city_name, population)
VALUES (8, 'Moonlit Cove', 80000 );