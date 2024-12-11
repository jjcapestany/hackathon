import {Layer, Circle} from "react-konva";
import {City} from "./CityClient.ts";
import CityStatusModal from "./CityStatusModal.tsx";
import {useState} from "react";

type CitiesProps = {
    stageWidth: number,
    stageHeight: number,
    cities: City[]
}

const Cities = ({stageWidth, stageHeight, cities}: CitiesProps) => {
    const [isCityStatusOpen, setIsCityStatusOpen] = useState(false);
    const [cityStatusContent, setCityStatusContent] = useState<City>({} as City)

    const handleCityStatusClick = (cityName: string) => {
        const selectedCity = cities.find(c => c.cityName === cityName);
        if (selectedCity) {
            setCityStatusContent(selectedCity);
            setIsCityStatusOpen(true);
        }
    };

    return (
        <>
            {isCityStatusOpen && cityStatusContent && (
                <Layer>
                    <CityStatusModal city={cityStatusContent}/>
                </Layer>
            )}
            <Layer>
                {/*Turtleback Island*/}
                <Circle
                    onClick={() => handleCityStatusClick("Turtleback Island")}
                    x={stageWidth * 0.125}
                    y={stageHeight * 0.71}
                    radius={30}
                    fill="green"
                />
                {/*Mangrove Coastline*/}
                <Circle
                    x={stageWidth * 0.425}
                    y={stageHeight * 0.374}
                    radius={30}
                    fill="green"
                />
                {/*Lagoon Bay Village*/}
                <Circle
                    x={stageWidth * 0.55}
                    y={stageHeight * 0.285}
                    radius={30}
                    fill="green"
                />
                {/*Moonlit Cave*/}
                <Circle
                    x={stageWidth * 0.58}
                    y={stageHeight * 0.365}
                    radius={30}
                    fill="green"
                />
                {/*Storm Breaker Cliffs*/}
                <Circle
                    x={stageWidth * 0.655}
                    y={stageHeight * 0.36}
                    radius={30}
                    fill="green"
                />
                {/*Sunset Cove District*/}
                <Circle
                    x={stageWidth * 0.595}
                    y={stageHeight * 0.49}
                    radius={30}
                    fill="green"
                />
                {/*Coral Reef Community*/}
                <Circle
                    x={stageWidth * 0.88}
                    y={stageHeight * 0.155}
                    radius={30}
                    fill="green"
                />
                {/*TF Main*/}
                <Circle
                    x={stageWidth * 0.485}
                    y={stageHeight * 0.385}
                    radius={30}
                    fill="green"
                />
            </Layer>
        </>
    )
}
export default Cities;