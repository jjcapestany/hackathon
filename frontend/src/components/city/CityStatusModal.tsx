import {City} from "./CityClient.ts";
import {Group, Label, Rect, Text} from "react-konva";
import {stageHeight, stageWidth} from "../../types.ts";
import {Dispatch, SetStateAction, useState} from "react";

type props = {
    city: City
    setIsOpen: Dispatch<SetStateAction<boolean>>

}

const CityStatusModal = ({city, setIsOpen}: props) => {

    return (
        // <Label>
        //     <Rect
        //
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
        <Group x={stageWidth * city.xcoord - 250} y={stageHeight * city.ycoord - 100} draggable={true}>
            {/* Background Rectangle */}
            <Rect
                width={200}
                height={230}
                fill="#f5f5f5"
                cornerRadius={5}
            />
            {/* Title */}

            <Text
                text={city.name}
                fontSize={16}
                fontStyle="bold"
                fill="#333"
                x={10}
                y={10}
            />
            {/* Water Section */}
            <Text text={`Water: ${(city.water.onHand / city.water.capacity * 100).toPrecision(4)}%`} fontSize={14} fill="#333" x={10} y={40}/>
            <Text text={`${city.water.onHand} / ${city.water.capacity} gallons`} fontSize={12} fill="#555" x={10} y={55}/>
            <Text text={`Usage: ${city.water.usageRate} gallons / day`} fontSize={12} fill="#555" x={10} y={70}/>

            {/* Food Section */}
            <Text text="Food: 50%" fontSize={14} fill="#333" x={10} y={90}/>
            <Text text="50/100 lbs" fontSize={12} fill="#555" x={10} y={105}/>
            <Text text="Usage: 20 lbs/day" fontSize={12} fill="#555" x={10} y={120}/>

            {/* Medical Section */}
            <Text text="Medical: 60%" fontSize={14} fill="#333" x={10} y={140}/>
            <Text text="30/50 pallets" fontSize={12} fill="#555" x={10} y={155}/>
            <Text text="Usage: 5 pallets/Day" fontSize={12} fill="#555" x={10} y={170}/>

            {/* Close Button */}
            <Text
                text="X"
                fontSize={14}
                fill="black"
                x={180}
                y={10}
                onClick={() => {
                    setIsOpen(false)
                }}
                style={{cursor: "pointer"}}
            />
            {/* Button at the Bottom */}
            <Group
                x={50} // Center the button horizontally
                y={190} // Position below the medical section
                onClick={() => console.log("Start Transfer")}
                style={{cursor: "pointer"}}
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