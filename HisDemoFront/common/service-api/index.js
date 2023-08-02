import * as Health from "./health";
import * as login from "./login.js";
import * as measure from "./measure.js";
import * as user from "./user.js";


const Api = Object.assign(
	{}, 
	Health,
	login,
	measure,
	user
);


export default Api;