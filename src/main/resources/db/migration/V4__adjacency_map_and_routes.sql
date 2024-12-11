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