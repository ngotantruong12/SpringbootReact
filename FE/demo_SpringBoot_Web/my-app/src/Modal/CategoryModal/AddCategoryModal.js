import { memo, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { PostCategory } from '../../Service/CategoryService';
import { ToastContainer, toast } from 'react-toastify';

function AddCategoryModal(props){
    console.log("modal addd new category")
    const {show , handleClose, getAllCategory} = props;
    const [name , setName] = useState('');
    const [description , SetDescription] = useState('');
    const [ errors, setErrors ] = useState({})

    const validationForm = () => {
        const newErrors = {}
        // name errors
        if ( !name || name === '' ) newErrors.name = 'Name must not NULL !'
        else if ( name.length < 2 ) newErrors.name = 'Name should have at least 2 characters !'
        // food errors
        if ( !description || description === '' ) newErrors.description = 'Description must not NULL !'
        else if ( description.length < 2 ) newErrors.description = 'Description should have at least 2 characters !'
        // console.log( "validation form >>>", newErrors)
        return newErrors
    }

    const setEmptyAllFiled= ()=>{
        SetDescription("");
        setName("");
        setErrors("");
    }

    const handleCloseCategory = () =>{
        handleClose();
        setEmptyAllFiled();
    }

    const handleSaveCategory = async() => {
        const errValidation  = validationForm();
        if ( Object.keys(errValidation).length > 0 )
        {
            setErrors(errValidation);
        }
        else{
            try {
                const res = await PostCategory(name, description);
                if(res && res.data && res.status === "OK"){
                    getAllCategory();
                    handleCloseCategory();
                    // console.log([newCategory, ...dataCategory]);
                    // setDataCategory([newCategory, ...dataCategory])
                    toast.success("Add Category Successfully ");
                    
                }
                else{
                    console.log(res.map((itemErr) => {
                        return console.log(itemErr)
                }));
                    toast.error(`Cannot Add Category !!!!`);
                }
               } catch (error) {
                console.log(error)
               }
        }
    }


    return(
        <>
        <Modal show={show} onHide={handleCloseCategory}>
            <Modal.Header closeButton>
            <Modal.Title>Add New Category</Modal.Title>
            </Modal.Header>
            <Modal.Body>
            <div className="mb-3">
                <label className="form-label">Name</label>
                <input onChange={(event) => {setName(event.target.value)}} type="text" className="form-control" />
                {
                    (errors.name) ? (
                        <div  className='err-validation'><i>{errors.name}</i></div>
                    ) : null
                }
            </div>
            <div className="mb-3">
                <label className="form-label">Description</label>
                <input onChange={(event) => {SetDescription(event.target.value)}} type="text"className="form-control" />
                {
                    (errors.description) ? (
                        <div className='err-validation'><i>{errors.description}</i></div>
                    ) : null
                }
            </div>
            </Modal.Body>
            <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseCategory}>
                Cancel
            </Button>
            <Button variant="primary" onClick={handleSaveCategory}>
                Save
            </Button>
            </Modal.Footer>
      </Modal> 
      <ToastContainer/>
        </>
    );
}

export default memo(AddCategoryModal) ;