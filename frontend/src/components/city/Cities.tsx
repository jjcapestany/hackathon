import {Circle, Group, Image, Layer, Text} from "react-konva";
import {City} from "./CityClient.ts";
import CityStatusModal from "./CityStatusModal.tsx";
import base from "../../assets/base.png"
import {useEffect, useState} from "react";
import {stageHeight, stageWidth} from "../../types.ts";
import city_black from "../../assets/city_black.png";

type CitiesProps = {
    cities: City[]
}

const Cities = ({cities}: CitiesProps) => {
    const [isCityStatusOpen, setIsCityStatusOpen] = useState(false);
    const [cityStatusContent, setCityStatusContent] = useState<City>({} as City)
    const [cityImage, setCityImage] = useState<CanvasImageSource | undefined>(undefined);
    const [baseImage, setBaseImage] = useState<CanvasImageSource | undefined>(undefined);


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
        loadImage(base, setBaseImage)
    }, []);

    const getColor = (c: City) => {

        if ((c.water.onHand / c.water.capacity < .2)
            || (c.food.onHand / c.food.capacity < .2)
            || (c.medical.onHand / c.medical.capacity < .2)) {
            return "#ff6666"
        } else if (c.water.onHand / c.water.capacity < .75
            || c.food.onHand / c.food.capacity < .75
            || c.medical.onHand / c.medical.capacity < .75) return "#ffff66"
        return "#719D71"
    }


    return (
        <>
            <Layer>
                {cities.map((c, index) => (
                    <Group key={index} x={stageWidth * c.xcoord} y={stageHeight * c.ycoord} draggable
                           onClick={() => handleCityStatusClick(c.name)}>
                        <Text text={c.name}/>
                        <Circle x={50} y={40} radius={25}
                                fill={c.name == "TF Main" ? "grey" : getColor(c)}/>
                        {c.name == "TF Main" ? <Image x={35} y={25} width={30} height={30} image={baseImage}/> :
                            <Image  x={35} y={25} width={30} height={30} image={cityImage}/>}
                    </Group>
                ))}
            </Layer>
            {isCityStatusOpen && cityStatusContent && (
                <Layer>
                    <CityStatusModal setIsOpen={setIsCityStatusOpen} city={cityStatusContent}/>
                </Layer>
            )}
        </>
    )
}
export default Cities;