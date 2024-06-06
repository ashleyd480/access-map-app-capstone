import { createContext } from "react";

export const ContributeContext = createContext({
  
        showReviewandTagButton: false,
   
   


});

// this is to conditonally render the contribute buttons 
// will be set to true only when navigating from contribute page
// and then we set it to false on the search page 
// the on place detail page- only if true do we show two buttons