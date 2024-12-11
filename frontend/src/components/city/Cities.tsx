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

    const handleCityStatusClick = (cityName: string) => {
        const selectedCity = cities.find(c => c.cityName === cityName);
        if (selectedCity) {
            setCityStatusContent(selectedCity);
            setIsCityStatusOpen(true);
        }
    };

    console.log(cities[0].yAxis)

    return (
        <>
            {isCityStatusOpen && cityStatusContent && (
                <Layer>
                    <CityStatusModal city={cityStatusContent}/>
                </Layer>
            )}
            <Layer>
                {cities.map((c) => (
                    <Circle
                        onClick={() => handleCityStatusClick(c.cityName)}
                        x={stageWidth * c.xAxis}
                        y={stageHeight * c.yAxis}
                        radius={30}
                        fill="green"
                    />
                ))}
            </Layer>
        </>
    )
}
export default Cities;