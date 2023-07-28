import * as Health from "./health";
import * as login from "./login.js";
import * as measure from "./measure.js";


const Api = Object.assign(
	{}, 
	Health,
	login,
	measure
);


export default Api;