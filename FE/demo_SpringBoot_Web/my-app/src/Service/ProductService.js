import axios from './CustomizeAxios'

function GetAllProduct(){
    return axios.get(`/product`); 
}

function PostProduct({...pram}){
    // console.log(typeof (pram));
    return axios.post('/product', {
        ...pram
    })
}

function PutProduct(id ,{...pram}){
    return axios.put(`/product/${id}`,{
        ...pram
    }); 
}

function DeleteProduct(id){
    return axios.delete(`/product/${id}`); 
}

export {GetAllProduct , PostProduct, PutProduct , DeleteProduct};