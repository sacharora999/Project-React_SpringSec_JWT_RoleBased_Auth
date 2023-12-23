import axios from 'axios'
import { getToken } from './AuthService';

const BASE_REST_API_URL = "http://localhost:8080/api/todos";

// Add a request interceptor
axios.interceptors.request.use(function (config) {
    
    config.headers['Authorization'] = getToken()
    // config.headers['Accept-Encoding']=  'gzip, deflate, br'
    // config.headers['Content-Type'] = 'application/json'
    // config.headers['Accept'] =  '*/*'
    // config.headers['Connection'] =  'keep-alive'




    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });


export const getAllTodos = () => axios.get(BASE_REST_API_URL);
export const getAllTodo = (id) => axios.get(BASE_REST_API_URL + '/' + id);
export const addTodos = (todo) => axios.post(BASE_REST_API_URL, todo);
export const updateTodos = (id, todo) => axios.put(BASE_REST_API_URL + '/' +  id, todo);
export const delTodos = (id) => axios.delete(BASE_REST_API_URL + "/" + id)
export const compTodo = (id) => axios.patch(BASE_REST_API_URL + "/" + id + "/complete")
export const incompTodo = (id) => axios.patch(BASE_REST_API_URL + "/" + id + "/incomplete")