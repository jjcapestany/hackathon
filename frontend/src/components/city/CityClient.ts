import axios from "axios";
import {FoodType, MedicalType, WaterType} from "../aide/AideHelper.ts";


export type City = {
    id: number
    cityName: string
    population: number
    xAxis: number | null
    yAxis: number | null
    fuel: number
    water: WaterType | {}
    food: FoodType | {}
    aide: MedicalType | {}
}

export const getCity = async (): Promise<City[]> => {
    const result = await axios.get<City[]>(`http://localhost:8080/api/city`);
    return result.data;
}