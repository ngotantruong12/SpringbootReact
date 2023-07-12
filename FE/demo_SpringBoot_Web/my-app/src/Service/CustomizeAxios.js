import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS',   
    }
});

instance.interceptors.response.use(function (response){
    // console.log("Res >>>>", response)
    return response.data ? response.data : {statusCode: response.status};
}, function (error){
    return error.response.data.details ;
});

export default instance;

// // interceptor giong nhu middelware trong node js
// trc khi gui req lem server va nha dc res thi se~ lam gi
// vd: them token thi bo? vao trong nay
// xu li trc khi nhan du lieu ve
// cai token het han -> logout ng dung- ra luon -> phai xu li o day