import {Circle, Group, Image, Text} from "react-konva";
import {useEffect, useState} from "react";
import boat from "../assets/boat.png"
import plane from "../assets/plane.png"
import truck from "../assets/truck.png"
import city_black from "../assets/city_black.png"
import base from "../assets/base.png"

const IconExamples = () => {

    const [boatImage, setBoatImage] = useState<CanvasImageSource | undefined>(undefined);
    const [planeImage, setPlaneImage] = useState<CanvasImageSource | undefined>(undefined);
    const [truckImage, setTruckImage] = useState<CanvasImageSource | undefined>(undefined);
    const [baseImage, setBaseImage] = useState<CanvasImageSource | undefined>(undefined);
    const [cityImage, setCityImage] = useState<CanvasImageSource | undefined>(undefined);

    useEffect(() => {
        const loadImage = (src: string, setImage: (img: CanvasImageSource) => void) => {
            const img = new window.Image();
            img.src = src;
            img.onload = () => setImage(img);
        };

        loadImage(boat, setBoatImage);
        loadImage(plane, setPlaneImage);
        loadImage(truck, setTruckImage);
        loadImage(base, setBaseImage);
        loadImage(city_black, setCityImage);

    }, []);

    return (
        <>
            <Image y={50} x={50} width={30} height={30} image={boatImage}/>
            <Image y={150} x={50} width={30} height={30} image={planeImage}/>
            <Image y={250} x={50} width={30} height={30} image={truckImage}/>

            <Group x={15} y={325} draggable>
                <Text text={"TF Main"} />
                <Circle x={50} y={40} radius={25} fill={"white"} />
                <Image x={35} y={25} width={30} height={30} image={baseImage}/>
            </Group>

            <Group x={15} y={425} draggable>
                <Text text={"Mangrove Coastline"} />
                <Circle x={50} y={40} radius={25} fill={"#ffff66"} />
                <Image x={35} y={25} width={30} height={30} image={cityImage} />
            </Group>

            <Group x={15} y={525} draggable>
                <Text text={"Mangrove Coastline"} />
                <Circle x={50} y={40} radius={25} fill={"#ff6666"} />
                <Image x={35} y={25} width={30} height={30} image={cityImage} />
            </Group>

            <Group x={15} y={625} draggable>
                <Text text={"Mangrove Coastline"} />
                <Circle x={50} y={40} radius={25} fill={"#719D71"} />
                <Image x={35} y={25} width={30} height={30} image={cityImage} />
            </Group>
        </>
    )
}

export default IconExamples;