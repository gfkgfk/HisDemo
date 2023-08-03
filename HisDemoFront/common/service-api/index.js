import * as Health from "./health";
import * as login from "./login.js";
import * as measure from "./measure.js";
import * as user from "./user.js";
import * as announcement from "./announcement.js";


const Api = Object.assign(
	{}, 
	Health,
	login,
	measure,
	user,
	announcement
);


export default Api;