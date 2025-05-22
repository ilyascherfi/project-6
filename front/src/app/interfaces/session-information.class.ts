import { Theme } from "./theme.interface"

export class SessionInformation {
    constructor(
        public token: string,
        public id: number,
        public username: string,
        public email: string,
        public themes: Theme[]
    ) { }

}
