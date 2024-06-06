import { createContext } from "react";

export const UserDataContext = createContext({
  userData: {
    // username: '', we getting username from auth 
    password: '',
    firstName: '',
    lastName: '',
    email: '',
      city: '',
      state: '',
    
  },
  setUserData: () => {},
});