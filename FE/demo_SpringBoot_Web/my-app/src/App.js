import './App.scss';
import Container from 'react-bootstrap/esm/Container';
import TableCategory from './Component/TableCategory';
import TabNavs from './Component/TabNavs';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import TableProduct from './Component/TableProduct';
import Home from './Component/Home';
import { GetAllCategory } from './Service/CategoryService';
import { useState, useEffect } from 'react';
import { GetAllProduct } from './Service/ProductService';

function App() {
  const [dataCategory , setDataCategory] =  useState([]);
  const [dataProduct , setDataProduct] =  useState([]);

  useEffect(() => {
    console.log("effect data ")
    getAllCategory();
    getAllProduct();
  }, [])

  const getAllCategory = async() =>{
        const res = await GetAllCategory();
        if (res && res.data && res.status === "OK"){
            setDataCategory(res.data);
        }
        else{
            console.log("err get data");
        }
  }

  const getAllProduct = async() =>{
    const res = await GetAllProduct();
    if (res && res.data && res.status === "OK"){
        setDataProduct(res.data);
    }
    else{
        console.log("err get data");
    }
}

  return (
    <div className='app-container'>
      {/* <Header/> */}
      <BrowserRouter>
       
        <Container>
        <TabNavs/>
        <Routes>
          <Route path='/' element={<Home/>} />
          <Route path='/category' element={<TableCategory dataCategory={dataCategory} getAllCategory = {getAllCategory}/>} />
          <Route path='/product' element={<TableProduct dataProduct = {dataProduct} getAllProduct = {getAllProduct} dataCategory={dataCategory} />} />
        </Routes>
          {/* <TableCategory/> */}
        </Container>
      </BrowserRouter>
    </div>
  );
}

export default App;