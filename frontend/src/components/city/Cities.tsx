import {Circle, Layer} from "react-konva";
import {City} from "./CityClient.ts";
import CityStatusModal from "./CityStatusModal.tsx";
import {useState} from "react";
import {stageHeight, stageWidth} from "../../types.ts";

type CitiesProps = {
    cities: City[]
}

const Cities = ({cities}: CitiesProps) => {
    const [isCityStatusOpen, setIsCityStatusOpen] = useState(false);
    const [cityStatusContent, setCityStatusContent] = useState<City>({} as City)

    const handleCityStatusClick = (name: string) => {
        const selectedCity = cities.find(c => c.name === name);
        if (selectedCity) {
            setCityStatusContent(selectedCity);
            setIsCityStatusOpen(true);
        }
    };

    console.log(cities[0].ycoord)

    return (
        <>
            {isCityStatusOpen && cityStatusContent && (
                <Layer>
                    <CityStatusModal city={cityStatusContent}/>
                </Layer>
            )}
            <Layer>
                {cities.map((c, index) => (
                    <Circle
                        key={index}
                        onClick={() => handleCityStatusClick(c.name)}
                        x={stageWidth * c.xcoord}
                        y={stageHeight * c.ycoord}
                        radius={30}
                        fill="green"
                    />
                ))}
            </Layer>
        </>
    )
}
export default Cities;