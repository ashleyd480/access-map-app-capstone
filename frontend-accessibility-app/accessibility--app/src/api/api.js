/*these are functions we can then call to help "collapse" the api calls in our components so not too lengthy */
const apiUrl = "http://localhost:8080";

export const fetchData = async (endpoint) => {// get request
  try {
    const response = await fetch(`${apiUrl}/${endpoint}`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
};


export const postData = async (endpoint, data) => {
  
    const response = await fetch(`${apiUrl}/${endpoint}`, {//post request
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => response.json())
    .then((data) => data)
    .catch((error) => {
      console.error("Error posting data:", error);
      throw error;
    });
  return response;
};


export const updateData = async (endpoint, data) => {
    try {
      // PUT request, specifying method and headers
      const response = await fetch(`${apiUrl}/${endpoint}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });
      if (!response.ok) {
        throw new Error(`Error putting data: ${response.statusText}`);
      }
      const result = await response.json();
      return result;
    } catch (error) {
      console.error("Error putting data:", error);
      throw error;
    }
};
  
export const deleteData = async (endpoint) => {
  try {
    // DELETE request, specifying method and headers
    const response = await fetch(`${apiUrl}/${endpoint}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw new Error(`Error deleting data: ${response.statusText}`);
    }
    const result = await response.json();
    return result;
  } catch (error) {
    console.error("Error deleting data:", error);
    throw error;
  }
};