import {Layer, Circle} from "react-konva";

type CitiesProps = {
    stageWidth: number,
    stageHeight: number
}

const Cities= ({stageWidth, stageHeight} : CitiesProps) => {
    return(
        <Layer >
            {/*Turtleback Island*/}
            <Circle
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
    )
}
export default Cities;