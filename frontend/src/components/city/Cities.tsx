import {Layer, Circle} from "react-konva";

const Cities= () => {
    return(
        <Layer>
            {/*Turtleback Island*/}
            <Circle x={210} y={710} radius={30} fill="green" />
            {/*Mangrove Coastline*/}
            <Circle x={730} y={372} radius={30} fill="green" />
            {/*Lagoon Bay Village*/}
            <Circle x={950} y={280} radius={30} fill="green" />
            {/*Moonlit Cave*/}
            <Circle x={1000} y={365} radius={30} fill="green" />
            {/*Storm Breaker Cliffs*/}
            <Circle x={1130} y={360} radius={30} fill="green" />
            {/*Sunset Cove District*/}
            <Circle x={1030} y={490} radius={30} fill="green" />
            {/*Coral Reef Community*/}
            <Circle x={1530} y={155} radius={30} fill="green" />
            {/*TF Main*/}
            <Circle x={840} y={380} radius={30} fill="green" />
        </Layer>
    )
}
export default Cities;