import axios from "axios";

export type City = {
    id: number
    cityName: string
    population: number
    xAxis: number | null
    yAxis: number | null
}

export const getCity = async (): Promise<City[]> => {
    const result = await axios.get<City[]>(`http://localhost:8080/api/city`);
    return result.data;
}