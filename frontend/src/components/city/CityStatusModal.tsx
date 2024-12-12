import {City} from "./CityClient.ts";
import {Group, Rect, Text} from "react-konva";
import {stageHeight, stageWidth} from "../../types.ts";
import {Dispatch, SetStateAction} from "react";

type props = {
    city: City
    setIsOpen: Dispatch<SetStateAction<boolean>>

}

const CityStatusModal = ({city, setIsOpen}: props) => {

    return (
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

            <Text text={`Water: ${(city.water.onHand / city.water.capacity * 100).toPrecision(4)}%`}
                  fontSize={14} fill="#333" x={10} y={40}/>
            <Text text={`${city.water.onHand} / ${city.water.capacity} gallons`} fontSize={12} fill="#555"
                  x={10} y={55}/>
            <Text text={`Usage: ${city.water.usageRate} gallons / day`} fontSize={12} fill="#555" x={10}
                  y={70}/>

            <Text text={`Food: ${(city.food.onHand / city.food.capacity * 100).toPrecision(4)}%`} fontSize={14}
                  fill="#333" x={10} y={90}/>
            <Text text={`${city.food.onHand} / ${city.food.capacity} lbs`} fontSize={12} fill="#555" x={10}
                  y={105}/>
            <Text text={`Usage: ${city.food.usageRate} lbs / day`} fontSize={12} fill="#555" x={10} y={120}/>

            <Text text={`Medical: ${(city.medical.onHand / city.medical.capacity * 100).toPrecision(4)}%`}
                  fontSize={14} fill="#333" x={10} y={140}/>
            <Text text={`${city.medical.onHand} / ${city.medical.capacity} pallets`} fontSize={12} fill="#555"
                  x={10} y={155}/>
            <Text text={`Usage: ${city.medical.usageRate} pallets / day`} fontSize={12} fill="#555" x={10}
                  y={170}/>

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