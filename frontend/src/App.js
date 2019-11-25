import { Route, BrowserRouter as Router } from 'react-router-dom'

import CreateLeadView from './lead/LeadCreateView'
import LoginView from './login/LoginView'
import MainView from './main/MainView'
import React from 'react'

function App() {
  return (
    <div className='App'>
      <Router>
        <Route exact path='/' component={LoginView} />
        <Route path='/lead' component={MainView} />
        <Route path='/lead/create' component={CreateLeadView} />
      </Router>
    </div>
  )
}

export default App
