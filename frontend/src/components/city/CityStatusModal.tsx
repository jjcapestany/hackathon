import {City} from "./CityClient.ts";
import {Label, Rect, Text} from "react-konva";
import {stageHeight, stageWidth} from "../../types.ts";

type props = {
    city: City
}

const CityStatusModal = ({city}: props) => {
    return (
        <Label>
            <Rect
                x={stageWidth * city.xcoord - 50}
                y={stageHeight * city.ycoord - 140}
                width={100}
                height={100}
                fill="tan"
                shadowBlur={3}
            />
            <Text
                x={stageWidth * city.xcoord - 50}
                y={stageHeight * city.ycoord - 140}
                text={city.name}/>
        </Label>
    )
}

export default CityStatusModal