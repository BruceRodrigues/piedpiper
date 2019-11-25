import React, { useEffect, useState } from 'react'

import Button from '@material-ui/core/Button'
import Grid from '@material-ui/core/Grid'
import Table from '@material-ui/core/Table'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'
import TextField from '@material-ui/core/TextField'
import axios from 'axios'
import { useHistory } from 'react-router-dom'

export default () => {
  const [query, setQuery] = useState('')
  const [data, setData] = useState([])
  const history = useHistory()

  useEffect(() => {
    axios
      .get(`http://localhost:8080/lead?name=${query}`)
      .then(result => setData(result.data))
      .catch(err => console.error(err))
  }, [query])

  return (
    <Grid container spacing={2}>
      <Grid item xs={10}>
        <TextField
          id='outlined-basic'
          label='Nome do Lead'
          fullWidth
          margin='normal'
          variant='outlined'
          value={query}
          onChange={event => setQuery(event.target.value)}
        />
      </Grid>
      <Grid item xs={2}>
        <Button
          variant='contained'
          color='primary'
          fullWidth
          onClick={() => history.push('/lead/create')}
        >
          Novo Lead
        </Button>
      </Grid>
      <Grid item xs={12}>
        <Table aria-label='customized table'>
          <TableHead>
            <TableRow>
              <TableCell>Nome</TableCell>
              <TableCell align='right'>Empresa</TableCell>
              <TableCell align='right'>E-mail</TableCell>
              <TableCell align='right'>Site</TableCell>
              <TableCell align='right'>Status</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {data.map(row => (
              <TableRow key={row.name}>
                <TableCell component='th' scope='row'>
                  {row.nome}
                </TableCell>
                <TableCell align='right'>{row.empresa}</TableCell>
                <TableCell align='right'>{row.email}</TableCell>
                <TableCell align='right'>{row.site}</TableCell>
                <TableCell align='right'>{row.status}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Grid>
    </Grid>
  )
}
