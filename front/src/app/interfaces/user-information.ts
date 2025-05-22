import { Theme } from "./theme.interface";

export interface UserInformation{
    id: number;
    username: string;
    email: string;
    themes: Theme[];
}
