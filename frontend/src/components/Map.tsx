import {Image, Layer, Stage} from 'react-konva';
import {useEffect, useRef, useState} from "react";
import Cities from "./city/Cities.tsx";
import {City, getCity} from "./city/CityClient.ts";
import {Box} from "@mui/material";
import Konva from "konva";
import {Vector2d} from "konva/lib/types";


export const MIN_ZOOM = 0.8;
export const MAX_ZOOM = 1.3;
export const IMAGE_WIDTH = 2100
export const IMAGE_HEIGHT = 1280

function clamp(val: number, min: number, max: number) {
    return val > max ? max : val < min ? min : val
}

const Map = () => {
    const [stageSize, setStageSize] = useState({
        width: window.innerWidth,
        height: window.innerHeight,
    });
    const [backgroundImage, setBackgroundImage] = useState<CanvasImageSource | undefined>(undefined);
    const [cities, setCities] = useState<City[]>([{} as City])
    const [_, setScale] = useState<number>(1);
    const stageRef = useRef<Konva.Stage | null>(null);
    const containerRef = useRef<HTMLDivElement | null>(null);
    const imageRef = useRef<Konva.Image | null>(null);

    const updateStageSize = () => {
        if (containerRef.current) {
            setStageSize({
                width: containerRef.current.offsetWidth,
                height: containerRef.current.offsetHeight,
            });
        }
    };

    const handleWheel = (e: Konva.KonvaEventObject<WheelEvent>) => {
        e.evt.preventDefault();
        if (stageRef.current) {
            const stage = stageRef.current;
            const scaleBy = 1.05; // Zoom factor
            const oldScale = stage.scaleX();
            const pointer = stage.getPointerPosition();
            if (!pointer) return;
            const mousePointTo = {
                x: (pointer.x - stage.x()) / oldScale,
                y: (pointer.y - stage.y()) / oldScale,
            };
            const newScale = e.evt.deltaY > 0 ? oldScale / scaleBy : oldScale * scaleBy;
            if (newScale < MIN_ZOOM || newScale > MAX_ZOOM) return;
            setScale(newScale);
            const newPos = {
                x: pointer.x - mousePointTo.x * newScale,
                y: pointer.y - mousePointTo.y * newScale,
            };

            stage.scale({x: newScale, y: newScale});
            stage.position(newPos);
            console.log(stage.position())
            stage.batchDraw(); // Redraw
        }
    };

    const handleDrag = (pos: Vector2d) => {
        if (!stageRef.current) {
            return {x: pos.x, y: pos.y}
        }
        return {
            x: clamp(pos.x, stageRef.current && (-IMAGE_WIDTH * stageRef.current?.scaleX() * 2) / 4,
                IMAGE_WIDTH * stageRef.current?.scaleX() * 2) / 4,
            y: clamp(pos.y, stageRef.current && (-IMAGE_HEIGHT * stageRef.current?.scaleY() * 2) / 4,
                IMAGE_HEIGHT * stageRef.current?.scaleY() * 2) / 4,
        }
    }

    useEffect(() => {
        getCity().then((response) => {
            setCities(response)
        });

        const intervalId = setInterval(() => {
            console.log('Update City Array')
            getCity().then((response) => {
                setCities(response)
            });
        }, 6000)

        const img = new window.Image();
        img.src = "./island_map.png";
        img.onload = () => {
            setBackgroundImage(img);
        }

        // Update the size of the canvas on window resize
        window.addEventListener('resize', updateStageSize);
        window.addEventListener('mousedown', updateStageSize);
        updateStageSize(); // Initial call
        return () => {
            window.removeEventListener('resize', updateStageSize);
            clearInterval(intervalId)
        };
    }, []);


    return (
        <Box ref={containerRef} height={"100%"} width={"100%"}>

            < Stage
                draggable
                dragBoundFunc={handleDrag}
                onWheel={handleWheel}
                ref={stageRef}
                width={stageSize.width}
                height={stageSize.height}
            >
                <Layer>
                    <Image
                        ref={imageRef}
                        image={backgroundImage}
                        x={(stageSize.width - IMAGE_WIDTH) / 2}
                        y={(stageSize.height - IMAGE_HEIGHT) / 2}
                        width={IMAGE_WIDTH}
                        height={IMAGE_HEIGHT}
                        listening={false} // Ensures the background doesn't interfere with other interactions
                    />
                </Layer>
                <Cities cities={cities} imageRef={imageRef}/>
            </Stage>
        </Box>)
}

export default Map;
