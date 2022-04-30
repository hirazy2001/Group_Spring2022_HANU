import { HOST } from "../constants/host";
import request from "../boot/axios";

export const signInService=async(data)=>{
   const response= await request.post(`${HOST}/auth/`,JSON.stringify(data))
    return response.data
};
export const signUpService = async (
  data
) => {
  const response = await request.post(`${HOST}/users/`,JSON.stringify(data));
  return response.data;
};
