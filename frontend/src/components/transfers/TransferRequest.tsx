import {Group, Layer, Rect, Text} from "react-konva";
import {City} from "../city/CityClient.ts";
import {Html} from "react-konva-utils";
import TransferForm from "./TransferForm.tsx";
import {Dispatch, SetStateAction} from "react";

type TransferRequestProps = {
  cities: City[]
  selectedCity: City
  setIsTransferOpen: Dispatch<SetStateAction<boolean>>
}

const TransferRequest = ({cities, selectedCity, setIsTransferOpen}: TransferRequestProps) => {

  return (
    <Layer>
        <Group draggable>
          <Rect x={48} y={20} width={304} height={422} fill={"black"}/>
          <Rect x={50} y={40} width={300} height={400} fill={"#F5F5F5"}/>
          <Text
            text="X"
            fontSize={14}
            fill="black"
            x={335}
            y={44}
            onClick={() => {
              setIsTransferOpen(false)
            }}
            style={{cursor: "pointer"}}
          />
          <Html>
            <TransferForm selectedCity={selectedCity} cities={cities} setIsTransferOpen={setIsTransferOpen}></TransferForm>
          </Html>
        </Group>
    </Layer>
  )
}
export default TransferRequest;