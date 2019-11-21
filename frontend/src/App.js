import { Route, BrowserRouter as Router } from 'react-router-dom'

import LoginView from './login/LoginView'
import React from 'react'

function App() {
  return (
    <div className='App'>
      <Router>
        <Route path='/' component={LoginView} />
      </Router>
    </div>
  )
}

export default App
