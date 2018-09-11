import http from './http'

export const login = (email, senha) => http.get(`/usuarios/v1/login?email=${email}&senha=${senha}`)

export const criarConta = (user) => http.post('/usuarios/v1', user)

export const enviarEmail = (nome, email) => http.get(`/email/v1/email-enviar-codigo?nome=${nome}&email=${email}`)

export const alterarUsuario = (user) => http.put('/usuarios/v1', user)

export const deletarUsuario = (id) => http.delete(`usuarios/v1/${id}`)

export const buscarTodosAnuncios = () => http.get('/anuncios/v1')

export const buscarAnunciosUsuario = (id) => http.get(`/anuncios/v1?id=${id}`)

export const buscarAnuncio = (id) => http.get(`/anuncios/v1/${id}`)

export const salvarAnuncio = (idUsuario, anuncio) => http.post(`/anuncios/v1/${idUsuario}`, anuncio)

export const alterarAnuncio = (anuncio) => http.put('/anuncios/v1', anuncio)

export const excluirAnuncio = (idAnuncio) => http.delete(`/anuncios/v1/${idAnuncio}`)

export const enviarEmailAnuncio = (email, idAnuncio) => http.post(`email/v1/email-enviar-contato/${idAnuncio}`, email)

export const salvarImagensAnuncio = (idAnuncio, imagens) => http.post(`/file/v1/enviar-imagens-anuncio/${idAnuncio}`, imagens)

export const salvarImagemUsuario = (idUser, imagem) => http.post(`/file/v1/enviar-imagem-user/${idUser}`, imagem)

export const excluirImagemAnuncio = (id) => http.delete(`/file/v1/deletar-imagem-anuncio/${id}`)
