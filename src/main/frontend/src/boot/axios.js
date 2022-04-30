import axios, { AxiosRequestConfig } from "axios";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { HOST } from "../constants/host";
const controller = new AbortController();
let cancelAxios=axios.CancelToken.source()
axios.defaults.baseURL = HOST;
axios.defaults.responseType= "json"
// axios.defaults.headers['Access-Control-Allow-Origin'] = '*';
// axios.defaults.headers['Access-Control-Allow-Methods'] = 'GET, PUT, PATCH, POST, DELETE, OPTIONS';
axios.defaults.headers['Content-Type'] = 'application/json';
axios.interceptors.response.use(
  (res) => {
    return res;
  },
  async (err) => {
    const originalConfig = err.config;
    if (originalConfig.url !== "/signin" && err.response) {
      // Access Token was expired
      if ((err.response.status === 401 ||err.response.status === 403 )&& !originalConfig._retry) {
        originalConfig._retry = true;
        localStorage.removeItem("ACCESS_TOKEN")
        cancelAxios.cancel("----stop all request---")
        if(err.response.status === 401 ){
          toast.error("Your session is expired! Please login again")
        }
        if(err.response.status === 403){
          toast.error("You not allow to access!")
        }
        
        setTimeout(() => {
          window.location.href="/signin"
        }, 3000);
        controller.abort()
        // try {
        //   const rs = await axios.post("/auth/refreshtoken", {
        //     refreshToken: localStorage.getItem("REFRESH_TOKEN"),
        //   });
        //   const { token } = rs.data;
        //   localStorage.setItem("TOKEN", token);
        //   return axios(originalConfig);
        // } catch (_error) {
        //   return Promise.reject(_error);
        // }
      }
    }
    return Promise.reject(err);
  }
);
const getAccessToken=()=>{
  let accessToken= localStorage.getItem("ACCESS_TOKEN")
  if(accessToken==null){
    return ""
  }
  return accessToken
}
const createConfig=()=>{
  let getConfig = {
    headers: {
      "Content-type": "Application/json",
      "Authorization": "Bearer " + getAccessToken(),
    },
    cancelToken:cancelAxios.token
  }
  return getConfig;
}
const multipartConfig=()=>{
  let getConfig = {
    headers: {
      "Content-type": "multipart/form-data",
      "Authorization": "Bearer " + getAccessToken(),
    },
  }
  return getConfig;
}
const request = {
  get: (url) => axios.get(url, createConfig()),
  post: (url, data) => axios.post(url, data),
  put: (url, data) => axios.put(url, data, createConfig()),
  patch: (url,data) => axios.patch(url,data, createConfig()),
  delete: (url) => axios.delete(url, createConfig()),
  post_multipart:(url, data) => axios.post(url, data, multipartConfig()),
  patch_multipart:(url, data) => axios.patch(url, data, multipartConfig()),
};

export default request;
