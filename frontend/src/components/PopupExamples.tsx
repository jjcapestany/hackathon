import {Group, Rect, Text} from "react-konva";
import {useState} from "react";

const PopupExamples = () => {
    const cityOptions = ["City A", "City B", "City C", "City D", "City E"];
    const aidOptions = ["Water", "Food", "Medical"];
    const transportOptions = ["Air", "Sea", "Land"];
    const [aidAmount, setAidAmount] = useState("");

    return (
        <>
            {/* Example for City Status */}
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

            {/* Aid Transfer Popup */}
            <Group x={100} y={100} draggable={true}>
                {/* Background Rectangle */}
                <Rect
                    width={300}
                    height={300}
                    fill="#f5f5f5"
                    cornerRadius={10}
                />

                {/* Title */}
                <Text
                    text="Transfer Aid"
                    fontSize={16}
                    fontStyle="bold"
                    fill="#333"
                    x={10}
                    y={10}
                />

                {/* Close Button */}
                <Text
                    text="X"
                    fontSize={14}
                    fill="black"
                    x={270}
                    y={10}
                    onClick={() => {
                        console.log("onClose")}}
                    style={{ cursor: "pointer" }}
                />

                {/* Destination City Selector */}
                <Text
                    text="Select City:"
                    fontSize={14}
                    fill="#333"
                    x={10}
                    y={50}
                />
                {cityOptions.map((city, index) => (
                    <Text
                        key={city}
                        text={city}
                        fontSize={12}
                        fill="#555"
                        x={20}
                        y={70 + index * 20}
                        onClick={() => console.log(`City selected: ${city}`)}
                        style={{ cursor: "pointer" }}
                    />
                ))}

                {/* Aid Type Selector */}
                <Text
                    text="Aid Type:"
                    fontSize={14}
                    fill="#333"
                    x={150}
                    y={50}
                />
                {aidOptions.map((aid, index) => (
                    <Text
                        key={aid}
                        text={aid}
                        fontSize={12}
                        fill="#555"
                        x={160}
                        y={70 + index * 20}
                        onClick={() => console.log(`Aid selected: ${aid}`)}
                        style={{ cursor: "pointer" }}
                    />
                ))}

                {/* Transport Selector */}
                <Text
                    text="Transport:"
                    fontSize={14}
                    fill="#333"
                    x={10}
                    y={170}
                />
                {transportOptions.map((transport, index) => (
                    <Text
                        key={transport}
                        text={transport}
                        fontSize={12}
                        fill="#555"
                        x={20}
                        y={190 + index * 20}
                        onClick={() =>
                            console.log(`Transport selected: ${transport}`)
                        }
                        style={{ cursor: "pointer" }}
                    />
                ))}
                {/* Aid Amount Input */}
                <Text
                    text="Amount:"
                    fontSize={14}
                    fill="#333"
                    x={150}
                    y={190}
                />
                <Text
                    text={aidAmount || "Enter amount"}
                    fontSize={12}
                    fill="#555"
                    x={160}
                    y={210}
                    onClick={() => {
                        const amount = prompt("Enter aid amount:");
                        if (amount) setAidAmount(amount);
                    }}
                    style={{ cursor: "pointer" }}
                />
                {/* Submit Button */}
                <Rect
                    x={100}
                    y={260}
                    width={100}
                    height={30}
                    fill="#ffcc01"
                    cornerRadius={5}
                    onClick={() => {
                        "handleSubmit"
                    }}
                    style={{ cursor: "pointer" }}
                />
                <Text
                    text="Submit"
                    fontSize={14}
                    fill="black"
                    x={125}
                    y={267}
                    onClick={() => {
                        "handleSubmit"
                    }}
                    style={{ cursor: "pointer" }}
                />
            </Group>
        </>
    )
}

export default PopupExamples;