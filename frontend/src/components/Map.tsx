import {Image, Layer, Stage} from 'react-konva';
import {useEffect, useState} from "react";
import Cities from "./city/Cities.tsx";
import {City, getCity} from "./city/CityClient.ts";
import IconExamples from "./IconExamples.tsx";

const Map = () => {
    const stageWidth = window.innerWidth;
    const stageHeight = window.innerHeight;
    const [backgroundImage, setBackgroundImage] = useState<CanvasImageSource | undefined>(undefined);
    const [city, setCity] = useState<City[]>([{} as City])
    const [isLoading, setIsLoading] = useState<boolean>(true)

    useEffect(() => {
        setIsLoading(true)
        getCity().then((response) => {
            setCity(response)
            setIsLoading(false)
        });

        const img = new window.Image();
        img.src = "./island_map.png";
        img.onload = () => {
            setBackgroundImage(img);
        }
    }, []);
    console.log(city)
    return (
        !isLoading ?

            < Stage
                width={stageWidth}
                height={stageHeight}>
                < Layer>
                    < Image
                        image={backgroundImage}
                        width={stageWidth}
                        height={stageHeight}
                        listening={false} // Ensures the background doesn't interfere with other interactions
                    />
                </Layer>
                <Cities cities={city}/>
                <Layer><IconExamples/></Layer>
            </Stage> :
            <></>
    )
}

export default Map;
