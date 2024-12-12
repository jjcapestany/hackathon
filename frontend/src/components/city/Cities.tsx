import {Circle, Group, Image, Layer, Line, Text} from "react-konva";
import {City} from "./CityClient.ts";
import CityStatusModal from "./CityStatusModal.tsx";
import base from "../../assets/base.png"
import {MutableRefObject, useEffect, useState} from "react";
import city_black from "../../assets/city_black.png";
import TransferRequest from "../transfers/TransferRequest.tsx";
import Konva from "konva";
import {IMAGE_HEIGHT, IMAGE_WIDTH} from "../Map.tsx";
import {getPath} from "../Route/PathHelper.ts";

export type CitiesProps = {
  cities: City[],
  imageRef: MutableRefObject<Konva.Image | null>
}

type RouteLineProps = CitiesProps & {
  currentCity: City,
  type: String
}

const RouteLine = ({cities, currentCity, imageRef, type}: RouteLineProps) => {
  const [points, setPoints] = useState<number[]>([]);

  useEffect(() => {
    setPoints([]);

    const fetchAndUpdatePoints = async () => {
      try {
        const pathResponse = await getPath(currentCity.id, 6, type);
        if (!pathResponse.pathFound || !pathResponse.cityIds.length) return;

        // Start with the first city's coordinates
        const newPoints: number[] = [];

        // Add each city's coordinates in sequence
        for (let i = 0; i < pathResponse.cityIds.length; i++) {
          const currentCityId = pathResponse.cityIds[i];
          const city = cities.find(c => c.id === currentCityId);
          if (city) {
            newPoints.push(getX(city));
            newPoints.push(getY(city));
          }
        }

        setPoints(newPoints);
      } catch (error) {
        console.error("Error fetching path:", error);
      }
    };

    fetchAndUpdatePoints();
  }, [currentCity, cities, imageRef]);

  const getX = (c: City) => {
    return (imageRef.current?.x() ?? 0) + IMAGE_WIDTH * c.xcoord + 50;
  };

  const getY = (c: City) => {
    return (imageRef.current?.y() ?? 0) + IMAGE_HEIGHT * c.ycoord + 50;
  };

  const getLineColor = (t: String) => {
    let color = "black"
    if (t === "Sea") color = "blue"
    else if (t === "Land") color = "green"
    return color
  }

  return points.length > 0 ? (
      <Line
        points = {points}
        stroke = {getLineColor(type)}
        strokeWidth = {2}
      />
    ):
    null;
};

const Cities = ({cities, imageRef}: CitiesProps) => {
  const [isCityStatusOpen, setIsCityStatusOpen] = useState(false);
  const [cityStatusContent, setCityStatusContent] = useState<City>({} as City)
  const [cityImage, setCityImage] = useState<CanvasImageSource | undefined>(undefined);
  const [baseImage, setBaseImage] = useState<CanvasImageSource | undefined>(undefined);
    const [transferModal, setIsTransferModal] = useState<boolean>(false)


  const handleCityStatusClick = (name: string) => {
    const selectedCity = cities.find(c => c.name === name);
    if (selectedCity) {
      setCityStatusContent(selectedCity)
      setIsCityStatusOpen(true);
    }
  };

  useEffect(() => {
    const loadImage = (src: string, setImage: (img: CanvasImageSource) => void) => {
      const img = new window.Image();
      img.src = src;
      img.onload = () => setImage(img);
    };

    loadImage(city_black, setCityImage);
    loadImage(base, setBaseImage)
  }, []);

  useEffect(() => {
        if (cities.find(c => c.name === cityStatusContent.name)) {
            setCityStatusContent(cities.find(c => c.name === cityStatusContent.name)!)
        }
    }, [cities]);

    const getColor = (c: City) => {

    if ((c.water.onHand / c.water.capacity < .2)
      || (c.food.onHand / c.food.capacity < .2)
      || (c.medical.onHand / c.medical.capacity < .2)) {
      return "#ff6666"
    } else if (c.water.onHand / c.water.capacity < .75
      || c.food.onHand / c.food.capacity < .75
      || c.medical.onHand / c.medical.capacity < .75) return "#ffff66"
    return "#719D71"
  }


  return (
    <>
      <Layer>
        {cities.map((c, index) => (
          <Group key={index} x={(imageRef.current?.x() ?? 0) + IMAGE_WIDTH * c.xcoord}
                 y={(imageRef.current?.y() ?? 0) + IMAGE_HEIGHT * c.ycoord} draggable
                 onClick={() => handleCityStatusClick(c.name)}>
            <Text text={c.name}/>
            <Circle x={50} y={40} radius={25}
                    fill={c.name == "TF Main" ? "grey" : getColor(c)}/>
            {c.name == "TF Main" ? <Image x={35} y={25} width={30} height={30} image={baseImage}/> :
              <Image x={35} y={25} width={30} height={30} image={cityImage}/>}
          </Group>
        ))}
      </Layer>
      {isCityStatusOpen && cityStatusContent && (
        <Layer>
          <CityStatusModal setIsOpen={setIsCityStatusOpen} city={cityStatusContent} setIsTransferOpen={setIsTransferModal}/>
          <RouteLine type={"Air"} imageRef={imageRef} currentCity={cityStatusContent} cities={cities}/>
          <RouteLine type={"Sea"} imageRef={imageRef} currentCity={cityStatusContent} cities={cities}/>
          <RouteLine type={"Land"} imageRef={imageRef} currentCity={cityStatusContent} cities={cities}/>

        </Layer>
      )}
            { transferModal && <TransferRequest cities={cities} selectedCity={cityStatusContent} setIsTransferOpen={setIsTransferModal}/>}
    </>
  )
}
export default Cities;