import axios from './CustomizeAxios'

function GetAllCategory(){
    return axios.get(`/category`); 
}

function PostCategory(name , description){
    // console.log(name , image);
    return axios.post('/category', {
        name, description
    })
}

function PutCategory(id ,name , description){
    return axios.put(`/category/${id}`,{
        name,
        description
    }); 
}

function DeleteCategory(id){
    return axios.delete(`/category/${id}`); 
}

export {GetAllCategory , PostCategory, PutCategory,DeleteCategory};