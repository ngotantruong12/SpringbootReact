import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { ToastContainer, toast } from 'react-toastify';
import { memo } from 'react';
import { DeleteProduct } from '../../Service/ProductService';

function DeleteProductModal(props){
    console.log("modal DElete Product");

    const {show , handleClose, dataDeleteProduct, getAllProduct } = props;

    const handleDeleteCategory = async() => {
        try {
            const res = await DeleteProduct(dataDeleteProduct.id);
            if(res && res.statusCode === 204){
                handleClose();
                getAllProduct();
                // console.log([newCategory, ...dataCategory]);
                // setDataCategory([newCategory, ...dataCategory])
                toast.success("Delete Product Product ");
                
            }
            else{
            toast.error(`Cannot Delete Product !!!!`);
            }
        } catch (error) {
        console.log(error)

        }
    }
    console.log("render table delete product")
    return(
        <>
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
            <Modal.Title>Delete Category</Modal.Title>
            </Modal.Header>
            <Modal.Body>
            <div className="mb-3">
                Do you want delete Product with name : <b>{dataDeleteProduct.name}</b>
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
export default memo(DeleteProductModal) ;