import {Circle, Group, Image, Layer, Text} from "react-konva";
import {City} from "./CityClient.ts";
import {MutableRefObject, useEffect, useState} from "react";
import city_black from "../../assets/city_black.png";
import Konva from "konva";
import {IMAGE_HEIGHT, IMAGE_WIDTH} from "../Map.tsx";
import CityStatusModal from "./CityStatusModal.tsx";

type CitiesProps = {
    cities: City[],
    imageRef: MutableRefObject<Konva.Image | null>
}

const Cities = ({cities, imageRef}: CitiesProps) => {
    const [isCityStatusOpen, setIsCityStatusOpen] = useState(false);
    const [cityStatusContent, setCityStatusContent] = useState<City>({} as City)
    const [cityImage, setCityImage] = useState<CanvasImageSource | undefined>(undefined);

    const handleCityStatusClick = (name: string) => {
        const selectedCity = cities.find(c => c.name === name);
        if (selectedCity) {
            setCityStatusContent(selectedCity);
            setIsCityStatusOpen(true);
        }
    };

    useEffect(() => {
        const loadImage = (src: string, setImage: (img: CanvasImageSource) => void) => {
            const img = new window.Image();
            img.src = src;
            img.onload = () => setImage(img);
        };

        loadImage(city_black, setCityImage);
    }, []);

    return (
        <>
            {isCityStatusOpen && cityStatusContent && (
                <Layer>
                    <CityStatusModal city={cityStatusContent}/>
                </Layer>
            )}
            <Layer>
                {cities.map((c, index) => (
                    <Group key={index} x={(imageRef.current?.x() ?? 0) + IMAGE_WIDTH * c.xcoord}
                           y={(imageRef.current?.y() ?? 0) + IMAGE_HEIGHT * c.ycoord} draggable
                           onClick={() => handleCityStatusClick(c.name)}>
                        <Text text={c.name}/>
                        <Circle x={50} y={40} radius={25} fill={"#ffff66"}/>
                        <Image x={35} y={25} width={30} height={30} image={cityImage}/>
                    </Group>
                    // <Circle
                    //     key={index}
                    //     onClick={() => handleCityStatusClick(c.name)}
                    //     x={stageWidth * c.xcoord}
                    //     y={stageHeight * c.ycoord}
                    //     radius={30}
                    //     fill="green"
                    // />
                ))}
            </Layer>
        </>
    )
}
export default Cities;