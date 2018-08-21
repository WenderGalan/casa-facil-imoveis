import http from './http'

export const login = (email, senha) => http.get(`/usuarios/v1/login?email=${email}&senha=${senha}`)

export const criarConta = (user) => http.post('/usuarios/v1', user)

export const enviarEmail = (nome, email) => http.get(`/email/v1/email-enviar-codigo?nome=${nome}&email=${email}`)

export const alterarUsuario = (user) => http.put('/usuarios/v1', user)

export const buscarTodosAnuncios = () => http.get('/anuncios/v1')

export const buscarAnuncio = (id) => http.get(`/anuncios/v1/${id}`)

export const salvarAnuncio = (idUsuario, anuncio) => http.post(`/anuncios/v1/${idUsuario}`, anuncio)

export const alterarAnuncio = (anuncio) => http.put('/anuncios/v1', anuncio)

export const excluirAnuncio = (idAnuncio) => http.delete(`/anuncios/v1/${idAnuncio}`)

export const salvarImagensAnuncio = (idAnuncio, imagens) => http.post(`/file/v1/enviar-imagens-anuncio/${idAnuncio}`, imagens)
