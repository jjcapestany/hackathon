import axios from "axios";

export type City = {
    cityName: string
    population: number
}

export const getCity = async (): Promise<City[]> => {
    const result = await axios.get<City[]>(`http://localhost:8080/api/city`);
    console.log(result.data)
    return result.data;
}