import http from './http'

export const login = (user) => http.post('/usuarios/v1', user)

export const criarConta = (user) => http.post('/usuarios/v1', user)

export const enviarEmail = (nome, email) => http.get(`/email/v1/email-enviar-codigo?nome=${nome}&email=${email}`)
