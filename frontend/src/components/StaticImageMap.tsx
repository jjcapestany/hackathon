import {MapContainer, ImageOverlay} from 'react-leaflet';
import L from 'leaflet';

const StaticImageMap = (props:{height:string,width:string}) => {
    // Define bounds (corners of the image in LatLng)
    const controlBounds =
        new L.LatLngBounds(
            new L.LatLng(0, 0),
            new L.LatLng(1000,1800 ));

    const mapBounds =
        new L.LatLngBounds(
            new L.LatLng(0, 0),
            new L.LatLng(1400,2200 ));

    // URL to your static image
    const imageUrl = '/island_map.png';

    return (
        <MapContainer
            center={[51.505, -0.09]}
            scrollWheelZoom={false}
            maxBoundsViscosity={1.0}
            maxBounds={mapBounds}

            crs={L.CRS.Simple} // Use a simple CRS for static image
            bounds={controlBounds}
            style={{height: props.height, width: props.width}}
        >
            <ImageOverlay url={imageUrl} bounds={mapBounds}/>
        </MapContainer>
    );
};

export default StaticImageMap;
