import { expect } from 'chai'
import { shallowMount } from '@vue/test-utils'
import Login from '../../src/views/Login'

describe('Login', () => {
  // Inspeciona as opções do componente
  it('validar campos', () => {
    expect(typeof Login.validarCampos).toBe('function')
  });

  it('login', () => {
    expect(typeof Login.login).toBe('function')
  });

  // Avalia os resultados das funções
  // nas opções do componente
  it('sets the correct default data', () => {
    expect(typeof Login.data).toBe('function')
    const defaultData = Login.data()
    expect(defaultData.email).toBe('')
  });

  it('sets the correct default data', () => {
    expect(typeof Login.data).toBe('function')
    const defaultData = Login.data()
    expect(defaultData.senha).toBe('')
  })
});
