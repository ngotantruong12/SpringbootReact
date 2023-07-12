import { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { PutCategory } from '../../Service/CategoryService';
import { ToastContainer, toast } from 'react-toastify';
import { memo } from 'react';

function EditCategoryModal(props){
    console.log("modal edit Category");

    const {show , handleClose,getAllCategory,dataEditCategory} = props;
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

    const handleCloseCategory = () =>{
        handleClose();
        setErrors("");
    }

    useEffect(() => {
        if(show){
            setName(dataEditCategory.name);
            SetDescription(dataEditCategory.description);
        }else{

        }
    }, [dataEditCategory])
    
    const handleEditCategory = async() => {
        const errValidation  = validationForm();
        if ( Object.keys(errValidation).length > 0 )
        {
            setErrors(errValidation);
        }
        else{
            try {
                const res = await PutCategory(dataEditCategory.id,name, description);
                if(res && res.data && res.status === "OK"){
                    SetDescription("");
                    setName("");
                    handleClose();
                    getAllCategory();
                    setErrors("");
                    // console.log([newCategory, ...dataCategory]);
                    // setDataCategory([newCategory, ...dataCategory])
                    toast.success("Edit Category Successfully ");
                    
                }
                else{
                console.log(res.map((itemErr) => {
                    return console.log(itemErr)
                }));
                toast.error(`Cannot Edit Category !!!!`);
                }
            } catch (error) {
            console.log(error)

            }
        }
    }
    console.log("render table edit category")
    return(
        <>
        <Modal show={show} onHide={handleCloseCategory}>
            <Modal.Header closeButton>
            <Modal.Title>Edit Category</Modal.Title>
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
                <input value={description} onChange={(event) => {SetDescription(event.target.value)}} type="text"className="form-control" />
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
            <Button variant="primary" onClick={handleEditCategory}>
                Save
            </Button>
            </Modal.Footer>
        </Modal> 
        <ToastContainer/>
        </>
    );

}
export default memo(EditCategoryModal) ;