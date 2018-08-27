import Axios from 'axios'
import constantes from '../util/constantes'

const client = Axios.create({
  baseURL: constantes.API_URL,
  timeout: 20000
})
export default client
