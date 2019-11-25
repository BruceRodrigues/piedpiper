import './create-lead.css'

import { Field, Form } from 'react-final-form'

import Button from '@material-ui/core/Button'
import Grid from '@material-ui/core/Grid'
import Modal from '@material-ui/core/Modal'
import Paper from '@material-ui/core/Paper'
import React from 'react'
import TextField from '@material-ui/core/TextField'
import axios from 'axios'

const TextFieldAdapter = ({
  input: { name, onChange, value, ...restInput },
  meta,
  ...rest
}) => (
  <TextField
    {...rest}
    name={name}
    helperText={meta.touched ? meta.error : undefined}
    error={meta.error && meta.touched}
    inputProps={restInput}
    onChange={onChange}
    value={value}
  />
)

export default () => {
  const onSubmit = async values => {
    console.log(JSON.stringify(values, 0, 2))
    axios.post('http://localhost:8080/lead', values)
  }
  return (
    <Modal
      open={true}
      className='modal'
      //   onClose={handleClose}
    >
      <Paper className='content'>
        <Form
          onSubmit={onSubmit}
          render={({ handleSubmit, form, submitting, pristine, values }) => (
            <form onSubmit={handleSubmit}>
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <Field
                    fullWidth
                    name='nome'
                    component={TextFieldAdapter}
                    label='Nome'
                  />
                </Grid>
                <Grid item xs={12}>
                  <Field
                    fullWidth
                    required
                    name='empresa'
                    component={TextFieldAdapter}
                    type='text'
                    label='Empresa'
                  />
                </Grid>
                <Grid item xs={12}>
                  <Field
                    fullWidth
                    required
                    name='email'
                    component={TextFieldAdapter}
                    type='text'
                    label='E-mail'
                  />
                </Grid>
                {/* <Grid item xs={12}>
              <TextField label='Telefone' variant='outlined' fullWidth />
            </Grid> */}
                {/* <Grid item xs={12}>
                  <TextField
                    label='Anotações'
                    multiline
                    rows='4'
                    margin='normal'
                    variant='outlined'
                    name='anotacoes'
                    fullWidth
                  />
                  </Grid> */}
                <Grid item xs={12}>
                  <Button
                    color='primary'
                    variant='contained'
                    fullWidth
                    type='submit'
                  >
                    Salvar
                  </Button>
                </Grid>
              </Grid>
            </form>
          )}
        />
      </Paper>
    </Modal>
  )
}
