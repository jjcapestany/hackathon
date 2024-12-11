import {Stage, Layer, Image} from 'react-konva';
import {useEffect, useState} from "react";
import Cities from "./city/Cities.tsx";

const Map = () => {
    const stageWidth = window.innerWidth;
    const stageHeight = window.innerHeight;
    const [backgroundImage, setBackgroundImage] = useState<CanvasImageSource | undefined>(undefined);

    useEffect(() => {
        const img = new window.Image();
        img.src = "./island_map.png";
        img.onload = () => {
            setBackgroundImage(img);
        };
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