import { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { ToastContainer, toast } from 'react-toastify';
import { memo } from 'react';
import { PutProduct } from '../../Service/ProductService';

function EditProductModal(props){
    console.log("modal edit Product");

    const {show , handleClose,dataEditProduct, dataCategory, getAllProduct} = props;

    const [name , setName] = useState('');
    const [description , setDescription] = useState('');
    const [price , setPrice] = useState('');
    const [category_id , setCategoryId ] = useState('');
    const [ errors, setErrors ] = useState({})

    const handleCloseProduct = () =>{
        handleClose();
        setErrors("");
    }

    const validationForm = () => {
        const newErrors = {}
        // name errors
        if ( !name || name === '' ) newErrors.name = 'Name must not NULL !'
        else if ( name.length < 2 ) newErrors.name = 'Name should have at least 2 characters !'
        // food errors
        if ( !description || description === '' ) newErrors.description = 'Description must not NULL !'
        else if ( description.length < 2 ) newErrors.description = 'Description should have at least 2 characters !'
        // console.log( "validation form >>>", newErrors)
        if ( !category_id || category_id === '' ) newErrors.category_id = 'Category_id must not NULL !'
        if ( !price || price === 0 ) newErrors.price = 'Price must not NULL !'
        else if ( price < 1000 ) newErrors.price = 'Price should not be less than 1000 !'
       
        return newErrors
    }

    useEffect(() => {
        if(show){
            setName(dataEditProduct.name);
            setDescription(dataEditProduct.description);
            setPrice(dataEditProduct.price);
            setCategoryId(dataEditProduct.category_id)
        }else{

        }
    }, [dataEditProduct])
    
    const handleEditProduct = async() => {
        const errValidation  = validationForm();
        if ( Object.keys(errValidation).length > 0 )
        {
            setErrors(errValidation);
        }
        else{
            try {
                const id = dataEditProduct.id;
                // console.log("infor edit pro >>>>" , { id ,name, description, category_id})
                const res = await PutProduct( id ,{ name, price, description, category_id});
                if(res && res.data && res.status === "OK"){
                    setDescription("");
                    setName("");
                    setPrice('')
                    setCategoryId('') // ??????
                    handleClose();
                    getAllProduct();
                    // console.log([newCategory, ...dataCategory]);
                    // setDataCategory([newCategory, ...dataCategory])
                    toast.success("Edit Product Successfully ");
                    
            }
            else{
                console.log(res.map((itemErr) => {
                    return console.log(itemErr)
            }));
            toast.error(`Cannot Edit PProduct !!!!`);
            }
            } catch (error) {
                console.log(error)
            }
        }
       
    }
    console.log("render table edit product")
    return(
        <>
        <Modal show={show} onHide={handleCloseProduct}>
            <Modal.Header closeButton>
            <Modal.Title>Edit Product</Modal.Title>
            </Modal.Header>
            <Modal.Body>
            <div className="mb-3">
                <label className="form-label">Name</label>
                <input value={name} onChange={(event) => {setName(event.target.value)}} type="text" className="form-control" />
                {
                    (errors.name) ? (
                        <div  className='err-validation'><i>{errors.name}</i></div>
                    ) : null
                }
            </div>
            <div className="mb-3">
                <label className="form-label">Description</label>
                <input value={description} onChange={(event) => {setDescription(event.target.value)}} type="text"className="form-control" />
                {
                    (errors.description) ? (
                        <div className='err-validation'><i>{errors.description}</i></div>
                    ) : null
                }
            </div>
            <div className="mb-3">
                <label className="form-label">Price</label>
                <input value={price} onChange={(event) => {setPrice(event.target.value)}}  type="number" min="1" step="1" className="form-control" />
                {
                    (errors.price) ? (
                        <div className='err-validation'><i>{errors.price}</i></div>
                    ) : null
                }
            </div>
            <div className="mb-3">
                <label className="form-label">Category</label>
                <select value={category_id} onChange={(e) => {setCategoryId(e.target.value)}} className="form-select" aria-label="Default select example">
                    {/* <option value >List Category</option> */}
                    {dataCategory && dataCategory.length > 0 &&
                    dataCategory.map((item, index) => {
                        return (
                            <option key={index} value={item.id}>{item.name}</option>)                     
                    })}
                </select>
                {
                    (errors.category_id) ? (
                        <div className='err-validation'><i>{errors.category_id}</i></div>
                    ) : null
                }
            </div>
            </Modal.Body>
            <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseProduct}>
                Cancel
            </Button>
            <Button variant="primary" onClick={handleEditProduct}>
                Save
            </Button>
            </Modal.Footer>
        </Modal> 
        <ToastContainer/>
        </>
    );

}
export default memo(EditProductModal) ;