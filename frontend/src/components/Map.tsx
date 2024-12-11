import {ImageOverlay, MapContainer, Marker, Popup} from 'react-leaflet';
import L from 'leaflet';

const Map = () => {
    // Define bounds (corners of the image in LatLng)
    const controlBounds =
        new L.LatLngBounds(
            new L.LatLng(0, 0),
            new L.LatLng(1000, 1800)
        );

    const mapBounds =
        new L.LatLngBounds(
            new L.LatLng(0, 0),
            new L.LatLng(1400, 2200)
        );

    // URL to your static image
    const imageUrl = '/island_map.png';

    return (
        <MapContainer
            scrollWheelZoom={false}
            doubleClickZoom={false}
            maxBoundsViscosity={1.0}
            maxBounds={mapBounds}
            crs={L.CRS.Simple} // Use a simple CRS for static image
            bounds={controlBounds}
            style={{
                width: '100vw',
                height: '100vh',
                position: 'relative',
            }}
        >
            <ImageOverlay url={imageUrl} bounds={mapBounds}/>
            <Marker
                position={[1900, 280]}
                icon={L.divIcon({
                    html: `<div style="color: black; font-weight: bold; font-size: 70px;">Turtleback Island</div>`,
                    iconSize: [540, 150],
                })}
            >
                <Popup>
                    <div>
                        <h3>You have selected death</h3>
                        <p>No supplies remaining.</p>
                    </div>
                </Popup>
            </Marker>
        </MapContainer>
    );
};

export default Map;
