import axios from "axios";

export type RouteDto = {
  sourceCityName: string,
  destinationCityName: string,
  transportationType: 'Air' | 'Land' | 'Sea',
  cost: number
}

export type PathDto = {
  pathFound: boolean,
  transportationType: 'Air' | 'Land' | 'Sea',
  totalDistance: number,
  cityIds: number[],
  routes: RouteDto[]
}

export const getPath = async (sourceId: number, destinationId: number, type: String) : Promise<PathDto> =>{
  const result = await axios.get(`http://localhost:8080/api/routes/shortest-path/${sourceId}/${destinationId}/${type}`)
  return result.data
}