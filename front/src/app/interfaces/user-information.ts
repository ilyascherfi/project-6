import { Theme } from "./theme.class";

export interface UserInformation{
    id: number;
    username: string;
    email: string;
    themes: Theme[];
}
