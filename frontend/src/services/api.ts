import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8891'
})

export default api
