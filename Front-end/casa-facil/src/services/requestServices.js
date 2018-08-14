import http from './http'

export const login = (user) => http.post('/user/login', user)

export const criarConta = user => http.post('user/register', user)
