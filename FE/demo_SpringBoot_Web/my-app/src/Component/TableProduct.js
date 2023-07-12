import { useState } from 'react';
import Table from 'react-bootstrap/Table';
import AddProductModal from '../Modal/ProductModal/AddProductModal';
import DeleteProductModal from '../Modal/ProductModal/DeleteProductModal';
import EditProductModal from '../Modal/ProductModal/EditProductModal';
function TableProduct(props) {
    const {dataProduct, getAllProduct, dataCategory} = props;

    const [showModalAddProduct, setShowModalAddProduct] = useState(false);
    const [showModalEditProduct, setShowModalEditProduct] = useState(false);
    const [showModalDeleteProduct, setShowModalDeleteProduct] = useState(false);

    const [dataEditProduct , setDataEditProduct] = useState({});
    const [dataDeleteProduct , setDataDeleteProduct] =  useState({});
    
    console.log("data Product >>>>>", dataProduct);


    const handleClose = () => {
        setShowModalAddProduct(false);
        setShowModalEditProduct(false)
        setShowModalDeleteProduct(false)
    }
    const handleShowAddProduct = ()=>{
        setShowModalAddProduct(true);
    }

    const handleEditProduct = (product) =>{
        setShowModalEditProduct(true);
        setDataEditProduct(product)
    }
    
    const handleDeleteProduct = (product) => {
        setShowModalDeleteProduct(true);
        setDataDeleteProduct(product);

    }

    return(
        <> {console.log("render table product")}
        <div className='my-3 add-new'>
          <span>List Product:</span>
          <button onClick={handleShowAddProduct}  className='btn btn-success'>Add New Product</button>
        </div>
        <Table striped bordered hover>
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>CategoryId</th>
            <th>Create Date</th>
            <th>Modified Date</th>
          </tr>
        </thead>
        <tbody>
            {
            dataProduct && dataProduct.length > 0 &&
            dataProduct.map((item, index) => {
                return(
                    <tr key={index}>
                        <td>{item.id}</td>
                        <td>{item.name}</td>
                        <td>{item.description}</td>
                        <td>{item.price}</td>
                        <td>{item.category_id}</td>
                        <td>{item.createdDate}</td>
                        <td>{item.modifiedDate}</td>
                        <td>
                            <button onClick={() => {handleEditProduct(item)}} className='btn btn-warning mx-3'>Edit</button>
                            <button onClick={() => {handleDeleteProduct(item)}}  className='btn btn-danger'>Delete</button>
                        </td>
                    </tr>
                )
            })
            }
        </tbody>
        </Table>
        <AddProductModal  
            show = {showModalAddProduct}
            handleClose = {handleClose}
            dataCategory = {dataCategory}
            getAllProduct= {getAllProduct}
        />
        <EditProductModal 
            show = {showModalEditProduct}
            handleClose = {handleClose}
            dataEditProduct = {dataEditProduct}
            dataCategory = {dataCategory}
            getAllProduct = {getAllProduct}
        />
        <DeleteProductModal 
            show = {showModalDeleteProduct}
            handleClose = {handleClose}
            dataDeleteProduct = {dataDeleteProduct}
            getAllProduct  = {getAllProduct}
        />
        {/* 
        <EditCategoryModal
            show = {showModalEditCategory}
            handleClose = {handleClose}
            getAllCategory = {getAllCategory}
            dataEditCategory = {dataEditCategory}
        />
        <DeleteCategoryModal 
            show = {showModalDeleteCategory}
            handleClose = {handleClose}
            getAllCategory = {getAllCategory}
            dataEditCategory = {dataDeleteCategory} /> */}
        
    </>
    );
}

export default TableProduct;