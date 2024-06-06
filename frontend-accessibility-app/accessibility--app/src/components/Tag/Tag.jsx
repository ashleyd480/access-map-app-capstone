import { Button } from "react-bootstrap";
const Tag = ({ tagName, tagId, handleAddTag }) => {
    
   // pass tag id to event handler 
    
    return (
        <>
            <Button onClick = {() => handleAddTag(tagId)}>{tagName}</Button>


        </>
    )    
}
export default Tag;
        
// Ty @Chad for your help with this logic idea - giving credit where it's due!