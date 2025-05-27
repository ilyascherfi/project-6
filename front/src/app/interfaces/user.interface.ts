import { Theme } from "./theme.class";

export interface User {
	id: number,
	username: string,
	email: string,
	themes: Theme[];
}
