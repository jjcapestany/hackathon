import {Group, Layer, Rect} from "react-konva";
import {useEffect, useState} from "react";
import {City} from "../city/CityClient.ts";
import {Html} from "react-konva-utils";
import TransferForm from "./TransferForm.tsx";

type TransferRequestProps = {
  isOpen: boolean
  cities: City[]
  selectedCity: City
}

const TransferRequest = ({isOpen, cities, selectedCity}: TransferRequestProps) => {
  const [open, setOpen] = useState<boolean>(true)

  useEffect(() => {
  }, []);

  return (
    <Layer>
      {open &&
        <Group draggable>
          <Rect x={48} y={38} width={304} height={304} fill={"black"}/>
          <Rect x={50} y={40} width={300} height={300} fill={"#F5F5F5"}/>
          <Html>
            <TransferForm selectedCity={selectedCity} cities={cities}></TransferForm>
          </Html>
        </Group>
      }
    </Layer>
  )
}
export default TransferRequest;