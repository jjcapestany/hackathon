import {City} from "../city/CityClient.ts";
import axios from "axios";

export type EventType = {
    eventId: number | null
    city: City | {}
    timestamp: number
    description: string
}

export const getEventsByCityId = async (cityId: number): Promise<EventType> => {
    const result = await axios.get("http://localhost:8080/api/events", {params: cityId})
    return result.data
}