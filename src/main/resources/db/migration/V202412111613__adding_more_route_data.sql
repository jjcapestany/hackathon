-- Clear existing routes first
DELETE FROM routes;

-- Main Island Land Routes (following the coastline)
-- West to East along north coast
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (4, 5, 'Land', 20);  -- Mangrove to Lagoon
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (5, 8, 'Land', 25);  -- Lagoon to Moonlit
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (8, 7, 'Land', 30);  -- Moonlit to Storm

-- South connections
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (4, 6, 'Land', 25);  -- Mangrove to TF Main
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (6, 3, 'Land', 20);  -- TF Main to Sunset
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (7, 3, 'Land', 30);  -- Storm to Sunset
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (8, 3, 'Land', 35);  -- Moonlit to Sunset

-- Air Routes (Can be more direct)
-- From Coral Reef (North Island)
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (1, 4, 'Air', 30);  -- Coral to Mangrove
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (1, 5, 'Air', 35);  -- Coral to Lagoon
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (1, 8, 'Air', 40);  -- Coral to Moonlit
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (1, 7, 'Air', 45);  -- Coral to Storm

-- From Turtleback (South Island)
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (2, 4, 'Air', 35);  -- Turtleback to Mangrove
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (2, 6, 'Air', 30);  -- Turtleback to TF Main
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (2, 3, 'Air', 25);  -- Turtleback to Sunset

-- Sea Routes (Following coastlines)
-- Coastal routes around main island
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (4, 5, 'Sea', 30);  -- Mangrove to Lagoon
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (5, 8, 'Sea', 35);  -- Lagoon to Moonlit
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (8, 7, 'Sea', 40);  -- Moonlit to Storm
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (7, 3, 'Sea', 45);  -- Storm to Sunset
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (3, 6, 'Sea', 35);  -- Sunset to TF Main
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (6, 4, 'Sea', 40);  -- TF Main to Mangrove

-- Island connections by sea
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (1, 4, 'Sea', 50);  -- Coral to Mangrove
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (1, 5, 'Sea', 55);  -- Coral to Lagoon
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (2, 3, 'Sea', 45);  -- Turtleback to Sunset
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (2, 6, 'Sea', 50);  -- Turtleback to TF Main

-- Don't forget to add reverse routes for all connections
-- Example:
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost)
SELECT destination_map_id, source_map_id, transportation_type, cost
FROM routes;