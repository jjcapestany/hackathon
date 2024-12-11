import {City} from "./CityClient.ts";
import {Label, Rect, Text} from "react-konva";
import {stageHeight, stageWidth} from "../../types.ts";

type props = {
    city: City
}

const CityStatusModal = ({city}: props) => {
    return (
        <Label draggable={true}>
            <Rect
                x={stageWidth * (city.xAxis - .020)}
                y={stageHeight * (city.yAxis - .020)}
                width={100}
                height={100}
                fill="red"
                shadowBlur={3}
            />
            <Text text={city.cityName}/>
        </Label>
    )
}

export default CityStatusModal