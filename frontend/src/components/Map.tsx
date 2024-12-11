import {Stage, Layer, Image} from 'react-konva';
import {useEffect, useState} from "react";
import Cities from "./city/Cities.tsx";
import {City, getCity} from "./city/CityClient.ts";

const Map = () => {
    const stageWidth = window.innerWidth;
    const stageHeight = window.innerHeight;
    const [backgroundImage, setBackgroundImage] = useState<CanvasImageSource | undefined>(undefined);
    const [city, setCity] = useState<City[]>([{id: 0, cityName: "none", population: 0, xAxis: 0, yAxis: 0}])

    useEffect(() => {
        getCity().then(setCity);
        console.log(city)
        const img = new window.Image();
        img.src = "./island_map.png";
        img.onload = () => {
            setBackgroundImage(img);
        }
    }, []);

    return (
        <Stage width={stageWidth} height={stageHeight}>
            <Layer>
                <Image
                    image={backgroundImage}
                    width={stageWidth}
                    height={stageHeight}
                    listening={false} // Ensures the background doesn't interfere with other interactions
                />
            </Layer>
            <Cities stageWidth={stageWidth} stageHeight={stageHeight}/>
        </Stage>
    )
}

export default Map;
