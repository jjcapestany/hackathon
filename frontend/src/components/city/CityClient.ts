import axios from "axios";
import {Food, Medical, Water} from "../aide/AideHelper.ts";


export type City = {
    id: number
    name: string
    population: number
    xcoord: number
    ycoord: number
    fuel: number
    water: Water
    food: Food
    medical: Medical
}

export const getCity = async (): Promise<City[]> => {
    const result = await axios.get<City[]>(`http://localhost:8080/api/city/all`);
    console.log(result.data)
    return result.data;
}