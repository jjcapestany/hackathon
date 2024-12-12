ALTER TABLE city
    ADD name VARCHAR(255);

ALTER TABLE city
    ADD xcoord DECIMAL;

ALTER TABLE city
    ADD ycoord DECIMAL;

ALTER TABLE city
    DROP COLUMN x_axis;
ALTER TABLE city
    DROP COLUMN y_axis;


INSERT INTO public.city (id, name, population, xcoord, ycoord, fuel)
VALUES (1, 'Coral Reef Community',150000, 0.850, 0.125, 0);
INSERT INTO public.city (id, name, population, xcoord, ycoord, fuel)
VALUES (2, 'Turtleback Island', 30000, 0.095, 0.69, 0);
INSERT INTO public.city (id, name, population, xcoord, ycoord, fuel)
VALUES (3, 'Sunset Cove District', 225000, 0.565, 0.46, 0);
INSERT INTO public.city (id, name, population, xcoord, ycoord, fuel)
VALUES (4, 'Mangrove Coastline', 350000, 0.395, 0.344, 0);
INSERT INTO public.city (id, name, population, xcoord, ycoord, fuel)
VALUES (5, 'Lagoon Bay Village', 130000, 0.520, 0.255, 0);
INSERT INTO public.city (id, name, population, xcoord, ycoord, fuel)
VALUES (6, 'TF Main', 0, 0.455, 0.355, 0);
INSERT INTO public.city (id, name, population, xcoord, ycoord, fuel)
VALUES (7, 'Stormbreaker Cliffs', 50000, 0.625, 0.33, 0);
INSERT INTO public.city (id, name, population, xcoord, ycoord, fuel)
VALUES (8, 'Moonlit Cove', 80000, 0.550, 0.335, 0);