const tokenKey = 'token'

export const getToken = () => {
	return uni.getStorageSync(tokenKey)
}

export const setToken = (token) => {
	uni.setStorageSync(tokenKey, token)
}


export const getItem = (key) => {
	return uni.getStorageSync(key)
}

export const setItem = (key, val) => {
	uni.setStorageSync(key, val)
}