import React, { useState } from "react";
import "./SearchBar.css"
import { Button } from "react-bootstrap";
import { Form, Row, Col } from "react-bootstrap";

const SearchBar = ({searchFunctionProp, searchData, setSearchData}) => {

    
      const handleChange = (event) => {
        const { name, value } = event.target;
        setSearchData((prevSearchData) => ({
          ...prevSearchData,
          [name]: value,
        }));
      };
    
   
  return (
    <div className="search-container">
      <h4 className= "search-greeting">Search by either keyword and/or city</h4>
     
    
      <Row>
        <Col md="6">
          <Form.Control
            type="text"
            placeholder="Enter keyword..."
            name="placeName"
            value={searchData.placeName}
            onChange={handleChange}
          />
        </Col>
        <Col md="6">
          <Form.Control
            type="text"
            placeholder="Enter city..."
            name="city"
            value={searchData.city}
            onChange={handleChange}
          />
        </Col>
      </Row>
      <Row className= "sorting">
              <Col md="6">
              <Form.Label>Sort by:</Form.Label> 

          <Form.Control
            as="select"
            name="sortField"
                      value={searchData.sortField}
                      placeholder= "Sortby"
            onChange={handleChange}
                  >
            <option value="name">Name</option>
            <option value="rating">Rating</option>
          </Form.Control>
        </Col>
              <Col md="6">
              <Form.Label>Sort direction:</Form.Label> 
          <Form.Control
            as="select"
            name="sortDirection"
            value={searchData.sortDirection}
            onChange={handleChange}
          >
            <option value="ascending">Ascending</option>
            <option value="descending">Descending</option>
          </Form.Control>
        </Col>
      </Row>
      
        <Button onClick={searchFunctionProp} className= "searchButton"> Search</Button>
        
    </div>
  );
};

export default SearchBar;
