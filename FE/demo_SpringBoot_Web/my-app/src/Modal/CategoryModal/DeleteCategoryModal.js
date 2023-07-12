import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { DeleteCategory } from '../../Service/CategoryService';
import { ToastContainer, toast } from 'react-toastify';
import { memo } from 'react';

function DeleteCategoryModal(props){
    console.log("modal DElete Category");

    const {show , handleClose, getAllCategory,dataEditCategory} = props;

    const handleDeleteCategory = async() => {
        try {
            const res = await DeleteCategory(dataEditCategory.id);
            if(res && res.statusCode === 204){
                handleClose();
                getAllCategory();
                // console.log([newCategory, ...dataCategory]);
                // setDataCategory([newCategory, ...dataCategory])
                toast.success("Delete Category Successfully ");
                
            }
            else{
            toast.error(`Cannot Delete Category !!!!`);
            }
        } catch (error) {
        console.log(error)

        }
    }
    console.log("render table delete category")
    return(
        <>
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
            <Modal.Title>Delete Category</Modal.Title>
            </Modal.Header>
            <Modal.Body>
            <div className="mb-3">
                Do you want delete Category with name : <b>{dataEditCategory.name}</b>
            </div>
            </Modal.Body>
            <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
                Cancel
            </Button>
            <Button variant="primary" onClick={handleDeleteCategory}>
                Confirm
            </Button>
            </Modal.Footer>
        </Modal> 
        <ToastContainer/>
        </>
    );

}
export default memo(DeleteCategoryModal) ;