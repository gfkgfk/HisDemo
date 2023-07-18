import * as Health from "./health";
import * as login from "./login.js";


const Api = Object.assign(
	{}, 
	Health,
	login,
);


export default Api;