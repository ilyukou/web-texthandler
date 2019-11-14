import React from 'react';
import Data from "./components/data/Data";
import UploadForm from "./components/upload/UploadForm";

function App() {
  return (
    <div className="App">
      <UploadForm/>
      <hr/>
      <Data/>
    </div>
  );
}

export default App;
