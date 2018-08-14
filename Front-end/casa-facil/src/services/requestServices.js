import http from './http'

export const login = (email, senha) => http.get(`/usuarios/v1/login?email=${email}&senha=${senha}`)

export const criarConta = (user) => http.post('/usuarios/v1', user)

export const enviarEmail = (nome, email) => http.get(`/email/v1/email-enviar-codigo?nome=${nome}&email=${email}`)
