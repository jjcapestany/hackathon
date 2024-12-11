INSERT INTO asset_type (mode_of_travel, water_capacity, food_capacity, aid_capacity, fuel_usage_rate, speed)
VALUES (0, 0, 0, 0, 0.5, 200),
       (1, 5000, 0, 0, 0.2, 60),
       (2, 0, 0, 0, 0.3, 80);

INSERT INTO asset (location_id, asset_type_id, food_onhand, medical_onhand, status, travel_days_remaining, water_onhand)
VALUES (1, 1, 0, 0, 1, 0, 0),
       (2, 2, 0, 0, 1, 0, 0),
       (3, 3, 0, 0, 1, 0, 0),
       (4, 1, 0, 0, 1, 0, 0),
       (5, 2, 0, 0, 1, 0, 0),
       (6, 3, 0, 0, 1, 0, 0),
       (7, 1, 0, 0, 1, 0, 0),
       (8, 2, 0, 0, 1, 0, 0),
       (1, 3, 0, 0, 1, 0, 0),
       (4, 1, 0, 0, 1, 0, 0);


INSERT INTO water (id, on_hand, usage_rate, capacity)
VALUES (1, 100000, 10.5, 500000);
INSERT INTO water (id, on_hand, usage_rate, capacity)
VALUES (2, 20000, 5.2, 100000);
INSERT INTO water (id, on_hand, usage_rate, capacity)
VALUES (3, 150000, 8.0, 600000);
INSERT INTO water (id, on_hand, usage_rate, capacity)
VALUES (4, 250000, 12.0, 700000);
INSERT INTO water (id, on_hand, usage_rate, capacity)
VALUES (5, 50000, 6.5, 250000);
INSERT INTO water (id, on_hand, usage_rate, capacity)
VALUES (6, 0, 0, 0); -- TF Main has no resources
INSERT INTO water (id, on_hand, usage_rate, capacity)
VALUES (7, 30000, 4.8, 150000);
INSERT INTO water (id, on_hand, usage_rate, capacity)
VALUES (8, 50000, 7.5, 300000);
INSERT INTO medical (id, on_hand, usage_rate, capacity)
VALUES (1, 5000, 3.0, 20000);
INSERT INTO medical (id, on_hand, usage_rate, capacity)
VALUES (2, 2000, 2.2, 10000);
INSERT INTO medical (id, on_hand, usage_rate, capacity)
VALUES (3, 12000, 4.5, 30000);
INSERT INTO medical (id, on_hand, usage_rate, capacity)
VALUES (4, 25000, 5.0, 40000);
INSERT INTO medical (id, on_hand, usage_rate, capacity)
VALUES (5, 7000, 3.8, 15000);
INSERT INTO medical (id, on_hand, usage_rate, capacity)
VALUES (6, 0, 0, 0); -- TF Main has no resources
INSERT INTO medical (id, on_hand, usage_rate, capacity)
VALUES (7, 4000, 3.0, 12000);
INSERT INTO medical (id, on_hand, usage_rate, capacity)
VALUES (8, 6000, 3.5, 18000);
INSERT INTO food (id, on_hand, usage_rate, capacity)
VALUES (1, 20000, 15.0, 100000);
INSERT INTO food (id, on_hand, usage_rate, capacity)
VALUES (2, 10000, 10.0, 50000);
INSERT INTO food (id, on_hand, usage_rate, capacity)
VALUES (3, 30000, 18.0, 150000);
INSERT INTO food (id, on_hand, usage_rate, capacity)
VALUES (4, 40000, 20.0, 200000);
INSERT INTO food (id, on_hand, usage_rate, capacity)
VALUES (5, 15000, 12.0, 80000);
INSERT INTO food (id, on_hand, usage_rate, capacity)
VALUES (6, 0, 0, 0); -- TF Main has no resources
INSERT INTO food (id, on_hand, usage_rate, capacity)
VALUES (7, 8000, 8.5, 40000);
INSERT INTO food (id, on_hand, usage_rate, capacity)
VALUES (8, 10000, 9.0, 60000);

UPDATE city
SET water_id=1,
    food_id=1,
    medical_id=1
WHERE id = 1;

UPDATE city
SET water_id=2,
    food_id=2,
    medical_id=2
WHERE id = 2;

UPDATE city
SET water_id=3,
    food_id=3,
    medical_id=3
WHERE id = 3;

UPDATE city
SET water_id=4,
    food_id=4,
    medical_id=4
WHERE id = 4;

UPDATE city
SET water_id=5,
    food_id=5,
    medical_id=5
WHERE id = 5;

UPDATE city
SET water_id=6,
    food_id=6,
    medical_id=6
WHERE id = 6;

UPDATE city
SET water_id=7,
    food_id=7,
    medical_id=7
WHERE id = 7;

UPDATE city
SET water_id=8,
    food_id=8,
    medical_id=8
WHERE id = 8;