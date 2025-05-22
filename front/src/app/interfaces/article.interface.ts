import { User } from "./user.interface";
import { Theme } from "./theme.interface";
export interface Article {
    postId: number;
    title: string;
    content: string;
    createdAt: Date;
    user: User;
    theme: Theme;
}
