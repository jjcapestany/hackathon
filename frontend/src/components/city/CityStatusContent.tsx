import {City} from "./CityClient.ts";
import {Dispatch, SetStateAction} from "react";
import {Stack, Typography} from "@mui/material";

type props = {
    city: City
    setIsOpen: Dispatch<SetStateAction<boolean>>
    setIsTransferOpen: Dispatch<SetStateAction<boolean>>
}

const CityStatusContent = ({city, setIsOpen, setIsTransferOpen}: props) => {
    return (
        <Stack sx={{position: 'absolute', top: 10, left: 20, width: 275}} flexDirection={'column'} gap={1}>
            <Typography>{city.name}</Typography>
        </Stack>
    )
}

export default CityStatusContent;