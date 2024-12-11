import {City} from "./CityClient.ts";
import {Paper} from "@mui/material";

type props = {
    city: City
}

const CityStatusModal = ({city}: props) => {
    return (
        <Paper sx={{width: 100, height: 100}}>{city.cityName}</Paper>
    )
}

export default CityStatusModal