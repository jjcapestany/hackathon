CREATE TABLE IF NOT EXISTS adjacency_maps
(
    id             BIGSERIAL PRIMARY KEY,
    origin_city_id BIGINT NOT NULL,
    CONSTRAINT fk_origin_city
    FOREIGN KEY (origin_city_id)
    REFERENCES city (id)
);

-- Create routes table
CREATE TABLE IF NOT EXISTS routes
(
    id                  BIGSERIAL PRIMARY KEY,
    source_map_id       BIGINT      NOT NULL,
    destination_map_id  BIGINT      NOT NULL,
    transportation_type VARCHAR(50) NOT NULL,
    cost                INTEGER     NOT NULL,
    CONSTRAINT fk_source_map
        FOREIGN KEY (source_map_id)
            REFERENCES adjacency_maps (id),
    CONSTRAINT fk_destination_map
        FOREIGN KEY (destination_map_id)
            REFERENCES adjacency_maps (id),
    CONSTRAINT unique_route
        UNIQUE (source_map_id, destination_map_id, transportation_type)
);

CREATE INDEX idx_routes_source_map ON routes (source_map_id);
CREATE INDEX idx_routes_dest_map ON routes (destination_map_id);
CREATE INDEX idx_adjacency_origin_city ON adjacency_maps (origin_city_id);

-- First, create adjacency maps for all cities
INSERT INTO adjacency_maps (id, origin_city_id) VALUES (1, 1);  -- Coral Reef
INSERT INTO adjacency_maps (id, origin_city_id) VALUES (2, 2);  -- Turtleback
INSERT INTO adjacency_maps (id, origin_city_id) VALUES (3, 3);  -- Sunset Cove
INSERT INTO adjacency_maps (id, origin_city_id) VALUES (4, 4);  -- Mangrove
INSERT INTO adjacency_maps (id, origin_city_id) VALUES (5, 5);  -- Lagoon Bay
INSERT INTO adjacency_maps (id, origin_city_id) VALUES (6, 6);  -- TF Main
INSERT INTO adjacency_maps (id, origin_city_id) VALUES (7, 7);  -- Stormbreaker
INSERT INTO adjacency_maps (id, origin_city_id) VALUES (8, 8);  -- Moonlit Cove

-- Now create routes with appropriate transportation types
-- Mangrove Coastline connections (id: 4)
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (4, 5, 'Land', 20); -- to Lagoon Bay
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (4, 6, 'Land', 25); -- to TF Main
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (4, 5, 'Sea', 30);  -- to Lagoon Bay
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (4, 6, 'Sea', 35);  -- to TF Main
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (4, 5, 'Air', 15);  -- to Lagoon Bay
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (4, 6, 'Air', 20);  -- to TF Main

-- Lagoon Bay connections (id: 5)
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (5, 8, 'Land', 20); -- to Moonlit
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (5, 8, 'Sea', 30);  -- to Moonlit
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (5, 8, 'Air', 15);  -- to Moonlit

-- Moonlit Cove connections (id: 8)
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (8, 7, 'Land', 20); -- to Stormbreaker
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (8, 3, 'Land', 25); -- to Sunset
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (8, 7, 'Sea', 30);  -- to Stormbreaker
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (8, 3, 'Sea', 35);  -- to Sunset
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (8, 7, 'Air', 15);  -- to Stormbreaker
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (8, 3, 'Air', 20);  -- to Sunset

-- Stormbreaker connections (id: 7)
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (7, 3, 'Land', 20); -- to Sunset
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (7, 3, 'Sea', 30);  -- to Sunset
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (7, 3, 'Air', 15);  -- to Sunset

-- Sunset Cove connections (id: 3)
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (3, 6, 'Land', 20); -- to TF Main
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (3, 6, 'Sea', 30);  -- to TF Main
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (3, 6, 'Air', 15);  -- to TF Main

-- Island connections (only Sea and Air)
-- Coral Reef connections (id: 1)
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (1, 4, 'Sea', 50);  -- to Mangrove
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (1, 5, 'Sea', 60);  -- to Lagoon Bay
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (1, 4, 'Air', 35);  -- to Mangrove
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (1, 5, 'Air', 40);  -- to Lagoon Bay

-- Turtleback Island connections (id: 2)
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (2, 4, 'Sea', 55);  -- to Mangrove
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (2, 3, 'Sea', 65);  -- to Sunset
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (2, 4, 'Air', 40);  -- to Mangrove
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (2, 3, 'Air', 45);  -- to Sunset

-- Add reverse routes for bidirectional travel (with same costs)
-- Note: You'll need to duplicate all the above route INSERTs with source and destination swapped
-- Example for first few:
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (5, 4, 'Land', 20); -- Lagoon Bay to Mangrove
INSERT INTO routes (source_map_id, destination_map_id, transportation_type, cost) VALUES (6, 4, 'Land', 25); -- TF Main to Mangrove
-- ... and so on for all routes