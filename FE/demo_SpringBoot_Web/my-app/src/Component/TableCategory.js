import { useState } from 'react';
import Table from 'react-bootstrap/Table';

import AddCategoryModal from '../Modal/CategoryModal/AddCategoryModal';
import EditCategoryModal from '../Modal/CategoryModal/EditCategoryModal';
import DeleteCategoryModal from '../Modal/CategoryModal/DeleteCategoryModal';
function TableCategory(props){

    const {dataCategory, getAllCategory} = props;
   
    const [showModalAddCategory, setShowModalAddCategory] = useState(false);
    
    const [showModalEditCategory, setshowModalEditCategory] = useState(false);
    const [dataEditCategory, setDataEditCategory] = useState([]);

    const [showModalDeleteCategory, setshowModalDeleteCategory] = useState(false);
    const [dataDeleteCategory, setDataDeleteCategory] = useState({});

    const handleClose = () => {
        setShowModalAddCategory(false);
        setshowModalEditCategory(false);
        setshowModalDeleteCategory(false);
    }

    const handleShowAddCategory = () => {
        setShowModalAddCategory(true);
    }

    const handleShowEditCategory = (category)=> {
        setshowModalEditCategory(true);
        setDataEditCategory(category)

    }

    const handleShowDeleteCategory= (category) => {
        setshowModalDeleteCategory(true)
        setDataDeleteCategory(category);
    }

    
    console.log("data category >>>>>" , [dataCategory])

    return(
        <> {console.log("render table cate")}
        <div className='my-3 add-new'>
          <span>List Category:</span>
          <button onClick={handleShowAddCategory}  className='btn btn-success'>Add New Category</button>
        </div>
        <Table striped bordered hover>
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Create Date</th>
            <th>Modified Date</th>
          </tr>
        </thead>
        <tbody>
            {
            dataCategory && dataCategory.length > 0 &&
            dataCategory.map((item, index) => {
                return(
                    <tr key={index}>
                        <td>{item.id}</td>
                        <td>{item.name}</td>
                        <td>{item.description}</td>
                        <td>{item.createdDate}</td>
                        <td>{item.modifiedDate}</td>
                        <td>
                            <button onClick={() => {handleShowEditCategory(item)}}  className='btn btn-warning mx-3'>Edit</button>
                            <button onClick={() => {handleShowDeleteCategory(item)}} className='btn btn-danger'>Delete</button>
                        </td>
                    </tr>
                )
            })
            }
        </tbody>
        </Table>
        <AddCategoryModal 
            show = {showModalAddCategory} 
            handleClose = {handleClose}
            getAllCategory= {getAllCategory}
        //  dataCategory = {dataCategory}
        //  setDataCategory ={setDataCategory}
        />
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
            dataEditCategory = {dataDeleteCategory} />
        
    </>
    )
}
export default TableCategory;