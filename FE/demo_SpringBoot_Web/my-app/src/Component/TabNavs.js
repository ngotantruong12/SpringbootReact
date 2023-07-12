import Nav from 'react-bootstrap/Nav';
import { Link } from "react-router-dom"

function TabNavs() {
  return (

    <Nav variant="pills" defaultActiveKey="/home">
      <Nav.Item>
      <Nav.Link eventKey="link-1"><Link className='color-link' to="/">Home</Link></Nav.Link>
      </Nav.Item>
      <Nav.Item>
      <Nav.Link eventKey="link-2"><Link className='color-link' to="/category">Category</Link></Nav.Link>
      </Nav.Item>
      <Nav.Item>
      <Nav.Link eventKey="link-3"><Link className='color-link' to="/product">Product</Link></Nav.Link>
      </Nav.Item>
    </Nav>
    // <Nav variant="tabs" defaultActiveKey="/">
    //   <Nav.Item>
    //     <Nav.Link eventKey="link-1"><Link to="/">Home</Link></Nav.Link>
    //   </Nav.Item>
    //   <Nav.Item>
    //     <Nav.Link eventKey="link-2"><Link to="/category">Category</Link></Nav.Link>
    //   </Nav.Item>
    //   <Nav.Item>
    //     <Nav.Link eventKey="link-3"><Link to="/product">Product</Link></Nav.Link>
    //   </Nav.Item>
    // </Nav>
  );
}

export default TabNavs;