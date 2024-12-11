import {Stack} from "@mui/material";
import {useEffect, useState} from "react";
import {City, getCity} from "./city/CityClient.ts";


const Map = () => {

    const [city, setCity] = useState<City>({cityName: "", population: 0})

    useEffect(() => {
        getCity().then(setCity)
        console.log(city)
    }, []);
    return (
        <Stack height="110vh" width={"110vw"} sx={{
            backgroundImage: `url(./island_map.png)`,
            backgroundRepeat: 'no-repeat',
            backgroundPosition: 'center'
        }}>
        </Stack>
    );
};

export default Map;
