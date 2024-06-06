import { createContext } from "react";

export const AuthContext = createContext({
     userSignedIn: false , setUserSignedIn: () => { }
})




/* for global sign in state
* creating a context in React
* the Provider component in app.jsx is used to wrap components that need access to this value 

* and then consumer components can access this value with the useContext hook
*/

/* I was looking into using Auth Payload but maybe a good to do in a future iteration */


// export const AuthContext = createContext({
//     userSignedIn: false,
   
//     username: null,
//     setUserSignedIn: () => {},

//     setUsername: () => {}
// })