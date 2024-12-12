import {City} from "./CityClient.ts";
import {Group, Label, Rect, Text} from "react-konva";
import {stageHeight, stageWidth} from "../../types.ts";

type props = {
    city: City
}

const CityStatusModal = ({city}: props) => {
    return (
        // <Label>
        //     <Rect
        //         x={stageWidth * city.xcoord - 50}
        //         y={stageHeight * city.ycoord - 140}
        //         width={100}
        //         height={100}
        //         fill="tan"
        //         shadowBlur={3}
        //     />
        //     <Text
        //         x={stageWidth * city.xcoord - 50}
        //         y={stageHeight * city.ycoord - 140}
        //         text={city.name}/>
        // </Label>
        <Group x={100} y={50} draggable={true}>
            {/* Background Rectangle */}
            <Rect
                width={200}
                height={230}
                fill="#f5f5f5"
                cornerRadius={5}
            />
            {/* Title */}
            <Text
                text="City Name"
                fontSize={16}
                fontStyle="bold"
                fill="#333"
                x={10}
                y={10}
            />
            {/* Water Section */}
            <Text text="Water: 42.5%" fontSize={14} fill="#333" x={10} y={40} />
            <Text text="100/200 gals" fontSize={12} fill="#555" x={10} y={55} />
            <Text text="Usage: 10 gal/day" fontSize={12} fill="#555" x={10} y={70} />

            {/* Food Section */}
            <Text text="Food: 50%" fontSize={14} fill="#333" x={10} y={90} />
            <Text text="50/100 lbs" fontSize={12} fill="#555" x={10} y={105} />
            <Text text="Usage: 20 lbs/day" fontSize={12} fill="#555" x={10} y={120} />

            {/* Medical Section */}
            <Text text="Medical: 60%" fontSize={14} fill="#333" x={10} y={140} />
            <Text text="30/50 pallets" fontSize={12} fill="#555" x={10} y={155} />
            <Text text="Usage: 5 pallets/Day" fontSize={12} fill="#555" x={10} y={170} />

            {/* Close Button */}
            <Text
                text="X"
                fontSize={14}
                fill="black"
                x={180}
                y={10}
                onClick={() => {
                    console.log("closed")}}
                style={{ cursor: "pointer" }}
            />
            {/* Button at the Bottom */}
            <Group
                x={50} // Center the button horizontally
                y={190} // Position below the medical section
                onClick={() => console.log("Start Transfer")}
                style={{ cursor: "pointer" }}
            >
                <Rect
                    width={100}
                    height={30}
                    fill="#ffcc01"
                    cornerRadius={5}
                />
                <Text
                    text="Start Transfer"
                    fontSize={14}
                    fill="black"
                    align="center"
                    verticalAlign="middle"
                    x={10}
                    y={8} // Center text within the button
                />
            </Group>
        </Group>

    )
}

export default CityStatusModal