export const TOKEN_KEY = '@tcc-user'
export const isAuthenticated = () => localStorage.getItem(TOKEN_KEY) !== null
export const getUser = () => JSON.parse(localStorage.getItem(TOKEN_KEY))
export const login = data => {
  localStorage.setItem(TOKEN_KEY, data)
}
export const logout = () => {
  localStorage.removeItem(TOKEN_KEY)
}
