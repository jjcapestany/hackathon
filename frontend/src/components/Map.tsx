import {Stage, Layer, Image} from 'react-konva';
import {useEffect, useState} from "react";
import Cities from "./city/Cities.tsx";

const Map = () => {
    const [backgroundImage, setBackgroundImage] = useState<CanvasImageSource | undefined>(undefined);

    useEffect(() => {
        const img = new window.Image();
        img.src = "./island_map.png";
        img.onload = () => {
            setBackgroundImage(img);
        };
    }, []);

    return (
        <Stage width={window.innerWidth} height={window.innerHeight}>
            <Layer>
                <Image
                    image={backgroundImage}
                    width={window.innerWidth}
                    height={window.innerHeight}
                    listening={false} // Ensures the background doesn't interfere with other interactions
                />
            </Layer>
            <Cities/>
        </Stage>
    )
}

export default Map;