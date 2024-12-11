import {City} from "../city/CityClient.ts";
import axios from "axios";

export type Asset = {
    assetId: number
    assetType: AssetType | {}
    waterOnhand: number | null
    foodOnhand: number | null
    medicalOnhand: number | null
    city: City | {}
    status: string
    travelDaysRemaining: number
}
export type AssetType = {
    assetTypeId: number
    modeOfTravel: string
    waterCapacity: number
    foodCapacity: number
    medicalCapacity: number
    fuelUseageRate: number
    speed: number
}

export const getAssetByCityId = async(cityId : number) : Promise<Asset[]> =>{
    const result = await axios.get("http://localhost:8080/api/asset", {params: cityId})
    return result.data
}