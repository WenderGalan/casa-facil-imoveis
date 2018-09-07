import { mount } from '@vue/test-utils'
import Login from '../../src/views/Login'

describe('Login', () => {
  // Now mount the component and you have the wrapper
  const wrapper = mount(Login)

  it('renders the correct markup', () => {
    expect(wrapper.html()).toContain('<input type="text" id="email" v-model="email" placeholder="exemplo@dominio.com"' +
      'class="form-control col-sm-12 col-md-4 col-lg-12"/>')
  })

  // it's also easy to check for the existence of elements
  it('has a button', () => {
    expect(wrapper.contains('button')).toBe(true)
  })
})