import './main.css'

import AppBar from '@material-ui/core/AppBar'
import IconButton from '@material-ui/core/IconButton'
import LeadRootView from '../lead/LeadRootView'
import MenuIcon from '@material-ui/icons/Menu'
import Paper from '@material-ui/core/Paper'
import React from 'react'
import ToolBar from '@material-ui/core/Toolbar'

export default () => (
  <div>
    <AppBar position='static'>
      <ToolBar>
        <IconButton>
          <MenuIcon />
        </IconButton>
      </ToolBar>
    </AppBar>
    <Paper className='content'>
      <LeadRootView />
    </Paper>
  </div>
)
