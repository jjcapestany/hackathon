import {Image} from "react-konva";
import {useEffect, useState} from "react";
import boat from "../assets/boat.png"
import plane from "../assets/plane.png"
import truck from "../assets/truck.png"

const IconExamples = () => {

    const [boatImage, setBoatImage] = useState<CanvasImageSource | undefined>(undefined);
    const [planeImage, setPlaneImage] = useState<CanvasImageSource | undefined>(undefined);
    const [truckImage, setTruckImage] = useState<CanvasImageSource | undefined>(undefined);

    useEffect(() => {
        const boatImg = new window.Image();
        boatImg.src = boat;
        boatImg.onload = () => {
            setBoatImage(boatImg);
        };

        const planeImg = new window.Image();
        planeImg.src = plane;
        planeImg.onload = () => {
            setPlaneImage(planeImg);
        };

        const truckImg = new window.Image();
        truckImg.src = truck;
        truckImg.onload = () => {
            setTruckImage(truckImg);
        };
    }, []);

    return (
        <>
            <Image y={50} x={50} width={30} height={30} image={boatImage}/>
            <Image y={150} x={50} width={30} height={30} image={planeImage}/>
            <Image y={250} x={50} width={30} height={30} image={truckImage}/>
        </>
    )
}

export default IconExamples;