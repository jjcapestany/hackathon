import {City} from "./CityClient.ts";
import {Label, Rect, Text} from "react-konva";

type props = {
    city: City
}

const CityStatusModal = ({city}: props) => {
    return (
        <Label draggable={true}>
            <Rect
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