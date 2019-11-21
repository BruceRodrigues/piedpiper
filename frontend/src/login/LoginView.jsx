import './login.css'

import {
  Avatar,
  Button,
  Grid,
  Link,
  Paper,
  TextField,
  Typography,
} from '@material-ui/core'
import { Link as RouterLink, withRouter } from 'react-router-dom'

import LockOutlinedIcon from '@material-ui/icons/LockOutlined'
import React from 'react'
import { blue } from '@material-ui/core/colors'
import { createStyles } from '@material-ui/core/styles'
import { useHistory } from 'react-router-dom'

const styles = createStyles({
  big: {
    height: 80,
    width: 80,
    backgroundColor: blue[100],
    color: '#fff',
  },
})

export default () => {
  const history = useHistory()

  return (
    <Grid container className='login' style={{ height: '100vh' }}>
      <Grid item xs={false} sm={4} md={7} className='image' />
      <Grid
        container
        item
        alignContent='center'
        xs={12}
        sm={8}
        md={5}
        component={Paper}
        elevation={6}
        square
        justify='center'
        spacing={2}
        className='form'
      >
        <Grid item>
          <Avatar style={styles.big}>
            <LockOutlinedIcon style={{ fontSize: '60px' }} />
          </Avatar>
        </Grid>
        <Grid item xs={12}>
          <Typography variant='h2'>Login</Typography>
        </Grid>
        <Grid item xs={12} sm={12} md={12}>
          <TextField
            variant='outlined'
            id='email'
            label='E-mail'
            autoComplete='email'
            fullWidth
            autoFocus
            required
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            variant='outlined'
            id='senha'
            label='Senha'
            type='password'
            fullWidth
            required
          />
        </Grid>
        <Grid item xs={12}>
          <Button fullWidth size='large' variant='contained' color='primary'>
            Entrar
          </Button>
        </Grid>
        <Grid container justify='space-between'>
          <Grid item>
            <Link
              variant='body2'
              component={RouterLink}
              onClick={() => history.push('/remember')}
            >
              Esqueceu sua senha?
            </Link>
          </Grid>
          <Grid item>
            <Link
              variant='body2'
              component={RouterLink}
              onClick={() => history.push('/main')}
            >
              Cadastre-se
            </Link>
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  )
}
